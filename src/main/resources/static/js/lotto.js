document.addEventListener('DOMContentLoaded', () => {

    //==================== 탭 ====================//
    const tabGenerate = document.getElementById('tab-generate');
    const tabStats = document.getElementById('tab-stats');

    const sectionGenerate = document.getElementById('section-generate');
    const sectionStats = document.getElementById('section-stats');

    tabGenerate.addEventListener('click', () => {
        tabGenerate.classList.add('active');
        tabStats.classList.remove('active');
        sectionGenerate.classList.remove('hidden');
        sectionStats.classList.add('hidden');
    });

    tabStats.addEventListener('click', () => {
        tabStats.classList.add('active');
        tabGenerate.classList.remove('active');
        sectionStats.classList.remove('hidden');
        sectionGenerate.classList.add('hidden');
    });

    //==================== 로또 발급 ====================//
    const generateForm = document.getElementById('generate-form');
    const generateResult = document.getElementById('generate-result');
    const resultTickets = document.getElementById('result-tickets');
    const resultYield = document.getElementById('result-yield');

    const rank1Count = document.getElementById('rank1-count');
    const rank2Count = document.getElementById('rank2-count');
    const rank3Count = document.getElementById('rank3-count');
    const rank4Count = document.getElementById('rank4-count');
    const rank5Count = document.getElementById('rank5-count');

    const totalPrize = document.getElementById('total-prize');

    generateForm.addEventListener('submit', async (event) => {
        event.preventDefault();

        const email = document.getElementById('generate-email').value.trim();
        const price = document.getElementById('generate-price').value.trim();

        if (!email || !price) {
            showError("이메일과 금액을 입력해주세요.");
            return;
        }

        try {
            const response = await fetch('/api/lotto/generate', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    email: email,
                    purchasePrice: price
                })
            });

            if (!response.ok) {
                throw new Error("로또 발급 실패!");
            }

            const data = await response.json();
            renderGenerateResult(data);

        } catch (e) {
            showError(e.message);
        }
    });

    function renderGenerateResult(data) {
        generateResult.classList.remove('hidden');

        // 수익률
        resultYield.textContent = `${data.returnRate.toFixed(1)}%`;

        // 로또 리스트
        resultTickets.innerHTML = "";
        data.lottos.forEach((numbers, index) => {

            const row = document.createElement('div');
            row.className = "ticket-item";

            const label = document.createElement('div');
            label.className = "ticket-label";
            label.textContent = `#${index + 1}`;

            const balls = document.createElement('div');
            balls.className = "ticket-balls";

            numbers.forEach(num => {
                const ball = document.createElement('div');
                ball.className = "ball";
                ball.textContent = num;
                balls.appendChild(ball);
            });

            row.appendChild(label);
            row.appendChild(balls);
            resultTickets.appendChild(row);
        });

        // 등수 결과 초기화
        rank1Count.textContent = "0개";
        rank2Count.textContent = "0개";
        rank3Count.textContent = "0개";
        rank4Count.textContent = "0개";
        rank5Count.textContent = "0개";

        let prizeSum = 0;

        data.winningRanks.forEach(rank => {
            const { matchedNumberCount, prizeMoney, count } = rank;

            prizeSum += prizeMoney * count;

            if (matchedNumberCount === 6) rank1Count.textContent = `${count}개`;
            if (matchedNumberCount === 5 + 1) rank2Count.textContent = `${count}개`;
            if (matchedNumberCount === 5) rank3Count.textContent = `${count}개`;
            if (matchedNumberCount === 4) rank4Count.textContent = `${count}개`;
            if (matchedNumberCount === 3) rank5Count.textContent = `${count}개`;
        });

        totalPrize.textContent = `${prizeSum.toLocaleString()}원`;
    }

    //==================== 로또 통계 조회 ====================//
    const statsForm = document.getElementById('stats-form');
    const statsResult = document.getElementById('stats-result');
    const statsList = document.getElementById('stats-list');

    statsForm.addEventListener('submit', async (event) => {
        event.preventDefault();

        const email = document.getElementById('stats-email').value.trim();
        if (!email) {
            showError("이메일을 입력해주세요.");
            return;
        }

        try {
            const response = await fetch('/api/lotto/statistic', {
                method: 'POST',
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ email })
            });

            if (!response.ok) {
                throw new Error("통계 조회 실패!");
            }

            const data = await response.json();
            renderStatsResult(data);

        } catch (e) {
            showError(e.message);
        }
    });

    function renderStatsResult(data) {
        statsList.innerHTML = "";
        statsResult.classList.remove('hidden');

        const infos = data.lottoStatisticInfos;

        if (!infos || infos.length === 0) {
            statsList.innerHTML = `<div style="color:#6B7280">조회된 기록이 없습니다.</div>`;
            return;
        }

        infos.forEach((info, index) => {

            const item = document.createElement('div');
            item.classList.add('stats-item');

            const left = document.createElement('div');
            left.classList.add('stats-left');

            const order = document.createElement('div');
            order.textContent = `${index + 1}.`;
            order.style.fontWeight = "600";

            const price = document.createElement('div');
            price.className = 'stats-amount';
            price.textContent = `${info.getPurchasePrice.toLocaleString()}원`;

            const label = document.createElement('div');
            label.className = 'stats-label';
            label.textContent = '총 구매 금액';

            left.appendChild(order);
            left.appendChild(price);
            left.appendChild(label);

            const yieldBox = document.createElement('div');
            yieldBox.className = "stats-yield";
            yieldBox.textContent = `${info.getReturnRate.toFixed(1)}%`;

            item.appendChild(left);
            item.appendChild(yieldBox);
            statsList.appendChild(item);
        });
    }

    //==================== 에러 토스트 ====================//
    const errorToast = document.getElementById('error-toast');
    let toastId = null;

    function showError(msg) {
        errorToast.textContent = msg;
        errorToast.classList.remove('hidden');

        if (toastId) clearTimeout(toastId);
        toastId = setTimeout(() => {
            errorToast.classList.add('hidden');
        }, 3000);
    }
});