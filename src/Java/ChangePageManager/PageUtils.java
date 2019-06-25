package ChangePageManager;

public class PageUtils {
    private Integer currentPage;    //当前页码
    private Integer prePage;		//上一页页码
    private Integer nextPage;		//下一页页码
    private Integer lastPage;	    //最后一页页码
    private Integer pageSize;	    //每页显示多少条数据
    private Integer counter;	    //你的数据库里数据总数

    //由当前页码、pagesize、counter算出其他的页码
    public PageUtils(String currentPage, Integer pageSize, Integer counter) {
        this.pageSize = pageSize;
        this.counter = counter;
        if(null==currentPage) {
            currentPage="1";     //默认当前页码为1
        }
        this.currentPage=Integer.parseInt(currentPage);   //由于current是由string类型传进来的要给它转成int类型才能运算
        lastPage=this.counter/this.pageSize;   //计算最后一页
        if(this.counter%this.pageSize !=0) {
            lastPage+=1;
        }
        nextPage=this.currentPage==lastPage?this.currentPage:this.currentPage+1;  //当前页是否为最后一页，是下一页=当前页，否下一页=当前页+1
        prePage=this.currentPage==1?this.currentPage:this.currentPage-1;          //当前页是否为首页
    }
    public Integer getCurrentPage() {
        return currentPage;
    }
    public PageUtils(Integer currentPage, Integer prePage, Integer nextPage, Integer lastPage, Integer pageSize) {
        super();
        this.currentPage = currentPage;
        this.prePage = prePage;
        this.nextPage = nextPage;
        this.lastPage = lastPage;
        this.pageSize = pageSize;
    }
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
    public Integer getPrePage() {
        return prePage;
    }
    public void setPrePage(Integer prePage) {
        this.prePage = prePage;
    }
    public Integer getNextPage() {
        return nextPage;
    }
    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }
    public Integer getLastPage() {
        return lastPage;
    }
    public void setLastPage(Integer lastPage) {
        this.lastPage = lastPage;
    }
    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    public Integer getCounter() {
        return counter;
    }
    public void setCounter(Integer counter) {
        this.counter = counter;
    }
}
