const base = {
    get() {
        return {
            url : "http://localhost:8080/equipmentzulinxitong/",
            name: "equipmentzulinxitong",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/equipmentzulinxitoog/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "露营设备租赁系统"
        } 
    }
}
export default base
