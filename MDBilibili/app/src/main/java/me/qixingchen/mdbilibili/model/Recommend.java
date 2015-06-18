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
		private String tname;
		private int mid;
		private long time;
		private String msg;
		private String face;
	}

	public class listclass {
		@SerializedName("last_recommend")
		private List<last_recommend> last_recommends;
		private String duration;
		private int coins;
		private int credit;
		private String pic;
		private String create;
		private String description;
		private String author;
		private int mid;
		private int favorites;
		private int video_review;
		private int review;
		private String play;
		private String subtitle;
		private String title;
		private String typename;
		private int typeid;
		private int aid;

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
