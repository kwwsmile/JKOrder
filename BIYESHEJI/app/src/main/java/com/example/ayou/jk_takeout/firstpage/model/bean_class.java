package com.example.ayou.jk_takeout.firstpage.model;

import java.util.List;

/**
 * Created by AYOU on 2017/4/24.
 */

public class bean_class {


    /**
     * status : ok
     * data : {"list":[{"id":"1","parent":"0","name":"小吃快餐","flag":"ms","oslcount":"0","procount":"0","icon-raw":"http://wm.gou00.cn/uploads/2016-10-28/43a2ca125b107f97eaf4413eab464e47.png"},{"id":"2","parent":"1","name":"中式简餐","flag":"zhongshi","oslcount":"39","procount":"39","icon-raw":"http://wm.gou00.cn/uploads/2016-05-10/e9a06577162007a4895c967b3e4cb8f0.png"},{"id":"12","parent":"1","name":"炸鸡炸串","flag":"zhaji","oslcount":"15","procount":"15","icon-raw":"http://wm.gou00.cn/uploads/2016-10-28/da1d86813b15ba46787f9847d5bd2559.png"},{"id":"6","parent":"1","name":"面条米线","flag":"mian","oslcount":"44","procount":"44","icon-raw":"http://wm.gou00.cn/uploads/2016-10-28/c11d71af1f543cbc6f7bbdd8eb64165e.png"},{"id":"3","parent":"1","name":"地方小吃","flag":"difang","oslcount":"46","procount":"46","icon-raw":"http://wm.gou00.cn/uploads/2016-05-10/fe7d8d23238d8135cf7d617d87c07beb.png"},{"id":"27","parent":"0","name":"海鲜烧烤","flag":"shao","oslcount":"0","procount":"0","icon-raw":"http://wm.gou00.cn/uploads/2016-10-28/ddead83fcb7635d284bfd8d5e4a42aca.png"},{"id":"30","parent":"0","name":"水果蔬菜","flag":"shuiguo","oslcount":"0","procount":"0","icon-raw":"http://wm.gou00.cn/uploads/2016-05-10/7582591a555fa227113d592be3bcd7ec.png"},{"id":"23","parent":"0","name":"甜点饮品","flag":"tiandian","oslcount":"0","procount":"0","icon-raw":"http://wm.gou00.cn/uploads/2016-10-28/aac8f5695a2ba0f811a5b703defb8701.png"},{"id":"20","parent":"0","name":"日韩料理","flag":"ri","oslcount":"0","procount":"0","icon-raw":"http://wm.gou00.cn/uploads/2016-10-28/74eb7e8b8378ea221995c74a5de60798.png"},{"id":"14","parent":"0","name":"蛋糕鲜花","flag":"dangao","oslcount":"0","procount":"0","icon-raw":"http://wm.gou00.cn/uploads/2016-10-28/38f47c469d91b75c124aca5a33d10027.png"},{"id":"8","parent":"1","name":"卤味鸭脖","flag":"luwei","oslcount":"2","procount":"2","icon-raw":"http://wm.gou00.cn/uploads/2016-10-28/fb86d66086f793d3a3da227ccdac6014.png"},{"id":"7","parent":"1","name":"黄焖鸡米饭","flag":"ji","oslcount":"12","procount":"12","icon-raw":"http://wm.gou00.cn/uploads/2016-05-10/6bfb713f812bbc15d73f28f772b02522.png"},{"id":"10","parent":"1","name":"麻辣烫","flag":"malatang","oslcount":"13","procount":"13","icon-raw":"http://wm.gou00.cn/uploads/2016-05-10/f19e9bfc48c29f429e056c882f4d4e66.png"},{"id":"36","parent":"0","name":"生活超市","flag":"shi","oslcount":"0","procount":"0","icon-raw":"http://wm.gou00.cn/uploads/2016-05-10/23f964fb17ffcb165433f9debbeec3dd.png"},{"id":"17","parent":"0","name":"西餐披萨","flag":"xican","oslcount":"0","procount":"0","icon-raw":"http://wm.gou00.cn/uploads/2016-10-28/6e6a4915343380bf834bca6ac91f51c8.png"}],"count":{"total":15,"perpage":0,"pageall":1,"pagenow":1,"paged":false},"hash":{"md5":"946b028c6125644f840d11094ce0d96e"}}
     */

