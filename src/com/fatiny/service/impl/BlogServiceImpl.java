package com.fatiny.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.print.attribute.standard.Media;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import com.fatiny.dao.BlogDao;
import com.fatiny.dao.ContactDao;
import com.fatiny.dao.TagDao;
import com.fatiny.pojo.Blog;
import com.fatiny.service.BlogService;
import com.fatiny.util.LogContext;
import com.fatiny.util.MediaUtil;
import com.fatiny.util.StringUtil;

@Component
public class BlogServiceImpl implements BlogService{
	private static Logger log = LogContext.LOG_MODULE_BLOG;
	
	private BlogDao blogDao;
	private ContactDao contactDao;
	private TagDao tagDao;
	
	public BlogDao getBlogDao() {return blogDao;}
	public TagDao getTagDao() {return tagDao;}
	public ContactDao getContactDao() {return contactDao;}
	@Resource
	public void setBlogDao(BlogDao blogDao) {this.blogDao = blogDao;}
	@Resource
	public void setTagDao(TagDao tagDao) {this.tagDao = tagDao;}
	@Resource
	public void setContactDao(ContactDao contactDao) {this.contactDao = contactDao;}
	
	private static final int mdContentLength = 340;
	private static final int binaryLength = (mdContentLength / 2);
	
	@Override
	public void saveOrUpdate(Blog blog) {
		blog.setPostDate(new Date());
		blog.setAuthor("Jeremy");
		blog.setMedia(MediaUtil.getMedia(blog.getContent()));
		blog.setContent(MediaUtil.assembleTag(blog.getContent()));
		//入库转换标签
		blog.setContent(StringUtil.escapeHTMLTag(StringUtil.filterLink(blog.getContent())));
		//入库前处理多媒体
		this.blogDao.saveOrUpdate(blog);
	}
	
	/**
	 * 修改查看次数,只有single调用,单词查看有效
	 */
	@Override
	public void updateViewTimes(Blog b) {
		b.setViewTimes(b.getViewTimes()+1);
		this.blogDao.saveOrUpdate(b);
	}
	
	@Override
	public void delete(Integer id) {
		this.blogDao.delete(id);
	}
	
	@Override
	public Blog getById(Integer id) {
		Blog blog = this.blogDao.getById(id);
		this.formatBlog(blog);
		return blog;
	}
	
	@Override
	public List<Blog> getAll() {
		List<Blog> blogs = this.blogDao.getAllBlog();
		return blogs;
	}
	
	/**
	 * 用于取出下一篇文章或者上一篇文章
	 * @param hql
	 * @param max
	 * @return
	 */
	@Override
	public Blog findByLimitFirst(String hql, int max) {
		List<Blog> blogs = this.blogDao.findByLimitFirst(hql,max);
		if (blogs.isEmpty())return null;
		
		Blog blog = blogs.get(0);
		this.formatBlog(blog);
		return blog;
	}
	
	/**
	 * 根据需要查出需要的文章
	 * @param hql
	 * @param max
	 * @return
	 */
	@Override
	public List<Blog> findListByLimitFirst(String hql, int max) {
		List<Blog> blogs = this.blogDao.findByLimitFirst(hql,max);
		this.formatBlogs(blogs);
		return blogs;
	}
	
	/**
	 * 用于上一篇,下一篇
	 * @param hql
	 * @param first
	 * @param max
	 * @return
	 */
	@Override
	public List<Blog> findByLimit(String hql, int first, int max) {
		List<Blog> blogs = this.blogDao.getByLimit(hql, first, max);
		this.formatBlogs(blogs);
		return blogs;
	}
	
	@Override
	public List<Blog> findByLimit(String text, String hql, int first, int max) {
		List<Blog> blogs = this.blogDao.getByLimit(hql, first, max);
		if (text != null && !text.equals("")) {
			this.formatFindBlogs(text, blogs);
		}else {
			this.formatBlogs(blogs);
		}
		return blogs;
	}
	
