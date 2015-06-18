package me.qixingchen.mdbilibili.model;

/**
 * Created by Yulan on 2015/6/18.
 * bilibili 读取用户推荐信息 API
 * http://docs.bilibili.cn/wiki/API.recommend
 */
public class Recommend {
	public class last_recommend {

	}

	public class list {
		private last_recommend last_recommend;
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
		private int play;
		private String subtitle;
		private String title;
		private String typename;
		private int typeid;
		private int aid;


		public Recommend.last_recommend getLast_recommend() {
			return last_recommend;
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

		public int getPlay() {
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
	private int pages;
	private int num;
	private list list;

	public String getCode() {
		return code;
	}

	public int getPages() {
		return pages;
	}

	public int getNum() {
		return num;
	}

	public Recommend.list getList() {
		return list;
	}
}
