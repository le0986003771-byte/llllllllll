極簡車機啟動器：雲端編譯版

目標：產生真正的 Android APK，支援 Android 6.0.1（minSdk 23）。

最少操作方式（需要 GitHub 帳號）：
1. 在 Mac 解壓縮這個 ZIP。
2. 到 https://github.com/new 建立一個新的 repository，名稱輸入 MinimalCarLauncher，選 Public 或 Private 都可以，按 Create repository。
3. 在新 repo 頁面，選「uploading an existing file」或「Add file」→「Upload files」。
4. 把解壓後資料夾裡的所有檔案和資料夾拖進上傳區（包含 .github 資料夾；若看不到 .github，請用 Finder 的「前往資料夾」或終端機顯示隱藏檔案）。更簡單可先把檔案夾內容全選後拖曳；請確保 app、.github、build.gradle、settings.gradle 都在 repo 根目錄。
5. 在頁面底部按 Commit changes。
6. 打開 repo 上方的 Actions。第一次若要求啟用 Actions，按允許。
7. 選左側「Build APK」，按右側「Run workflow」→綠色「Run workflow」。若 push 自動觸發，也可以直接等它跑完。
8. 點進最新一次工作流程，等所有步驟綠色成功。往下找到 Artifacts，下載「MinimalCarLauncher-APK.zip」。
9. 解壓縮後取得 app-debug.apk。把 APK 傳到車機（USB 隨身碟或下載），在車機上點開安裝；若系統阻擋，需在設定允許該來源安裝未知應用程式。
10. 安裝後按 Home，若跳出選擇桌面，選 Minimal Car Launcher。是否能作預設桌面取決於車機廠商系統。

注意：這個流程會在 GitHub 雲端編譯；本 ZIP 本身不是 APK。APK 要等 GitHub Actions 成功後才能下載。

功能：深色極簡首頁、時間日期、音樂、Google 地圖（若已安裝）、藍牙設定、系統設定。音樂/地圖的啟動行為依車機已安裝的 App 而異。
