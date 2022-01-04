function addNote(uuid) {
  //apply custom values where needed
  $('#addNoteModal').find('[name="uuid"]').val(uuid);
  //show dialog
  $('#addNoteModal').modal('show');
}
