function confirmAndDelete() {
    document.getElementById('diaryForm').action ='/diary/delete-note';
    document.getElementById('diaryForm').submit();
}