function confirmDelete() {
	const result = confirm('本当に削除しますか？');
	if (result) {
		alert('削除が完了しました')
	} else {
		alert('削除をキャンセルしました');
	}
	return result;
}

	//!--return confirmDeleteが持つtrueやfalseの情報もらって実行している--

