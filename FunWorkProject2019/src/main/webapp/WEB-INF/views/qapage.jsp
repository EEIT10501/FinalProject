<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.3.1.js"
	integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
	crossorigin="anonymous"></script>

<title>常見問題</title>
<style>
.card-text-size {
	font-size: 14px;
}

.footerbackground {
	background: #343a40;
	color: white;
}
</style>
</head>

<body>

	<%@ include file="/WEB-INF/views/includes/navbar.jsp"%>

	<div style="height: 4rem"></div>
	<div class="container-fluid">
		<div class="row m-3 justify-content-center">
			<div class="col-sm-4 text-center">
				<!--  第一段 -->
				<h3>常見問題</h3>
				<div class="accordion text-left" id="QAlist">
					<div class="card">
						<div class="card-header" id="Q03">
							<h2 class="mb-0">
								<button class="btn collapsed" type="button"
									data-toggle="collapse" data-target="#A03" aria-controls="A03"
									aria-expanded="false">一分鐘快速上手</button>
							</h2>
						</div>

						<div id="A03" class="collapse" aria-labelledby="Q03"
							data-parent="#QAlist">
							<div class="card-body">
								<p>第一步 - 快速登入 打開打工趣的網站，點擊登入按鈕。 進入到登入畫面，你可以選擇任何一種方式登入
									1.Google 帳號登入 2.Email 帳號登入 假如你還沒有帳號，請點選最下方的「註冊帳號」 創立一個屬於自己的帳號唷~
									第二步 - 填寫簡單履歷 登入後，就可以編輯自己的簡短履歷囉！ 1.點擊設定齒輪 2.點擊簡短履歷 3.開始撰寫你的履歷
									小技巧 使用好看的大頭貼 畫面乾淨、光線充足、背景不要凌亂 描述你的工作或是活動經驗，證明你的專長和能力！ 第三步 -
									瞭解方便功能 尋找打工的同時，可以搭配方便簡單的功能快速找到想要的打工。
									1.縣市分類－選擇你所在的縣市，或是選擇「自家」，在家裡輕鬆工作！ 2.距離排序－找出最靠近你的好工作。
									3.追蹤功能－標記出有興趣的工作，之後可以到「追蹤」中瀏覽。 4.已讀功能－閱讀過後會從深藍色變回淺藍色。
									5即時溝通功能－輕鬆和雇主在線上溝通。 第四步 - 一鍵應徵 遇到你喜歡的打工，就直接一鍵應徵吧！
									雇主就會立即收到你應徵的通知，接著也可以開始使用即時訊息與雇主溝通。 舉報功能
									如果發現這個職缺刊載有以下問題時，可以點選「舉報此工作」 1.八大行業 2.傳直銷產業 3.詐騙 4.薪水待遇與實係刊登不符
									5.薪資工時違反勞基法 6.雇主惡意拖欠薪水 7.逾期 8.其他（填寫你遇到的問題）</p>
							</div>
						</div>
					</div>
					<div class="card">
						<div class="card-header" id="Q01">
							<h2 class="mb-0">
								<button class="btn collapsed" type="button"
									data-toggle="collapse" data-target="#A01" aria-controls="A01"
									aria-expanded="false">可以修改信箱嗎？</button>
							</h2>
						</div>

						<div id="A01" class="collapse" aria-labelledby="Q01"
							data-parent="#QAlist">
							<div class="card-body">
								<p>1.進到 打工趣 網頁 2.循著「右上角大頭照 -> 帳號設定」進到 修改頁面 3.在 email 欄位輸入新的
									email ( 記得密碼也必須填寫 4.點擊設定 5.完成</p>
							</div>
						</div>
					</div>
					<div class="card">
						<div class="card-header" id="Q02">
							<h2 class="mb-0">
								<button class="btn collapsed" type="button"
									data-toggle="collapse" data-target="#A02" aria-controls="A02"
									aria-expanded="false">忘記密碼怎麼辦？</button>
							</h2>
						</div>

						<div id="A02" class="collapse" aria-labelledby="Q02"
							data-parent="#QAlist">
							<div class="card-body">
								<p>1.到 重置密碼 頁面填寫你註冊的 email 並點擊重設密碼 （系統會寄出重置密碼通知信）
									2.回到信箱，並點擊通知信的連結 3.填寫新的密碼並送出 4.完成</p>
							</div>
						</div>
					</div>

				</div>
			</div>
			<div class="col-sm-4 text-center">
				<!--  第二段 -->
				<h3>我是雇主</h3>
				<div class="accordion text-left" id="QAlist1">
					<div class="card">
						<div class="card-header" id="Q1001">
							<h2 class="mb-0">
								<button class="btn collapsed" type="button"
									data-toggle="collapse" data-target="#A1001"
									aria-expanded="true" aria-controls="A1001">如何刊登職缺？</button>
							</h2>
						</div>

						<div id="A1001" class="collapse" aria-labelledby="Q1001"
							data-parent="#QAlist1">
							<div class="card-body">
								<p>張貼工作職缺可依以下步驟進行： 1.至打工趣張貼工作列表
									2.點擊右上角「張貼工作」按鈕 3.詳細填寫職缺資訊後送出 4.回到張貼工作列表點擊「送審」按鈕 5.完成</p>
							</div>
						</div>
					</div>
					<div class="card">
						<div class="card-header" id="Q101">
							<h2 class="mb-0">
								<button class="btn collapsed" type="button"
									data-toggle="collapse" data-target="#A101" aria-expanded="true"
									aria-controls="A101">職缺審核時間大約多久？</button>
							</h2>
						</div>

						<div id="A101" class="collapse" aria-labelledby="Q101"
							data-parent="#QAlist1">
							<div class="card-body">
								<p>平日上班時段，職缺約半天內會通過審核，大部分是兩三個小時內可完成。</p>
								<p>平日非上班時段，職缺可能延至隔日才會通過審核。</p>
								<br>
								<p>小提醒：由於人手不足的關係，我們目前僅在平日上班時間審核，因此假日張貼的職缺很有可能會延至下週</p>
								<p>的上班日再審。</p>
								<p>若有造成你的不便，還請見諒。</p>
							</div>
						</div>
					</div>
					<div class="card">
						<div class="card-header" id="Q102">
							<h2 class="mb-0">
								<button class="btn collapsed" type="button"
									data-toggle="collapse" data-target="#A102"
									aria-expanded="false" aria-controls="A102">如何建立公司單位？</button>
							</h2>
						</div>
						<div id="A102" class="collapse" aria-labelledby="Q102"
							data-parent="#QAlist1">
							<div class="card-body">
								<p>新增公司單位可以依照下列步驟進行： 1.進入編輯打工的頁面 2.找到單位欄位，點擊旁邊的「馬上建立」連結
									3.填寫正確單位資訊並附上身分驗證文件 *1 4.送出 5.完成
									成功建立後，回到編輯打工頁面，就可以在單位欄位的選單中，看到剛剛建立的公司。 * 身分驗證文件共有兩種類型，可擇一上傳。
									上傳前請先確認是否符合以下的規範： 。公司名片：名片必須能夠清楚看到 1. 個人名字 2. 公司名稱 3. 公司統編
									。公司營業登記文件 (核准函或登記表)：文件必須能夠清楚看到 1. 公司名稱 2. 公司統編</p>
							</div>
						</div>
					</div>
					<div class="card">
						<div class="card-header" id="Q103">
							<h2 class="mb-0">
								<button class="btn collapsed" type="button"
									data-toggle="collapse" data-target="#A103"
									aria-expanded="false" aria-controls="A103">應徵沒有收到回覆怎麼辦？</button>
							</h2>
						</div>
						<div id="A103" class="collapse" aria-labelledby="Q103"
							data-parent="#QAlist1">
							<div class="card-body">
								<p>應徵沒有收到回覆先不用緊張，部分雇主可能因為比較忙碌，沒有辦法回覆每一個應徵者，只會通知已錄取
									的人選，要如何確定你是不是那個人選呢？這個時候你有幾個做法： 1.可以多傳即時訊息取得聯繫，善用平台好功能。
									2.可以同時間投其他工作的履歷，多方增加機會。 3.要謹慎使用殺手鐧，電話詢問雇主，表達出你的積極性，但不要嚇到雇主。
									祝全天下的打工趣使用者都能順利找到合適的工作！</p>
							</div>
						</div>
					</div>
					<div class="card">
						<div class="card-header" id="Q104">
							<h2 class="mb-0">
								<button class="btn collapsed" type="button"
									data-toggle="collapse" data-target="#A104"
									aria-expanded="false" aria-controls="A104">如何得知職缺被駁回的原因？</button>
							</h2>
						</div>

						<div id="A104" class="collapse" aria-labelledby="Q104"
							data-parent="#QAlist1">
							<div class="card-body">
								<p>當你看到職缺被駁回時先不用懊惱，也不用緊張。 造成職缺審核未過的原因有很多種，可以在以下幾個地方得知「駁回原因」：

									1.帳號的註冊信箱 2. 網站 > 工作管理 > 未發佈 > 點擊任一職缺的 “失敗原因” 按鈕 3.App > 已張貼 >
									未發佈 > 點擊任一職缺就可以看到 如果對於駁回內容有疑問，歡迎來信與我們聯絡support@Funworkapp.com。</p>
							</div>
						</div>
					</div>
					<div class="card">
						<div class="card-header" id="Q105">
							<h2 class="mb-0">
								<button class="btn collapsed" type="button"
									data-toggle="collapse" data-target="#A105" aria-controls="A105"
									aria-expanded="false">如何退出公司單位？</button>
							</h2>
						</div>

						<div id="A105" class="collapse" aria-labelledby="Q105"
							data-parent="#QAlist1">
							<div class="card-body">
								<p>假使已經離職，或不再需要使用公司單位來張貼職缺，可以參考以下步驟退出公司單位： 1.點擊右上角帳號
									2.點擊「公司管理列表」 3.點擊任一公司的退出按鈕</p>
							</div>
						</div>
					</div>
					<div class="card">
						<div class="card-header" id="Q106">
							<h2 class="mb-0">
								<button class="btn collapsed" type="button"
									data-toggle="collapse" data-target="#A106" aria-controls="A106"
									aria-expanded="false">公司審核失敗怎麼辦？</button>
							</h2>
						</div>

						<div id="A106" class="collapse" aria-labelledby="Q106"
							data-parent="#QAlist1">
							<div class="card-body">
								<p>公司審核失敗的處理步驟很簡單，只要進入公司單位的編輯頁面，並上傳合格的身分驗證文件後，就可以再 次送審職缺。
									進入公司單位的路徑如下： App 1.點擊左上角「設定」 2.點擊「我的公司」 3.點擊任一駁回公司的編輯按鈕 網頁版
									1.點擊右上角雇主後台 2.點擊「公司管理列表」 3.點擊任一駁回公司的編輯按鈕 P.S.
									更新完驗證文件，請直接送審職缺，不需等候公司審核通過。</p>
							</div>
						</div>
					</div>
					<div class="card">
						<div class="card-header" id="Q107">
							<h2 class="mb-0">
								<button class="btn collapsed" type="button"
									data-toggle="collapse" data-target="#A107" aria-controls="A107"
									aria-expanded="false">如何使用即時訊息與應徵者聯繫？</button>
							</h2>
						</div>

						<div id="A107" class="collapse" aria-labelledby="Q107"
							data-parent="#QAlist1">
							<div class="card-body">
								<p>即時訊息最棒的地方是能夠讓你和應徵者即時溝通、即時確認，當你想瞭解應徵者的經驗、所學專長、或是
									安排打工班次都能夠為你省下不少時間。 操作方式分別如下： 1.進入 已張貼頁面 2.點擊任意職缺上的「聯絡應徵者」按鈕
									3.點擊任意應徵者上的「聯絡應徵者」按鈕 4.輸入訊息並按下 Enter</p>
							</div>
						</div>
					</div>
					<div class="card">
						<div class="card-header" id="Q108">
							<h2 class="mb-0">
								<button class="btn collapsed" type="button"
									data-toggle="collapse" data-target="#A108" aria-controls="A108"
									aria-expanded="false">如何修改單位資訊？</button>
							</h2>
						</div>

						<div id="A108" class="collapse" aria-labelledby="Q108"
							data-parent="#QAlist1">
							<div class="card-body">
								<p>提供下列資訊至打工趣客服信箱，待人員確認無誤後，就會立即幫忙修正： 。公司統編 。公司名稱 。要修改的內容

									客服信箱：support@Funworkapp.com</p>
							</div>
						</div>
					</div>
					<div class="card">
						<div class="card-header" id="Q109">
							<h2 class="mb-0">
								<button class="btn collapsed" type="button"
									data-toggle="collapse" data-target="#A109" aria-controls="A109"
									aria-expanded="false">職缺已經額滿，不想讓其他人看到職缺怎麼辦？</button>
							</h2>
						</div>

						<div id="A109" class="collapse" aria-labelledby="Q109"
							data-parent="#QAlist1">
							<div class="card-body">
								<p>職缺已額滿，不想再讓其他使用者看到職缺資訊，甚至應徵，可以將職缺設定為「已額滿」，你的職缺就不 會出現在網站或者
									app 中，僅有那些已應徵的應徵者看的到。 設定額滿的步驟如下：1.至打工趣網站頁面 2.點擊右上角「張貼工作」張貼工作列表
									3.選擇任一發佈中職缺，並點選「設為已額滿」</p>
							</div>
						</div>
					</div>
				</div>

			</div>
			<div class="col-sm-4 text-center">
				<!-- 第三段-->
				<h3>我要打工</h3>
				<div class="accordion text-left" id="QAlist2">
					<div class="card">
						<div class="card-header" id="Q201">
							<h2 class="mb-0">
								<button class="btn collapsed" type="button"
									data-toggle="collapse" data-target="#A201"
									aria-expanded="false" aria-controls="A201">應徵沒有收到回覆怎麼辦？</button>
							</h2>
						</div>
						<div id="A201" class="collapse" aria-labelledby="Q201"
							data-parent="#QAlist2">
							<div class="card-body">
								<p>應徵沒有收到回覆先不用緊張，部分雇主可能因為比較忙碌，沒有辦法回覆每一個應徵者，只會通知已錄取
									的人選，要如何確定你是不是那個人選呢？這個時候你有幾個做法： 1.可以多傳即時訊息取得聯繫，善用平台好功能。
									2.可以同時間投其他工作的履歷，多方增加機會。 3.要謹慎使用殺手鐧，電話詢問雇主，表達出你的積極性，但不要嚇到雇主。
									祝全天下的打工趣使用者都能順利找到合適的工作！</p>
							</div>
						</div>
					</div>
					<div class="card">
						<div class="card-header" id="Q202">
							<h2 class="mb-0">
								<button class="btn collapsed" type="button"
									data-toggle="collapse" data-target="#A202"
									aria-expanded="false" aria-controls="A202">如何不漏接雇主的回信？</button>
							</h2>
						</div>
						<div id="A202" class="collapse" aria-labelledby="Q202"
							data-parent="#QAlist2">
							<div class="card-body">
								<p>使用一鍵應徵時 。雇主回信若使用打工趣的聊天室，app 將會有新訊息通知，同時時會寄到用戶註冊的信箱。
									。雇主有可能經由履歷提供的聯絡方式（例如：電話號碼、通訊軟體）來連絡你。 使用email應徵時 。定時確認信箱的回信。
									。雇主有可能經由履歷提供的聯絡方式（例如：電話號碼、通訊軟體）來連絡你。 使用電話應徵時 。請留意雇主提到的通知錄取方式。
									。自行與雇主詢問通知錄取的方式與時限。</p>
							</div>
						</div>
					</div>
					<div class="card">
						<div class="card-header" id="Q203">
							<h2 class="mb-0">
								<button class="btn collapsed" type="button"
									data-toggle="collapse" data-target="#A203"
									aria-expanded="false" aria-controls="A203">如何使用即時訊息與雇主聯繫？</button>
							</h2>
						</div>
						<div id="A203" class="collapse" aria-labelledby="Q203"
							data-parent="#QAlist2">
							<div class="card-body">
								<p>1.進入 已應徵頁面 2.點擊任意職缺上的「聯絡雇主」按鈕 3.輸入訊息並按下 Enter</p>
							</div>
						</div>
					</div>
					<div class="card">
						<div class="card-header" id="Q204">
							<h2 class="mb-0">
								<button class="btn collapsed" type="button"
									data-toggle="collapse" data-target="#A204"
									aria-expanded="false" aria-controls="A204">如何應徵打工？</button>
							</h2>
						</div>

						<div id="A204" class="collapse" aria-labelledby="Q204"
							data-parent="#QAlist2">
							<div class="card-body">
								<p>一鍵應徵 趣打工最方便的應徵方式，只要將自己的簡短履歷寫好後 ( 30 秒完成 )，點擊「應徵」按鈕，雇主就會看到
									你應徵的通知和個人檔案。 Email 應徵 使用自己的信箱寄履歷給對方。 *有些應徵會預先將履歷存成草稿，加快寄送履歷的速度。
									電話應徵 直接撥電話給對方。 *雖然打電話很快，但在一般情況下，除非雇主有表明直接電話應徵，否則直接撥電話過去可是會造成雇主
									困擾的，使用上還是需要小心一些。</p>
							</div>
						</div>
					</div>

					<div class="card">
						<div class="card-header" id="Q205">
							<h2 class="mb-0">
								<button class="btn collapsed" type="button"
									data-toggle="collapse" data-target="#A205" aria-controls="A205"
									aria-expanded="false">履歷怎麼寫？提高錄取率！</button>
							</h2>
						</div>

						<div id="A205" class="collapse" aria-labelledby="Q205"
							data-parent="#QAlist2">
							<div class="card-body">
								<p>我要找到一份工作！ 簡潔有力的簡歷，是幫助你打中雇主心頭的關鍵！來看看以下的建議： 挑選一張好看的大頭貼
									1.畫面要乾淨清楚 2.光線要充足 3.背景不要過度雜亂 留下美好的第一印象吧！ 介紹你的專長及特色
									單純描述不夠力，寫出經驗來證明！ 1.有工作經驗的話，寫下幾個令人印象深刻的工作，並簡單描述你的經驗吧！
									2.沒有工作經驗的話，寫下你曾經參加過的活動或是做過的事情，證明你的能力吧！
									3.寫下一段簡短的自我介紹，凸顯你的個人特質跟專長吧！</p>
							</div>
						</div>
					</div>
					<div class="card">
						<div class="card-header" id="Q206">
							<h2 class="mb-0">
								<button class="btn collapsed" type="button"
									data-toggle="collapse" data-target="#A206"
									aria-expanded="false" aria-controls="A206">打工趣安全嗎？</button>
							</h2>
						</div>
						<div id="A206" class="collapse" aria-labelledby="Q206"
							data-parent="#QAlist2">
							<div class="card-body">
								<p>為了提供一個安全找打工的地方，打工趣的職缺為「人工審核」機制，審核過程大致為以下步驟：
									1.使用者第一次刊登公司職缺時，都需要先建立公司單位，並提供相關的身分驗證資料，例如公司名 片、政府營業登記文件
									(核准函或登記表) 2.打工趣會依據公示網站以及身分驗證資料查驗單位是否屬實 3.職缺內容都必須符合勞基法的規定 p.s.
									個人職缺不需建立公司單位 為了維護受雇者的權益，每個職缺都會經過以上步驟，請大家安心笑納 關於個人簡歷
									為了確保使用者資訊的安全，在個人簡歷的部分： 1.打工趣不會主動提供任何使用者資料給雇主
									2.當使用者按下應徵鍵後，雇主才會收到通知，並可以看到你的檔案 (未按下應徵鍵，雇主是完全看不 到的)</p>
							</div>
						</div>
					</div>
				</div>

			</div>
			<!-- 			<div class="col-sm-2">預留區塊</div> -->
		</div>
	</div>
	<div class="container-fluid">
		<div class="row no-gutter footerbackground">
			<div class="col text-center">Copyright© 2019 趣打工 All rights
				reserved.</div>
		</div>
	</div>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>