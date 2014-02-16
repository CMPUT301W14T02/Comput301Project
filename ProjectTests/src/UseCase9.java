public void testSortByDate(ArrayList<Location> locationList) {
    TopLevelCommentModel topComment1 = new TopLevelCommentModel("name1", locationList.get(1));
    TopLevelCommentModel topComment2 = new TopLevelCommentModel("name2", locationList.get(2));
    TopLevelCommentModel topComment3 = new TopLevelCommentModel("name3", locationList.get(3));
    TopLevelCommentModel topComment4 = new TopLevelCommentModel("name4", locationList.get(4));
    TopLevelCommentModel topComment5 = new TopLevelCommentModel("name5", locationList.get(5));
    ArrayList<TopLevelCommentModel> arrayList1 = new ArrayList<TopLevelCommentModel>;
    ArrayList<TopLevelCommentModel> arrayList2 = new ArrayList<TopLevelCommentModel>;
    arrayList1.add(topComment2);
    arrayList1.add(topComment1);
    arrayList1.add(topComment5);
    arrayList1.add(topComment4);
    arrayList1.add(topComment3);
    arrayList2.add(topComment1);
    arrayList2.add(topComment2);
    arrayList2.add(topComment3);
    arrayList2.add(topComment4);
    arrayList2.add(topComment5);
    Sort.sortByDate(arrayList1, locationList.get(0));
    assertTrue("The arraylists should be the same after the sort", (arrayList1 == arrayList2));
}
