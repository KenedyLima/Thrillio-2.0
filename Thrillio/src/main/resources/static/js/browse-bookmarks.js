const profileBox = document.querySelector(".profile-box");
const profileBoxBody = document.querySelector(".profile-box-body");
const configButton = document.querySelector(".config-button");
const logoutButton = document.querySelector(".config-button");

profileBox.addEventListener("click", (e) => {
  if (e.target.closest(".profile-box-body")) return;
  profileBox.classList.toggle("opened");
});

configButton.addEventListener("click", (e) => {
  window.location.href =
    "http://127.0.0.1:5500/Thrillio/src/main/webapp/WEB-INF/templates/configuration-page.html";
});

logoutButton.addEventListener("click", (e) => {
  window.location.href =
    "http://127.0.0.1:5500/Thrillio/src/main/webapp/WEB-INF/templates/configuration-page.html";
});