	/**
	 * 普通渲染页面之前处理文本.时间
	 * @param List<Blog>
	 */
	private void formatBlogs(List<Blog> blogs){
		for (Blog blog : blogs) {
			if (blog == null)continue;
			this.formatBlog(blog);
			this.subString(blog);
		}
	}
	
	/**
	 * 模糊查询专用渲染页面之前处理文本.时间
	 * @param List<Blog>
	 */
	private void formatFindBlogs(String text,List<Blog> blogs){
		for (Blog blog : blogs) {
			if (blog == null)continue;
			this.formatBlog(blog);
			this.subAndRep(text,blog);
		}
	}
	
	/**
	 * subString and replace
	 * 这个方法查找专用
	 * 1.查找显示出高亮标签
	 * 2.根据需要裁剪长度
	 */
	private void subAndRep(String text,Blog blog)
	{
		//高亮代码
	    String light = "<span class=\"highlight3\">"+text+"</span>";
	    //文本内容
	    String mdc = blog.getMdContent();
	    //文本总长度
	    int mdcSize = mdc.length();
	    //关键字所处位置
	    int index = mdc.indexOf(text);
	    if(index== -1) return;
	    //关键字之前的文本长度
	    int beforeTextSize = mdc.substring(0, index).length();
	    //关键字之后的文本长度
	    int afterTextSize = mdc.substring(index,mdcSize).length();
	    afterTextSize = mdcSize - beforeTextSize;
	    //若当前文本过大,则截取处理文本
	    if (mdcSize >= mdContentLength){
	    	//1.指定文本长度,如果关键字之前在指定长度范围内,则从0开始
	    	if (beforeTextSize < binaryLength){
	    		mdc=mdc.substring(0,mdContentLength);
	    	}
	    	else if(afterTextSize < binaryLength){
	    		mdc=mdc.substring((mdcSize-mdContentLength),mdcSize);
			}
	    	else {
	    		int begin = (beforeTextSize-binaryLength);
	    		int end = (beforeTextSize+binaryLength);
	    		mdc=mdc.substring(begin,end);
			}
		}
	    log.info("长度:"+mdc.length());
	    //若当前文本小于340,不作处理,然后replace标签.
	    String temp = StringUtil.replaceStr(mdc, text, light);
	    mdc = !temp.equals("") ? temp :mdc;
	    //if(!temp.equals("")){mdc = temp;}
	    //mdc = mdc.replaceAll();
	    blog.setMdContent(mdc+"...");
	}
	
	
	/**
	 * 显示固定长度的数据
	 * @param blog
	 */
	private void subString(Blog blog)
	{
	    int length = blog.getMdContent().length();
	    if (length > mdContentLength) {
	    	length = mdContentLength;
		}
	    blog.setMdContent(blog.getMdContent().substring(0, length) + "...");
	}
	
	/**
	 * 渲染页面之前处理文本.时间
	 * @param blog
	 */
	private static String hqlcount = "select count(*) from Contact as c where c.bid = ";
	private static String hqltag = "from Tag t where t.id in (select b.tid from BlogTag b where b.bid = :bid)";
	private void formatBlog(Blog blog){
		blog.setCount(this.contactDao.getCount(hqlcount + blog.getBid()));
		blog.setTags(this.tagDao.findByNameParam(hqltag, "bid", blog.getBid()));
		//处理文本
		String content = StringUtil.unEscapeHTMLTag(blog.getContent());
		blog.setContent(content);
	}

	/**
	 * 入库之前处理文本
	 * @return
	 */
	@SuppressWarnings("unused")
	private void unformatBlogs(List<Blog> blogs){
		for (Blog blog : blogs) {
			unformatBlog(blog);
		}
	}
	
	/**
	 * 入库之前处理文本
	 * @param blog
	 */
	private void unformatBlog(Blog blog){
		String content = StringUtil.escapeHTMLTag(blog.getContent());
		blog.setContent(content);
	}
	
	/**
	 * 取出所有文章的数量
	 * @return
	 */
	@Override
	public int getCount() {
		String hqlcount = "select count(*) from Blog";
		return blogDao.getCount(hqlcount);
	}

}
