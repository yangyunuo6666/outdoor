const base = {
    get() {
        return {
            url : "https://localhost:8443/equipmentzulinxitong/",
            name: "equipmentzulinxitong",
            // 退出到首页链接
            indexUrl: 'https://localhost:8443/equipmentzulinxitoog/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "露营设备租赁系统"
        } 
    }
}
export default base
