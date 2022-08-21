function groupJoin(id, grade){
    console.log("id: " + id + ", grade: " + grade);
        let groupTabId = id;
        let result = {"groupTabId" : groupTabId, "grade" : grade};
        $.ajax({
            url: "/mig/new/" + groupTabId,
            type: "POST",
            data: result,
            success: function(data){
                location.reload();
            }
        });
}

function groupQuit(id, grade){
    console.log("id: " + id + ", grade: " + grade);
    let groupTabId = id;
    let result = {"groupTabId" : groupTabId, "grade" : grade};
    $.ajax({
        url: "/mig/delete/" + groupTabId,
        type: "POST",
        data: result,
        success: function(data){
            location.reload();
        }
    });
}