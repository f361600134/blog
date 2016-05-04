#-*- coding: UTF-8 -*- 
'''
Created on 2015年09月08日
@author: Jeremy
'''

import json
import logging
import random

import Utils
from com.janlr.server.scripts import JythonHelper


logging.basicConfig(level=logging.ERROR)

'''
金币副本模块
@note: 
1.玩家
货币副本有挑战次数的限制, 所以需要在avatar里面新增属性: 货币副本挑战次数，历史最高记录
2.计时器
因为挑战次数的限制,所以挑战次数每天要更新成初始值.
3.服务器存储数据结构(服务器统计细心):{怪物ID:[生成次数, 杀死次数], 怪物ID:[生成次数, 杀死次数]},临时数据，打完副本清除数据
eg:{10015:[3, 3], 10016:[4,3]}
4.服务器配置数据结构:{玩家等级id:{怪物id:[是否投放,出现上线,权重,掉落金币]}
eg:{20:{10015:[], 10016:[3,50,10]}, 30:{10015:[3,50,10], 10016:[3,50,10]}}
'''

#存储数据结构
INFO_INDEX_CREATE  =   0   #代表"生成次数"下标
INFO_INDEX_KILL    =   1   #代表"杀死次数"下标

#配置数据结构
'''
DATA_INDEX_COUNT   =   0   #怪物出现次数  
DATA_INDEX_GOLD    =   1   #怪物兑换金币
DATA_INDEX_DIAMOND =   2   #怪物兑换元宝
DATA_INDEX_WEIGHT  =   3   #怪物权重
DATA_INDEX_ISPUT   =   4   #是否投放
'''

#每次出的怪物总量
REFRESH_MONSTER_NUMBER = 5