    private String status;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * list : [{"id":"1","parent":"0","name":"小吃快餐","flag":"ms","oslcount":"0","procount":"0","icon-raw":"http://wm.gou00.cn/uploads/2016-10-28/43a2ca125b107f97eaf4413eab464e47.png"},{"id":"2","parent":"1","name":"中式简餐","flag":"zhongshi","oslcount":"39","procount":"39","icon-raw":"http://wm.gou00.cn/uploads/2016-05-10/e9a06577162007a4895c967b3e4cb8f0.png"},{"id":"12","parent":"1","name":"炸鸡炸串","flag":"zhaji","oslcount":"15","procount":"15","icon-raw":"http://wm.gou00.cn/uploads/2016-10-28/da1d86813b15ba46787f9847d5bd2559.png"},{"id":"6","parent":"1","name":"面条米线","flag":"mian","oslcount":"44","procount":"44","icon-raw":"http://wm.gou00.cn/uploads/2016-10-28/c11d71af1f543cbc6f7bbdd8eb64165e.png"},{"id":"3","parent":"1","name":"地方小吃","flag":"difang","oslcount":"46","procount":"46","icon-raw":"http://wm.gou00.cn/uploads/2016-05-10/fe7d8d23238d8135cf7d617d87c07beb.png"},{"id":"27","parent":"0","name":"海鲜烧烤","flag":"shao","oslcount":"0","procount":"0","icon-raw":"http://wm.gou00.cn/uploads/2016-10-28/ddead83fcb7635d284bfd8d5e4a42aca.png"},{"id":"30","parent":"0","name":"水果蔬菜","flag":"shuiguo","oslcount":"0","procount":"0","icon-raw":"http://wm.gou00.cn/uploads/2016-05-10/7582591a555fa227113d592be3bcd7ec.png"},{"id":"23","parent":"0","name":"甜点饮品","flag":"tiandian","oslcount":"0","procount":"0","icon-raw":"http://wm.gou00.cn/uploads/2016-10-28/aac8f5695a2ba0f811a5b703defb8701.png"},{"id":"20","parent":"0","name":"日韩料理","flag":"ri","oslcount":"0","procount":"0","icon-raw":"http://wm.gou00.cn/uploads/2016-10-28/74eb7e8b8378ea221995c74a5de60798.png"},{"id":"14","parent":"0","name":"蛋糕鲜花","flag":"dangao","oslcount":"0","procount":"0","icon-raw":"http://wm.gou00.cn/uploads/2016-10-28/38f47c469d91b75c124aca5a33d10027.png"},{"id":"8","parent":"1","name":"卤味鸭脖","flag":"luwei","oslcount":"2","procount":"2","icon-raw":"http://wm.gou00.cn/uploads/2016-10-28/fb86d66086f793d3a3da227ccdac6014.png"},{"id":"7","parent":"1","name":"黄焖鸡米饭","flag":"ji","oslcount":"12","procount":"12","icon-raw":"http://wm.gou00.cn/uploads/2016-05-10/6bfb713f812bbc15d73f28f772b02522.png"},{"id":"10","parent":"1","name":"麻辣烫","flag":"malatang","oslcount":"13","procount":"13","icon-raw":"http://wm.gou00.cn/uploads/2016-05-10/f19e9bfc48c29f429e056c882f4d4e66.png"},{"id":"36","parent":"0","name":"生活超市","flag":"shi","oslcount":"0","procount":"0","icon-raw":"http://wm.gou00.cn/uploads/2016-05-10/23f964fb17ffcb165433f9debbeec3dd.png"},{"id":"17","parent":"0","name":"西餐披萨","flag":"xican","oslcount":"0","procount":"0","icon-raw":"http://wm.gou00.cn/uploads/2016-10-28/6e6a4915343380bf834bca6ac91f51c8.png"}]
         * count : {"total":15,"perpage":0,"pageall":1,"pagenow":1,"paged":false}
         * hash : {"md5":"946b028c6125644f840d11094ce0d96e"}
         */

        private CountBean count;
        private HashBean hash;
        private List<ListBean> list;

        public CountBean getCount() {
            return count;
        }

        public void setCount(CountBean count) {
            this.count = count;
        }

        public HashBean getHash() {
            return hash;
        }

        public void setHash(HashBean hash) {
            this.hash = hash;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class CountBean {
            /**
             * total : 15
             * perpage : 0
             * pageall : 1
             * pagenow : 1
             * paged : false
             */

            private int total;
            private int perpage;
            private int pageall;
            private int pagenow;
            private boolean paged;

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public int getPerpage() {
                return perpage;
            }

            public void setPerpage(int perpage) {
                this.perpage = perpage;
            }

            public int getPageall() {
                return pageall;
            }

            public void setPageall(int pageall) {
                this.pageall = pageall;
            }

            public int getPagenow() {
                return pagenow;
            }

            public void setPagenow(int pagenow) {
                this.pagenow = pagenow;
            }

            public boolean isPaged() {
                return paged;
            }

            public void setPaged(boolean paged) {
                this.paged = paged;
            }
        }

        public static class HashBean {
            /**
             * md5 : 946b028c6125644f840d11094ce0d96e
             */

            private String md5;

            public String getMd5() {
                return md5;
            }

            public void setMd5(String md5) {
                this.md5 = md5;
            }
        }

        public static class ListBean {
            /**
             * id : 1
             * parent : 0
             * name : 小吃快餐
             * flag : ms
             * oslcount : 0
             * procount : 0
             * icon-raw : http://wm.gou00.cn/uploads/2016-10-28/43a2ca125b107f97eaf4413eab464e47.png
             */

            private String id;
            private String parent;
            private String name;
            private String flag;
            private String oslcount;
            private String procount;
            private String iconraw;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getParent() {
                return parent;
            }

            public void setParent(String parent) {
                this.parent = parent;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getFlag() {
                return flag;
            }

            public void setFlag(String flag) {
                this.flag = flag;
            }

            public String getOslcount() {
                return oslcount;
            }

            public void setOslcount(String oslcount) {
                this.oslcount = oslcount;
            }

            public String getProcount() {
                return procount;
            }

            public void setProcount(String procount) {
                this.procount = procount;
            }

            public String getIconraw() {
                return iconraw;
            }

            public void setIconraw(String iconraw) {
                this.iconraw = iconraw;
            }
        }
    }
}
