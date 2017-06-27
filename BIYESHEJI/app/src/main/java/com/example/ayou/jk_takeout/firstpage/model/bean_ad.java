package com.example.ayou.jk_takeout.firstpage.model;

import java.util.List;

/**
 * Created by AYOU on 2017/4/24.
 */

public class bean_ad {

    /**
     * status : ok
     * data : {"config":{"time":40},"list":[{"image":"http://wm.gou00.cn/uploads/images/howd0api/hm.mM4393.gif","text":"火锅季","link":"http://wm.gou00.cn/m.php?mod=index&code=dishes&sid=333","pid":"","city":"2"},{"image":"http://wm.gou00.cn/uploads/images/howd0api/hm.CFGIte.gif","text":"美味火锅季","link":"http://wm.gou00.cn/m.php?mod=index&code=dishes&sid=347","pid":"","city":"2"},{"image":"http://wm.gou00.cn/uploads/images/howd0api/hm.3xntyx.gif","text":"所有恶作剧都出于爱","link":"http://wm.gou00.cn/m.php?mod=index&code=dishes&sid=361","pid":"","city":"2"}],"hash":{"md5":"0e3cd45050c9a967dcb9728359cd8830"}}
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
         * config : {"time":40}
         * list : [{"image":"http://wm.gou00.cn/uploads/images/howd0api/hm.mM4393.gif","text":"火锅季","link":"http://wm.gou00.cn/m.php?mod=index&code=dishes&sid=333","pid":"","city":"2"},{"image":"http://wm.gou00.cn/uploads/images/howd0api/hm.CFGIte.gif","text":"美味火锅季","link":"http://wm.gou00.cn/m.php?mod=index&code=dishes&sid=347","pid":"","city":"2"},{"image":"http://wm.gou00.cn/uploads/images/howd0api/hm.3xntyx.gif","text":"所有恶作剧都出于爱","link":"http://wm.gou00.cn/m.php?mod=index&code=dishes&sid=361","pid":"","city":"2"}]
         * hash : {"md5":"0e3cd45050c9a967dcb9728359cd8830"}
         */

        private ConfigBean config;
        private HashBean hash;
        private List<ListBean> list;

        public ConfigBean getConfig() {
            return config;
        }

        public void setConfig(ConfigBean config) {
            this.config = config;
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

        public static class ConfigBean {
            /**
             * time : 40
             */

            private int time;

            public int getTime() {
                return time;
            }

            public void setTime(int time) {
                this.time = time;
            }
        }

        public static class HashBean {
            /**
             * md5 : 0e3cd45050c9a967dcb9728359cd8830
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
             * image : http://wm.gou00.cn/uploads/images/howd0api/hm.mM4393.gif
             * text : 火锅季
             * link : http://wm.gou00.cn/m.php?mod=index&code=dishes&sid=333
             * pid :
             * city : 2
             */

            private String image;
            private String text;
            private String link;
            private String pid;
            private String city;

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }
        }
    }
}
