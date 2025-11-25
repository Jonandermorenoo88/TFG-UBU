 
  <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
 // Construye etiquetas (usar código A5.1 desde .card-sub) y datos leyendo las tarjetas ya renderizadas (.card)
    
 document.addEventListener('DOMContentLoaded', function () {
      const canvas = document.getElementById('controlesChart');
      if (!canvas) return;

      const cardElems = Array.from(document.querySelectorAll('.grid a.card, .grid .card'));
      if (cardElems.length === 0) {
        canvas.style.display = 'none';
        return;
      }

      const labels = [];
      const data = [];
      cardElems.forEach(card => {
        // usar el subtítulo (ej. A5.1) como etiqueta del gráfico
        const idEl = card.querySelector('.card-sub');
        const scoreEl = card.querySelector('.score');
        const label = idEl ? idEl.textContent.trim() : '(sin código)';
        let score = 0;
        if (scoreEl) {
          const txt = scoreEl.textContent.trim();
          const m = txt.match(/(\d+)\s*%/);
          if (m) score = parseInt(m[1], 10);
        }
        labels.push(label);
        data.push(isNaN(score) ? 0 : score);
      });

      if (labels.length === 0) {
        canvas.style.display = 'none';
        return;
      }

      canvas.style.height = '360px';
      const ctx = canvas.getContext('2d');
      new Chart(ctx, {
        type: 'bar',
        data: {
          labels: labels,
          datasets: [{
            label: 'Puntuación media (%)',
            data: data,
            backgroundColor: data.map(() => 'rgba(13,110,253,0.72)'),
            borderColor: data.map(() => 'rgba(13,110,253,0.95)'),
            borderWidth: 1
          }]
        },
        options: {
          responsive: true,
          maintainAspectRatio: false,
          scales: {
            x: { ticks: { maxRotation: 45, minRotation: 0 }, grid: { display: false } },
            y: { beginAtZero: true, max: 100, ticks: { stepSize: 10 } }
          },
          plugins: {
            legend: { display: false },
            tooltip: { callbacks: { label: function(ctx) { return ctx.parsed.y + '%'; } } }
          }
        }
      });
    });