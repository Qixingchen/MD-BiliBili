package me.qixingchen.mdbilibili.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Yulan on 2015/6/18.
 * bilibili 读取用户推荐信息 API
 * http://docs.bilibili.cn/wiki/API.recommend
 */
public class Recommend {
	public class last_recommend {
		private String uname;/*推荐人名字*/
		private int mid;/*推荐人ID*/
		private long time;/*推荐时间*/
		private String msg;/*推荐信息*/
		private String face;/*推荐人头像地址*/
	}

	public class listclass {
		@SerializedName("last_recommend")
		private List<last_recommend> last_recommends;/*最后推荐信息*/
		private String duration;/*视频时长*/
		private int coins;/*推荐数量*/
		private int credit;/*评分数量*/
		private String pic;/*封面图片地址*/
		private String create;/*视频创建日期*/
		private String description;/*视频简介*/
		private String author;/*视频作者*/
		private int mid;/*视频作者ID*/
		private int favorites;/*收藏数*/
		private int video_review;/*弹幕数*/
		private int review;/*评论数*/
		private String play;/*播放次数*/
		private String subtitle;/*视频副标题*/
		private String title;/*视频标题*/
		private String typename;/*视频分类名称*/
		private int typeid;/*视频分类ID*/
		private int aid;/*视频编号*/

		public List<last_recommend> getLast_recommends() {
			return last_recommends;
		}

		public String getDuration() {
			return duration;
		}

		public int getCoins() {
			return coins;
		}

		public int getCredit() {
			return credit;
		}

		public String getPic() {
			return pic;
		}

		public String getCreate() {
			return create;
		}

		public String getDescription() {
			return description;
		}

		public String getAuthor() {
			return author;
		}

		public int getMid() {
			return mid;
		}

		public int getFavorites() {
			return favorites;
		}

		public int getVideo_review() {
			return video_review;
		}

		public int getReview() {
			return review;
		}

		public String getPlay() {
			return play;
		}

		public String getSubtitle() {
			return subtitle;
		}

		public String getTitle() {
			return title;
		}

		public String getTypename() {
			return typename;
		}

		public int getTypeid() {
			return typeid;
		}

		public int getAid() {
			return aid;
		}
	}

	private String code;
	private String pages;
	private String num;
	@SerializedName("list")
	private List<listclass> lists;

	public String getCode() {
		return code;
	}

	public String getPages() {
		return pages;
	}

	public String getNum() {
		return num;
	}

	public List<listclass> getLists() {
		return lists;
	}
}
