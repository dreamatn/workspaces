package com.hisun.SO;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCRCWAT;

import java.io.IOException;
import java.sql.SQLException;

public class SOZRRUN {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WK_SCPRRUNJ = "SCPRRUNJ";
    String PGM_SOZSMGJ = "SOZSMGJ";
    String WK_TEMP_CODE = " ";
    short WK_CNT = 0;
    int RESP_CODE = 0;
    SOZRRUN_WS_MSGID WS_MSGID = new SOZRRUN_WS_MSGID();
    SCCMSG SCCMSG = new SCCMSG();
    SOCMSG SOCMSG = new SOCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SOCSMGJ SOCSMGJ = new SOCSMGJ();
    SCCGWA SCCGWA;
    SCRCWAT SCRCWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    String WK_PROC_NAME = " ";
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SOZRRUN return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        WK_PROC_NAME = SCCGSCA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SOCSMGJ);
        SOCSMGJ.PROC_NAME = WK_SCPRRUNJ;
        SOCSMGJ.PROC_DATA = " ";
        if (SOCSMGJ.PROC_DATA == null) SOCSMGJ.PROC_DATA = "";
        JIBS_tmp_int = SOCSMGJ.PROC_DATA.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) SOCSMGJ.PROC_DATA += " ";
        SOCSMGJ.PROC_DATA = "P=" + SOCSMGJ.PROC_DATA.substring(2);
        if (SOCSMGJ.PROC_DATA == null) SOCSMGJ.PROC_DATA = "";
        JIBS_tmp_int = SOCSMGJ.PROC_DATA.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) SOCSMGJ.PROC_DATA += " ";
        if (WK_PROC_NAME == null) WK_PROC_NAME = "";
        JIBS_tmp_int = WK_PROC_NAME.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) WK_PROC_NAME += " ";
        SOCSMGJ.PROC_DATA = SOCSMGJ.PROC_DATA.substring(0, 3 - 1) + WK_PROC_NAME + SOCSMGJ.PROC_DATA.substring(3 + 8 - 1);
        S000_LINK_SOZSMGJ();
        if (pgmRtn) return;
    }
    public void S000_LINK_SOZSMGJ() throws IOException,SQLException,Exception {
        SOZSMGJ SOZSMGJ = new SOZSMGJ();
        SOZSMGJ.MP(SCCGWA, SOCSMGJ);
    }
    public void S000_ERROR_PROCESS() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        Z_RET();
        if (pgmRtn) return;
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
