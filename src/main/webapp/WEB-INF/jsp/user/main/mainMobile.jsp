<div id="wrap" class="main">
    <header>
    <div class="headerWrap">
      <a href="/user/mobile/main/mainMobile">
        <h1><span>KETEP</span></h1>
		<h2>ESS산업 Job-Navi</h2>
      </a>

      <button class="menu"></button>
    </div>
  </header>


    <div class="swiper-container swiper-content">
		<img src="/assets/mobile/images/main01.png" alt="메인이미지" />
		<div class="mainimg_txt">
			<p class="inwrapTitle"><span class="blue_font">ESS산업</span><br />일자리 내비게이터</p>
            <p>ESS산업  분야로의 취업 및 경력<br />개발을 위해 ESS산업의 종합적인<br />일자리 정보를 제공하는 플랫폼<br /></p>
		</div>
    </div>

    <script>
      var swiper = new Swiper('.swiper-content', {
        spaceBetween: 0,
        slidesPerView: 'auto',
        freeMode: false,
        pagination: {
          el: '.swiper-pagination',
        },
        navigation: {
          nextEl: ".swiper-button-next",
          prevEl: ".swiper-button-prev"
        },
      });
    </script>

    <section>
      <div class="m-wrap">
        <h2>내비게이터 소개</h2>
        <p class="h2Cont">ESS산업 일자리 내비게이터는 국내 ESS산업 실정과 일자리 구조에 최적화된 ‘ESS산업 직무역량 모델’을 구축합니다.</p>
      </div>
    </section>

    <section>
      <div class="m-wrap h3Cont num01">
        <h3>목적</h3>
        <p>ESS 산업 관련 직업 선택 및<br />경로 설계를 위한 종합적인 일자리 정보 제공</p>
    </section>

    <section>
      <div class="m-wrap h3Cont num02">
        <h3>대상</h3>
        <p class="h3">ESS 산업 분야에 종사하고자 하는 고등학생, 대학생, 취업준비생, 직장인 등</p>
      </div>
    </section>

    <section>
      <div class="m-wrap h3Cont num03">
        <h3>내용</h3>
        <p>ESS 산업 일자리 내비게이터, ESS산업 직업사전, ESS산업 일자리 지도 제공</p>
      </div>
    </section>

    <section>
      <div class="m-wrap h3Cont last">
        <h3>정보</h3>
        <ul>
          <li>ESS산업 생태계별, 직종별 일자리 현황</li>
          <li>ESS산업 일자리별 필요학력/전공, 자격증, 직무역량, 연봉 등의 정보 제공</li>
          <li>ESS산업 일자리별 관련 대표기업 정보 제공</li>
          <li>지역별 ESS 일자리 및 기업 분포 현황</li>
        </ul>
      </div>
    </section>

    <section class="model">
      <div class="m-wrap">
        <h2>직무역량 모델</h2>
        <p class="h2Cont last">ESS산업 일자리 내비게이터는 국내 ESS산업 실정과 일자리 구조에 최적화된 ‘ESS산업 직무역량 모델’을 구축합니다.</p>
        <ul class="pyramid">
          <li>
            <a class="open" href="#tab06">관리역량</a>
            <img src="/assets/mobile/images/pyra_bg01.png" alt="">
          </li>
          <li>
            <a class="open" href="#tab05">자격역량</a>
            <img src="/assets/mobile/images/pyra_bg02.png" alt="">
          </li>
          <li>
            <a class="open" href="#tab04">산업역량</a>
            <img src="/assets/mobile/images/pyra_bg03.png" alt="">
          </li>
          <li>
            <a class="open" href="#tab03">기초업무역량</a>
            <img src="/assets/mobile/images/pyra_bg04.png" alt="">
          </li>
          <li>
            <a class="open" href="#tab02">학업역량</a>
            <img src="/assets/mobile/images/pyra_bg05.png" alt="">
          </li>
          <li>
            <a class="open" href="#tab01">개인역량</a>
            <img src="/assets/mobile/images/pyra_bg06.png" alt="">
          </li>
        </ul>
        <script>
          $(function() {
            $('.open').on('click', function() {
              $('.mask').css({'display':'block'});
              $('#model').css({'display':'block'});
            });
            $('.btn_close').on('click', function() {
              $('.mask').css({'display':'none'});
              $('#model').css({'display':'none'});
            });
          });
          
          $(function () {
            var anchor = $('.pyramid > li > a'),
                panel = $('.js-tabcontent');
        
            anchor.on('click', function(e) {
              e.preventDefault();
              $target = $(this).attr('href');
              
              $('.tablist > li > a').attr('aria-selected','false');
        
              panel.attr('aria-hidden','true');
              $($target + '.js-tabcontent').attr('aria-hidden','false');
            });
        
            $('a[href="#tab06"]').click (function () {
              $('.tablist > li:nth-child(6) > a').attr('aria-selected','true');
            });
            $('a[href="#tab05"]').click (function () {
              $('.tablist > li:nth-child(5) > a').attr('aria-selected','true');
            });
            $('a[href="#tab04"]').click (function () {
              $('.tablist > li:nth-child(4) > a').attr('aria-selected','true');
            });
            $('a[href="#tab03"]').click (function () {
              $('.tablist > li:nth-child(3) > a').attr('aria-selected','true');
            });
            $('a[href="#tab02"]').click (function () {
              $('.tablist > li:nth-child(2) > a').attr('aria-selected','true');
            });
            $('a[href="#tab01"]').click (function () {
              $('.tablist > li:nth-child(1) > a').attr('aria-selected','true');
            });
          });
        </script>
      </div>
    </section>

    <section>
      <ul class="partner">
        <li>
          <a href=""><img src="/assets/mobile/images/partner/biz_info.png" alt=""></a>
        </li>
        <li>
          <a href=""><img src="/assets/mobile/images/partner/ris_pec.png" alt=""></a></li>
        <li>
          <a href=""><img src="/assets/mobile/images/partner/simpan.png" alt=""></a></li>
        <li>
          <a href=""><img src="/assets/mobile/images/partner/biz_info.png" alt=""></a></li>
        <li>
          <a href=""><img src="/assets/mobile/images/partner/ris_pec.png" alt=""></a></li>
        <li>
          <a href=""><img src="/assets/mobile/images/partner/korea_gov.png" alt=""></a></li>
      </ul>
    </section>
  </div>

  <div class="mask"></div>

  <div id="model" class="popup">
    <div class="wrap">
      <div class="header">
        <h1>직무역량 모델</h1>
        <span class="btn_close"></span>
      </div>
      
      <div class="tabs">
        <ul class="tablist" data-hx="h2">
          <li class="tablist__item">
            <a href="#tab01" class="tablist__link" aria-selected=false>개인역량</a>
          </li>
          <li class="tablist__item">
            <a href="#tab02" class="tablist__link ">학업역량</a>
          </li>
          <li class="tablist__item">
            <a href="#tab03" class="tablist__link">업무역량</a>
          </li>
          <li class="tablist__item">
            <a href="#tab04" class="tablist__link">산업역량</a>
          </li>
          <li class="tablist__item">
            <a href="#tab05" class="tablist__link">자격역량</a>
          </li>
          <li class="tablist__item">
            <a href="#tab06" class="tablist__link">관리역량</a>
          </li>

        </ul>
        <div id="tab01" class="js-tabcontent">
          <div class="tabWrap">
            <h3 class="modelTitle">TIER.1<br />개인역량</h3>
            <p class="summary">개인 역량은 타고난 역량으로 기본적인 성격이나 태도를 의미</p>

            <dl class="capabilities">
              <dt>윤리의식</dt>
              <dd>도덕과 양심에 따라 행동하며 개인적인 이익보다는 조직의 가치와 기준을 수용하는 태도
              </dd>
              <dt>주인의식</dt>
              <dd>경영자의 입장에서 경영환경을 이해하고 전략적 방향을 판단하거나 수용하며, 자발적으로 업무상의 문제점을 발견/분석하여 해결방안을 적극 제시하고 실행하는 능력
              </dd>
              <dt>성실성</dt>
              <dd>모든 일에 성실·정직하여 남에게 신뢰감을 주고, 꼼꼼하며 정확하게 일을 수행하는 태도
              </dd>
              <dt>진취성</dt>
              <dd>적극적 사고와 자신감으로 새로운 기회를 찾아내어 주도적으로 일을 추진하고, 더 높은 성과목표를 세워 열정적으로 추진하려는 태도
              </dd>
              <dt>창의성</dt>
              <dd>사물의 현상과 본질을 이해하고 발상의 전환 및 적용의 변환을 통해 다양한 아이디어를 제시하여 새로운 가치를 창출하는 능력
              </dd>
              <dt>정확성</dt>
              <dd>업무 수행 시 업무 지침이나 업무 지시 등의 내용에 따라 세부적인 부분까지 꼼꼼하고 정확하게 확인하고 수행하고 관리하는 능력
              </dd>
              <dt>의사소통</dt>
              <dd>상대방을 경청하여 정확히 이해하고, 자신의 의사를 명확히 전달 및 이해시키며, 필요 시 상대방의 협조를 이끌어 내는 능력
              </dd>
              <dt>자기개발</dt>
              <dd>스스로 꾸준히 학습하고 개발하여 보다 나은 성과와 발전을 이루려는 태도
              </dd>
            </dl>
          </div>
        </div>
        <div id="tab02" class="js-tabcontent">
          <div class="tabWrap">
            <h3 class="modelTitle">TIER.2<br />학업역량</h3>
            <p class="summary"></p>

            <dl class="capabilities">
              <dt>듣기·읽기·쓰기·말하기</dt>
              <dd>다른 사람들이 말하는 것을 집중해서 듣고 상대방이 말하려는 요점을 이해하거나 적절한 질문을 할 수 있는 능력
              </dd>
              <dt>컴퓨터 활용</dt>
              <dd>컴퓨터, IT를 활용하여 일을 효율적으로 처리하는 능력
              </dd>
			  <dt>영어</dt>
              <dd>영어를 읽고, 쓰고, 듣고 말하는데 필요한 지식
              </dd>
			  <dt>수학</dt>
              <dd>연산, 대수학, 통계, 기하학의 계산 및 응용에 관한 지식
              </dd>
			  <dt>공학</dt>
              <dd>다양한 물건을 만들고 설계하거나 서비스를 제공하기 위해 필요한 공학적인 원리, 기법, 장비 등을 실제로 적용시키는 지식
              </dd>
			  <dt>물리</dt>
              <dd>공기, 물, 빛, 열, 전기이론 및 자연현상에 관한 지식
              </dd>
			  <dt>건축/설계</dt>
              <dd>집, 빌딩, 혹은 도로를 만들고 수리하기 위해 필요한 지식
              </dd>
			  <dt>경제/경영/회계</dt>
              <dd>돈의 흐름, 은행업무, 그리고 재무자료의 보고와 분석과 같은 경제 및 회계원리에 관한 지식
              </dd>
			  <dt>법/행정</dt>
              <dd>법의 기초 원리와 실정법의 해석에 관한 이론과 실제, 또는 정책, 인사, 재무, 조직, 지방행정 등 행정학에 대한 전반적인 지식
              </dd>
            </dl>
          </div>
        </div>
        <div id="tab03" class="js-tabcontent">
          <div class="tabWrap">
            <h3 class="modelTitle">TIER.3<br />기초업무역량</h3>
            <p class="summary"></p>

            <dl class="capabilities">
              <dt>정보수집 및 활용 능력</dt>
              <dd>필요한 정보를 지속적으로 수집하고 가공 및 관리하여 업무에 활용할 수 있는 능력
              </dd>
              <dt>프레젠테이션 능력</dt>
              <dd>자신의 의견이나 제안을 효과적으로 발표하고 상대방의 동의와 행동을 이끌어내는 능력
              </dd>
			  <dt>분석력</dt>
              <dd>문제나 상황의 여러 부분을 논리적으로 생각하고, 체계적으로 업무를 처리하는 능력
              </dd>
			  <dt>설득력</dt>
              <dd>자신의 입장과 견해를 논리적이고 설득력있는 방식으로 제시하여 타인들로부터 필요한 지지와 지원을 끌어낼 수 있으며, 효과적인 지지를 얻어내는데 필요한 정보와 데이터를 효율적으로 이용할 수 있는 능력
              </dd>
			  <dt>리더십</dt>
              <dd>공동의 목표달성을 위해 구성원들을 동기부여시키고, 지도 및 지원하며 이끌어가는 능력
              </dd>
			  <dt>팀워크</dt>
              <dd>팀의 일원임을 인식하고 팀 목표달성을 위해 동료들과 적극 협력하고 함께 일하려는 태도
              </dd>
			  <dt>조직 적응 능력</dt>
              <dd>자신이 속한 조직 및 구성원들과 융화할 수 있는 능력과 주변의 급격한 환경 변화에도 적극 수용하는 융통성
              </dd>
			  <dt>의사 결정 능력</dt>
              <dd>문제를 판단하고 쓸 수 있는 자원을 선택하여 올바른 결정을 적시에 내릴 수 있는 능력
              </dd>
			  <dt>응급상황 및 갈등 해결 능력</dt>
              <dd>예측하지 못한 사고에 대해 합리적으로 대처하거나, 갈등 상황의 본질을 정확히 파악하여 최선의 해결책을 신속히 찾아내어 실행할 수 있는 능력
              </dd>
			  <dt>업무 계획 능력</dt>
              <dd>업무 수행 전 활동계획을 세우고 필요한 인적·물적자원을 구성할 수 있는 능력
              </dd>
			  <dt>업무 이해 능력</dt>
              <dd>회사와 업계의 특성, 성공요소를 파악하고 자신의 역량과 연계하여 이해하는 능력
              </dd>
			  <dt>업무 해결 능력</dt>
              <dd>진행상황에 따라 목표달성을 위한 접근방식을 융통성있게 변경하여 업무를 해결할 수 있는 능력
              </dd>
			  <dt>업무 관리 능력</dt>
              <dd>시간과 자원을 효율적으로 관리하며, 우선 순위에 따라 다양한 업무 요구사항을 효율적으로 처리하는 능력
              </dd>
            </dl>
          </div>
        </div>
        <div id="tab04" class="js-tabcontent">
          <div class="tabWrap">
            <h3 class="modelTitle">TIER.4<br />산업역량</h3>
            <p class="summary"></p>

            <dl class="capabilities">
              <dt>에너지 소재/부품/장비</dt>
              <dd>에너지 생산의 원천이 되는 소재 및 부품에 대한 특성 및 공정과 이를 생산하기 위해 필요한 관련 장비에 대한 기본적인 이해
              </dd>
			  <dt>에너지 발전</dt>
              <dd>에너지 생산에 대한 기본 지식부터 에너지 발전시스템 및 관련 설비의 설계, 설치, 운영 및 유지관리에 대한 대한 전반적인 이해
              </dd>
			  <dt>에너지 효율</dt>
              <dd>화석연료에 대한 의존도 절감과 에너지 절약 및 에너지 효율 제고에 대한 기본적인 이해
              </dd>
			  <dt>신재생에너지</dt>
              <dd>ESS, 풍력 등 신재생에너지에 대한 기본 개념과 에너지가 생산되는 원리에 대한 이해
              </dd>
			  <dt>법/제도/정책</dt>
              <dd>에너지산업에 영향을 미치는 법/제도/정책, 규정 등에 대해 인지하고 준수하는 능력
              </dd>
			  <dt>안전/보안</dt>
              <dd>안전하고 건강한 에너지산업의 작업환경을 보장하는데 필요한 절차를 준수 및 관리하고, 도출된 데이터에 대한 보안의식을 보유
              </dd>
			  <dt>회계/금융/경제</dt>
              <dd>에너지산업과 관련된 회계, 금융, 경제에 대한 기본 이해
              </dd>
			  <dt>교육/마케팅/컨설팅</dt>
              <dd>에너지산업의 인력 교육 및 발전사업 관련 컨설팅, 영업/마케팅에 대한 이해
              </dd>
              <!--<dt>주인의식</dt>
              <dd>2
              </dd>-->
            </dl>
          </div>
        </div>
        <div id="tab05" class="js-tabcontent">
          <div class="tabWrap">
            <h3 class="modelTitle">TIER.5<br />자격역량</h3>
            <p class="summary"></p>

            <dl class="capabilities">
              <dt>에너지/환경</dt>
              <dd>에너지, 환경(폐기물)과 관련된 자격
              </dd>
			  <dt>전기/전자</dt>
              <dd>전기, 전자와 관련된 자격
              </dd>
			  <dt>건축/토목</dt>
              <dd>건축, 토목과 관련된 자격
              </dd>
			  <dt>측정/측량</dt>
              <dd>정밀 측정, 공간 측량 등과 관련된 자격
              </dd>
			  <dt>기계/장비/설비</dt>
              <dd>기계, 장비, 설비의 설계·제작·정비 등과 관련된 자격
              </dd>
			  <dt>IT/정보</dt>
              <dd>응용소프트웨어 및 정보의 처리·관리와 관련된 자격
              </dd>
			  <dt>생산관리</dt>
              <dd>소재·부품·장비 및 제품의 품질과 관련된 자격
              </dd>
			  <dt>안전관리</dt>
              <dd>산업 안전관리와 관련된 자격
              </dd>
			  <dt>법률/금융</dt>
              <dd>법률, 금융과 관련된 자격
              </dd>
              <!--<dt>주인의식</dt>
              <dd>2
              </dd>-->
            </dl>
          </div>
        </div>
        <div id="tab06" class="js-tabcontent">
          <div class="tabWrap">
            <h3 class="modelTitle">TIER.6<br />관리역량</h3>
            <p class="summary"></p>
            <dl class="capabilities">
              <dt>조직관리</dt>
              <dd>법·제도·정책적 변화 등 급변화하는 환경속에서 조직의 비전을 연계한 업무 방향을 제시하는 능력
              </dd>
              <dt>인력관리</dt>
              <dd>조직 구성원/고객과 상호신뢰를 바탕으로 우호적인 협력관계를 구축하고 유지함으로써 서로의 발전을 도모하도록 유도하는 능력
              </dd>
			  <dt>고객관리</dt>
              <dd>대외적인 문제와 관련하여 조직의 입장을 대변하고, 이해관계자들과의 네트워킹 활동을 이끄는 능력
              </dd>
			  <dt>자원관리</dt>
              <dd>업무 수행에 필요한 시간자원, 자본자원, 물적자원, 인적자원이 얼마나 필요한지를 전반적으로 확인하고, 이용가능한 각종 자원들을 최대한 수집하여 실제 업무에 어떻게 활용할 것인지를 계획하고 할당하는 능력 
              </dd>
            </dl>
          </div>
        </div>
      </div>
    </div>
  </div>

