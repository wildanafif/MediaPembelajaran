package com.yokoding.wildanafif.mediapembelajaranproject.model;

/**
 * Created by wildan afif on 5/31/2017.
 */


public final class Request_addr {
    public static final String _HOST="http://www.lenteranegeri.com/";
    public static final String _SLIDER=_HOST+"api/slider/get_all";
    public static final String _BAB=_HOST+"api/bab/get/";
    public static final String _URL_GAMBAR=_HOST+"public/uploads/";
    public static final String _MATERI=_HOST+"api/materi/get_where/";

    public static final String _URLMATERI=_HOST+"materi/";

    public static final String _DETAIL_MATERI=_HOST+"api/materi/detail/";

    public static final String _GET_SOAL=_HOST+"api/soal/get_where/";
    public static final String _SET_NILAI=_HOST+"api/bab/set_status_bab/";


    //post
    public static final String _SAVE_USER=_HOST+"api/user/create";
    public static final String _VIDEO=_HOST+"public/video.php?id=";
    public static final String _STATUS_BAB=_HOST+"api/user/getstatus/";
}
