function searchMap() {
    navigator.geolocation.getCurrentPosition(function (pos) {
        let latitude = pos.coords.latitude;
        let longitude = pos.coords.longitude;
        $("#latitude2").val(latitude);
        $("#longitude2").val(longitude);
        //alert("현재 위치는 : " + latitude + ", "+ longitude);
        getAddr($("#latitude2").val(), $("#longitude2").val());
    });
    function getAddr(lat, lng) {
        let geocoder = new kakao.maps.services.Geocoder();

        let coord = new kakao.maps.LatLng(lat, lng);
        let callback = function (result, status) {
            let openWin;
            if (status === kakao.maps.services.Status.OK) {
                $("#place").val(result[0].address.region_3depth_name);
                let place = $("#place").val();

                window.name = "parentForm";
                openWin = window.open(
                    "/gatherings/gatheringSearchMap?place=" + place,
                    "gatheringSearchMap",
                    "width=1000, height=530, top=100, left=100"
                );
            }
        };

        geocoder.coord2Address(coord.getLng(), coord.getLat(), callback);
    }
}