class GoldInstanceSystem:
    
    def __init__(self, owner):
        self.owner = owner
        self.globalData = JythonHelper.getJavaJythonGlobalObject().getGlobalData();
    
    '''
    @summary: 初始化一个新的怪物信息
    @param monsterId: 通过ID生成新的怪物临时信息
    @return result: 返回新生成的怪物信息 
    '''
    def newStatistic(self, monsterId):
        result = {}
        infoList = []
        infoList.insert(INFO_INDEX_CREATE, 0)
        infoList.insert(INFO_INDEX_KILL, 0)
        result[monsterId] = infoList
        return result
    
    '''
    @summary: 获取到玩家临时生成的怪物信息
    @return:  dict类型的怪物统计
    @warning: 注释
    '''
    def getGoldInstanceStatistic(self):
        return self.owner.getGoldInstanceStatistic()
    
    '''
    @summary: 获取到玩家临时生成的怪物信息
    @param: monsterId, 怪物id
    @return:  dict类型的怪物统计
    @warning: 注释
    '''
    def getStatisticInfoById(self, monsterId):
        statistic = self.owner.getGoldInstanceStatistic()
        if statistic:
            info = statistic.get(monsterId)
            if info:
                return info
            else:
                logging.error("GoldInstanceSystem, method: getStatisticInfoById, info is null, statistic:%s:,monsterId:%d" % (statistic, monsterId))
        else:
            logging.error("GoldInstanceSystem, method: getStatisticInfoById, statistic is null, statistic:%s:,monsterId:%d" % (statistic))
        return None

    '''
    @summary: 获取到金币副本限制
    @warning: 注释
    '''
    def getGoldInstanceLimit(self):
        limit = self.owner.getGoldInstanceLimit()
        print "getGoldInstanceLimit, limit:", limit
        return limit
    
    '''
    @summary: 获取到金币副本限制
    @warning: 注释
    '''
    def setGoldInstanceLimit(self, times):
        self.owner.setGoldInstanceLimit(times)
        
    '''
    @summary: 获取到金币副本最佳记录
    @warning: 注释
    '''
    def getGoldInstanceBest(self):
        return self.owner.getGoldInstanceBest()
    
    '''
    @summary: 获取到金币副本最佳纪录
    @warning: 注释
    '''
    def setGoldInstanceBest(self, best):
        self.owner.setGoldInstanceBest(best)
    
    '''
    @summary: 获取到金币副本配置文件
    '''
    def getGoldInstanceData(self):
        goldInstanceData = self.globalData.goldInstanceData
        if goldInstanceData:
            return goldInstanceData
        logging.error("GoldInstanceSystem, method: getGoldInstanceData, goldInstanceData: %s:" % (goldInstanceData))
        return None
   
    '''
    @summary: 根据玩家等级,怪物等级获取到金币副本配置文件
    @param roleLevel: 通过玩家等级，获取到金币副本的配置信息
    @return roleLevelValueData:
    '''
    def getGoldInstanceDataByLevel(self, roleLevel):
        goldInstanceData = self.getGoldInstanceData()
        if goldInstanceData:
            roleLevelKey = Utils.ceil_key(goldInstanceData, roleLevel)
            if roleLevelKey:
                roleLevelValueData = goldInstanceData.get(roleLevelKey)
                if roleLevelValueData:
                    return roleLevelValueData
                else:
                    logging.error("GoldInstanceSystem, method: getGoldInstanceDataByLevel, roleLevelValueData: %s:" % (roleLevelValueData))
            else:
                logging.error("GoldInstanceSystem, method: getGoldInstanceDataByLevel, roleLevelKey: %s:" % (roleLevelKey))
        else:
            logging.error("GoldInstanceSystem, method: getGoldInstanceDataByLevel, goldInstanceData: %s:" % (goldInstanceData))
        return None
    
    
    '''
    @deprecated: 弃用函数,现在使用updateStatisticByCreate and updateStatisticByKill
    @summary: 保存临时怪物信息
    @param updateInfo: 生成怪物，或者记录杀死怪物数量的时候调用
    eg: updateInfo={10015:1, 10016:2}
    @param way: 记录标识. INFO_INDEX_CREATE or INFO_INDEX_KILL
    @warning: 这里需要调用Avatar.py中的保存
    def updateStatistic(self, updateInfo, way):
        if way != INFO_INDEX_CREATE or way != INFO_INDEX_KILL:
            logging.error("GoldInstanceSystem, method: updateStatistic, way: %d:" % (way))
            return
        statistic = self.getGoldInstanceStatistic()
        #迭代需要保存的怪物信息
        for uid in updateInfo:
            #取出怪物数量
            uNum = updateInfo[uid]
            #判断保存的怪物信息中是否存在这只怪物，若存在，累加。
            if uid in statistic:
                mInfo = statistic[uid]
                mInfo[way] = mInfo[way] + uNum
            else:
                #若不存在,打印错误信息
                logging.error("GoldInstanceSystem, method: updateStatistic, updateInfo(uid) is not found: %d, way:%d:" % (uid, way))
    '''
   
    '''
    @summary: 保存临时怪物生成信息
    @param updateInfo: 生成怪物，或者记录杀死怪物数量的时候调用
    eg: updateInfo={10015:1, 10016:2}
    @param way: 记录标识. INFO_INDEX_CREATE or INFO_INDEX_KILL
    @warning: 这里需要调用Avatar.py中的保存
    '''
    def updateStatisticByCreate(self, updateInfo):
        statistic = self.getGoldInstanceStatistic()  #迭代需要保存的怪物信息
        print "GoldInstanceSystem, method:updateStatisticByCreate, statistic:updateInfo:" , statistic, updateInfo
        for uid in updateInfo:
            #取出需要更新的怪物数量
            uNum = updateInfo[uid]
            #print "GoldInstanceSystem, method:updateStatisticByCreate, uid, uNum, statistic" ,uid, uNum, statistic
            #判断保存的怪物信息中是否存在这只怪物
            if uid in statistic:
                #取出临时信息中的怪物信息
                #sInfo = statistic[uid]
                sInfo = self.getStatisticInfoById(uid)
                #若存在,累加.
                sInfo[INFO_INDEX_CREATE] = sInfo[INFO_INDEX_CREATE] + uNum
                #print "GoldInstanceSystem, method:updateStatisticByCreate, in statistic" ,uid, uNum, statistic
            else:
                #若不存在,添加新的数据
                newStatisticInfo = self.newStatistic(uid)
                print "GoldInstanceSystem, method:updateStatisticByCreate, 111 newStatisticInfo:" , newStatisticInfo
                newStatisticInfo[uid][INFO_INDEX_CREATE] = uNum
                statistic.update(newStatisticInfo)
                print "GoldInstanceSystem, method:updateStatisticByCreate, 222 newStatisticInfo:" , statistic
                #print "GoldInstanceSystem, method:updateStatisticByCreate, not in statistic" ,uid, uNum, statistic
        #循环完毕,保存信息
        self.saveStatistic(statistic)
    
    '''
    @summary: 保存临时怪物杀死数量信息
    @param updateInfo: 生成怪物，或者记录杀死怪物数量的时候调用
    eg: updateInfo={10015:1, 10016:2}
    @param way: 记录标识. INFO_INDEX_CREATE or INFO_INDEX_KILL
    @warning: 这里需要调用Avatar.py中的保存
    '''
    def updateStatisticByKill(self, updateInfo):
        statistic = self.getGoldInstanceStatistic()
        #迭代需要保存的怪物信息
        for uid in updateInfo:
            #取出需要更新的怪物数量
            uNum = updateInfo[uid]
            #判断保存的怪物信息中是否存在这只怪物
            if uid in statistic:
                #取出临时信息中的怪物信息
                #mInfo = statistic[uid]
                mInfo = self.getStatisticInfoById(uid)
                #若存在,累加.若杀敌次数大于了生成的怪物数量,(客户端作弊等), 则直接赋值为生成怪物数量
                allKillNum = mInfo[INFO_INDEX_KILL] + uNum
                if mInfo[INFO_INDEX_CREATE] < allKillNum:
                    mInfo[INFO_INDEX_KILL] = mInfo[INFO_INDEX_CREATE]
                else:
                    mInfo[INFO_INDEX_KILL] = allKillNum
            else:
                #若不存在,打印错误信息
                logging.error("GoldInstanceSystem, method: updateStatisticByKill, uid: %d, type:%s, statistic:%s" % (uid, type(uid), statistic))
        #循环完毕,保存信息       
        self.saveStatistic(statistic)
    
    '''
    @summary: 保存怪物属性
    @warning: 注释
    '''
    def saveStatistic(self, statistic):
        if not statistic:
            logging.error("GoldInstanceSystem, method: saveStatistic, statistic is {}")
            return
        if not isinstance(statistic, dict):
            logging.error("GoldInstanceSystem, method: saveStatistic, statistic is not Dict: %s:" % (type(statistic)))
            return
        print "GoldInstanceSystem, Mrthod:setGoldInstanceLimit, statistic:", statistic
        self.owner.setGoldInstanceStatistic(statistic)
        
    '''
    @summary: 清除掉统计信息
    @warning: 注释
    '''
    def clearStatistic(self):
        self.owner.setGoldInstanceStatistic({})
        print "GoldInstanceSystem, Method:clearStatistic, statistic:", self.getGoldInstanceStatistic()
        
    
    '''
    @summary: 处理副本选择
    @note：选择副本，判断是否可进入副本.
    @return errorCode:错误码; result:字符串类型的怪物信息结果集,eg:{10015:1, 10016:3}
    '''
    def processInstanceSelect(self):
        #self.clearStatistic()
        errorCode = self.checkSelect()
        if errorCode != 0:
            logging.error("GoldInstanceSystem, method: processInstanceSelect, errorCode: %d:" % (errorCode))
            errorCode = 0
        
        result = ""
        allMonster = ""
        if errorCode == 0:
            #减去一次挑战机会
            self.owner.addGoldInstanceLimit(-1)
            #处理怪物的筛选等.生成的怪物要进行统计
            allMonsterInfo = {}
            for num in range(0, 3):
            #    beginTime = time.time() 
                monsterInfo = self.refreshMonster()
                allMonsterInfo = Utils.unionDict(allMonsterInfo, monsterInfo)
            #print "GoldInstanceSystem, method:processInstanceSelect, allMonsterInfo:", allMonsterInfo
            #怪物创建信息,保存到服务器临时信息中
            self.updateStatisticByCreate(allMonsterInfo)
            #转换成客户端需要的数据,返回
            result = Utils.jsonDumpsAndStripSpace(allMonsterInfo)
            #所有怪物id
            monsterIds = self.getMonstersPosInfo()
            allMonster = Utils.jsonDumpsAndStripSpace(monsterIds)
        return errorCode, allMonster, result
    
    
    
    '''
    @summary: 处理副本选择
    @return 0:可以进入. 1:挑战次数不足, 2:系统错误
    '''
    def checkSelect(self):
        if self.getGoldInstanceLimit() <= 0:
            return 1
        return 0
    
    '''
    @summary: 处理怪物筛选
    @note: 提示
    1.这段代码可以即使,判断循环执行1次耗时
    2.这段代码服务器运行1000次,看看出的数据,神奇怪物会出现
    @return: 
    dict类型的怪物信息, eg:{怪物id1: 数量1, 怪物id2:数量2}
    '''
    def refreshMonster_bak(self):
        result = {}
        roleLevel = self.owner.getLevel()
        goldInstanceDict = self.getGoldInstanceData()
        roleLevel = Utils.ceil_key(goldInstanceDict, roleLevel)
        #print "GoldInstanceSystem, method:refreshMonster, roleLevel, goldInstanceDict:", roleLevel, goldInstanceDict
        goldInstanceData = self.getGoldInstanceDataByLevel(roleLevel)
        #print "GoldInstanceSystem, method:refreshMonster, goldInstanceData:", goldInstanceData
        #获取到记录的怪物统计信息等
        statistic = self.getGoldInstanceStatistic()
        #print "GoldInstanceSystem, method:refreshMonster, statistic:", statistic
        
        monstersList,weightList = self.getMonstersAndWeight_bak()
        simpleMosterId = monstersList[-1]
        #print "GoldInstanceSystem, method:refreshMonster, monstersList, simpleMosterId: weightList:", monstersList, simpleMosterId, weightList
        #剩余可以生成怪物总量
        remainder = REFRESH_MONSTER_NUMBER
        #循环所有怪物,获取随机数是否可以
        #for monsterId in goldInstanceData:
        for monsterId in monstersList:
            #print "GoldInstanceSystem, method:refreshMonster, monsterId:", monsterId
            if monsterId in statistic:
                monster = self.getStatisticInfoById(monsterId)
                createTimes = monster[INFO_INDEX_CREATE]
            else:
                createTimes = 0
            #print "GoldInstanceSystem, method:refreshMonster, createTimes,", createTimes
            
            goldInstance = goldInstanceData[monsterId]
            weight = goldInstance.get("weight_i")#权重
            count = goldInstance.get("count_i")#数量
            #获取到普通怪物的ID,三元表达式
            #simpleMosterId = simpleMosterId == weight?monsterId:simpleMosterId
            simpleMosterId = monsterId if simpleMosterId == weight else simpleMosterId
            #print "GoldInstanceSystem, method:refreshMonster, simpleMosterId:", simpleMosterId
            #如果生成次数小于等于生成上限制,取随机数.随机出
            if createTimes < count:
                isBreak = True
                #这里可以加个while循环,用于实现策划的循环需求
                while isBreak:
                    #print "GoldInstanceSystem, method:refreshMonster, result, monsterId:", result, monsterId
                    #随机一个值,如果这个值在当前怪物的权重范围内,再循环一次
                    rate = random.randint(0, 100)
                    value = Utils.ceilListKey(weightList, rate) 
                    #print "GoldInstanceSystem, method:refreshMonster, rate,value", rate, value
                    
                    if  value == weight:
                        #怪物中标,剩余怪物总量-1,加入集合
                        remainder -=1
                        if monsterId not in result:
                            result[monsterId] = 1
                        else:
                            result[monsterId] = result[monsterId] + 1
                    else:
                        #没中标,停止循环,循环下一个怪物
                        isBreak = False
                    #如果已经生成了指定数量的怪物,则停止循环
                    if remainder == 0:
                        break
        #如果怪物没有生成到指定数量,则生成全部的小怪
        if remainder > 0:
            #print "GoldInstanceSystem, method:refreshMonster, result, simpleMosterId,remainder:", result, simpleMosterId,remainder
            if simpleMosterId not in result:
                result[simpleMosterId] = remainder
            else:
                result[simpleMosterId] = result[simpleMosterId] + remainder
        #print "GoldInstanceSystem, method:refreshMonster, result:", result
        #返回生成的怪物集合
        return result
    
    '''
    @summary: 处理怪物筛选,改版
    @note: 提示
    1.循环指定次数(5),随机取出一个概率,通过概率取出怪物id
    '''
    def refreshMonster(self):
        result = {}
        roleLevel = self.owner.getLevel()
        goldInstanceDict = self.getGoldInstanceData()
        roleLevel = Utils.ceil_key(goldInstanceDict, roleLevel)
        print "GoldInstanceSystem, method:refreshMonster, roleLevel, goldInstanceDict:", roleLevel, goldInstanceDict
        goldInstanceData = self.getGoldInstanceDataByLevel(roleLevel)
        print "GoldInstanceSystem, method:refreshMonster, goldInstanceData:", goldInstanceData
        #获取到记录的怪物统计信息等
        statistic = self.getGoldInstanceStatistic()
        print "GoldInstanceSystem, method:refreshMonster, statistic:", statistic
        weightDict = self.getMonstersWeightInfo()
        print "GoldInstanceSystem, method:refreshMonster, weightDict:",weightDict
        #剩余可以生成怪物总量
        remainder = REFRESH_MONSTER_NUMBER
        #循环所有怪物,获取随机数是否可以
        while remainder > 0:
            #随机一个值,如果这个值在当前怪物的权重范围内,再循环一次
            rate = random.randint(0, 100)
            #通过随机数计算出来的权值
            value = Utils.ceil_key(weightDict, rate)
            print "GoldInstanceSystem, method:refreshMonster, rate,value:",rate,value
            #通过权值获取到怪物id
            monsterId = weightDict.get(value)
            
            #判断统计信息中,是否存在这只怪物,如果存在获取到怪物创建次数
            if monsterId in statistic:
                monster = self.getStatisticInfoById(monsterId)
                createTimes = monster[INFO_INDEX_CREATE]
            else:
                createTimes = 0
            #获取到怪物配置信息
            goldInstance = goldInstanceData[monsterId]
            count = goldInstance.get("count_i")#数量
            #判断当前出现次数是否小于指定次数
            if createTimes < count:
                remainder -=1
                if monsterId not in result:
                    result[monsterId] = 1
                else:
                    result[monsterId] = result[monsterId] + 1
        print "GoldInstanceSystem, method:refreshMonster, result:",result
        return result
    
    
    '''
    @summary: 通过配置, 获取到权重列表
    @return 权重id列表.从小到大排序
    @return 怪物id,按照权重排序.
    
    def getWeightList(self, goldInstanceData):
        weightList = []
        for monsterId in goldInstanceData:
            goldInstance = goldInstanceData[monsterId]
            weight = goldInstance.get("weight_i")#权重
            weightList.append(weight)
        weightList.sort()
        return weightList
    '''
   
    '''
    @summary: 让怪物id根据权重从小到大排序.权重为key, 怪物id为value
    @return : 返回字典类型的怪物ids
    '''
    def getMonstersWeightInfo(self):
        tempDict = {}
        roleLevel = self.owner.getLevel()
        goldInstanceData = self.getGoldInstanceDataByLevel(roleLevel)
        for monsterId in goldInstanceData:
            goldInstance = goldInstanceData[monsterId]
            weight = goldInstance.get("weight_i")#权重
            tempDict[weight] = monsterId
        return tempDict
   
   
    '''
    @summary: 让怪物id根据权重从小到大排序
    @return : 权重ids, 排列后的怪物ids
    '''
    def getMonstersAndWeight_bak(self):
        weightList = []
        monsterList = []
        tempDict = self.getMonstersWeightInfo()
        '''
        roleLevel = self.owner.getLevel()
        goldInstanceData = self.getGoldInstanceDataByLevel(roleLevel)
        for monsterId in goldInstanceData:
            goldInstance = goldInstanceData[monsterId]
            weight = goldInstance.get("weight_i")#权重
            tempDict[weight] = monsterId
        '''
        keys = tempDict.keys() 
        keys.sort()
        
        for key in keys:
            weightList.append(key)
            monsterList.append(tempDict[key])
        return monsterList, weightList
    
    '''
    @summary: 通过配置, 获取到怪物id, key:pos, value:monsterId.并标识出位置信息返回个客户端使用105消息使用
    @return 怪物id字典,权重为key,
    '''
    def getMonstersPosInfo(self):
        result = {}
        pos = 4
        #monsterList, weightList = self.getMonstersAndWeight()
        tempDict = self.getMonstersWeightInfo()
        keys = tempDict.keys() 
        keys.sort()
        
        monsterList = []
        for key in keys:
            monsterList.append(tempDict[key])
        
        for mid in monsterList:
            result[pos] = mid
            pos-=1
            
        return result
    
    '''
    @summary: 处理副本刷新怪物
    '''
    def processInstanceRefreshMonster(self):
        monsterInfo = self.refreshMonster()
        self.updateStatisticByCreate(monsterInfo)
        print "GoldInstanceSystem, Method:processInstanceRefreshMonster,monsterInfo:",monsterInfo,type(monsterInfo)
        result = Utils.jsonDumpsAndStripSpace(monsterInfo)
        return result
    
    '''
    @summary: 处理统计杀怪信息
    @param: 客户端传过来，杀死怪物的信息。｛怪物ID:怪物数量｝
    '''
    def processInstanceStatistics(self, updateInfoStr):
        updateInfoDict = json.loads(updateInfoStr)
        updateInfoDict = Utils.parseDict(updateInfoDict)
        print "GoldInstanceSystem, Method:processInstanceStatistics,updateInfoDict:",updateInfoDict,type(updateInfoDict)
        #updateInfoDict = {10105:4,10110:1,10107:2,10109:3}
        self.updateStatisticByKill(updateInfoDict)
     
    '''
    @summary: 处理副本结束
    '''
    def processInstanceEnd(self):
        #需要返回的数据
        allGold = 0
        allDiamond = 0
        best = self.getGoldInstanceBest()
        print "GoldInstanceSystem , Method: processInstanceEnd, best:",best
            
        #验证副本
        #self.checkEnd()
        #通过角色等级获取配置信息
        roleLevel = self.owner.getLevel()
        goldMonsterData = self.getGoldInstanceDataByLevel(roleLevel)
        print "GoldInstanceSystem , Method: processInstanceEnd, roleLevel, goldMonsterData:",roleLevel ,goldMonsterData
        #获取到记录的临时怪物统计信息
        statisticDict = self.getGoldInstanceStatistic()
        print "GoldInstanceSystem , Method: processInstanceEnd, statisticDict:", statisticDict
        for sid in statisticDict:
            statisticList = statisticDict[sid]
            killNum = statisticList[INFO_INDEX_KILL]
            monsterDataList = goldMonsterData.get(sid)
            print "GoldInstanceSystem , Method: processInstanceEnd, monsterDataList:goldMonsterData:",monsterDataList,goldMonsterData
            
            gold = monsterDataList.get("gold_i")
            diamond = monsterDataList.get("ingot_i")
            
            if gold != 0:
                allGold = allGold + gold * killNum
            if diamond != 0:
                allDiamond = allDiamond + diamond * killNum
        
        if allGold > best:
            best = allGold
            self.setGoldInstanceBest(best)
        #保存金币,元宝
        self.owner.addGold(allGold)
        self.owner.addDiamond(allDiamond)
        
        #清除掉缓存信息
        self.clearStatistic()
        print "GoldInstanceSystem , Method: processInstanceEnd, allGold,allDiamond,best:",allGold,allDiamond,best
        return allGold,allDiamond
    
    '''
    @summary: 检测副本结束.纯测试使用
    1.检测临时怪物信息数据中, 杀死的怪物是否大于生成的怪物数量.若大于,则打印错误信息
    2.判断怪物出现次数有没有超出上限
    3.这个检测副本信息,用于检测,验证服务器代码的业务性,逻辑性是否正确.不影响最终结果
    '''    
    def checkEnd(self):
        statistic = self.getGoldInstanceStatistic()
        print "GoldInstanceSystem , Method: checkEnd, statistic:",statistic
        roleLevel = self.owner.getLevel()
        print "GoldInstanceSystem , Method: checkEnd, roleLevel:",roleLevel
        goldMonsterData = self.getGoldInstanceDataByLevel(roleLevel)
        print "GoldInstanceSystem , Method: checkEnd, goldMonsterData:",goldMonsterData
        for sid in statistic:
            #monsterData = goldMonsterData[sid]
            #monster = statistic[sid]
            monster = self.getStatisticInfoById(sid)
            if monster[INFO_INDEX_CREATE] < monster[INFO_INDEX_KILL]:
                logging.error("GoldInstanceSystem, method: checkEnd, ==1111=== sid: %d:" % (sid))
            
            #if monster[INFO_INDEX_CREATE] > monsterData[DATA_INDEX_COUNT]:
            #    logging.error("GoldInstanceSystem, method: checkEnd, ==2222=== sid: %d:" % (sid))
    
        
    