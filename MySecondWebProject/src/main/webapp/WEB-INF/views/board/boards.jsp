<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>

header.masthead {
   display: none;
}   
.row{
	height : 800px;
}
tr:hover{
	cursor : pointer;
}

</style>

<br/><br/> 
 
   <jsp:include page="../include/header.jsp"/>

   <!-- Begin Page Content -->
   <div class="container">
      <div class="row">
         <div class="col-lg-1">
         </div>
         <div class="col-lg-10">
            <div class="panel-body">
            <h2 class="page-header"><span style="color: #52b1ff;">KH</span> 자유 게시판
               <a href="enrollForm.board" class="btn float-right" style="background-color: #52b1ff; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">글쓰기</a>
            </h2>
               <table class="table table-bordered table-hover">
                  <thead>
	                  <tr style="background-color: #52b1ff; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">
	                     <th width="100">번호</th>
	                     <th width="150">카테고리</th>
	                     <th width="150">작성자</th>
	                     <th width="450">제목</th>
	                     <th width="200">작성일</th>
	                     <th width="100">조회수</th>
	                  </tr>
                  </thead>
                  
                  <tbody>
	                <c:forEach items="${ map.boards }" var="board">
	                    <tr style="color: #52b1ff;"
	                        class="board"
	                        id="${ board.boardNo }"
	                        onclick="goTo(${ board.boardNo })">
	                        <td>
	                        	${ board.boardNo }
	                        </td>
	                        <td>
	                        	${ board.boardCategory }
	                        </td>
	                        <td>
	                        	${ board.boardWriter }
	                        </td>
	                        <td style="color: #52d6ffcc;">
	                         	${ board.boardTitle }&nbsp;
	                        </td>
	                        <td>
	                        	${ board.createDate }
	                        </td>
	                        <td>
	                        	${ board.count }
	                        </td>
	                    </tr>    
	        		</c:forEach>
                  </tbody>
               </table>               
            </div>            
         </div>
       </div>
       
         <script>
         	const goTo = (no) => {
				// console.log(e);
				location.href = `board?boardNo=\${no}`;
         	}
         </script>
      
         <div class="paging-area" align="center" >
        		
        		<c:if test="${ map.page > 1 }">
		        	<button 
		       		class="btn btn-outline-primary" style="color:#52b1ff;"
		       		onclick="location.href='boards?page=${ map.page - 1 }'">이전
		       		</button>
	       		</c:if>
        
				<c:forEach begin="${ map.startBtn }" end="${ map.endBtn }" var="i">
	                <button 
	                class="btn btn-outline-primary" style="color:#52b1ff;"
	                onclick="location.href='boards?page=${i}'">${ i }
	                </button>
                </c:forEach>
                
                <c:if test="${ map.page < map.maxPage }">
		        	<button 
		       		class="btn btn-outline-primary" style="color:#52b1ff;"
		       		onclick="location.href='boards?page=${ map.page + 1 }'">다음
		       		</button>
        		</c:if>
        </div>
      
      
      
      
   </div>
   
     
     
   <jsp:include page="../include/footer.jsp"/>