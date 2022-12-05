(function startPostersAnimation() {
  const spans = document.querySelectorAll(".subtitle-span");

  setInterval(() => {
    spans.forEach((span) => {
      span.classList.toggle("active-word");
    });
  }, 2000);
})();
