package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class BPZFUFAV {
    boolean pgmRtn = false;
    String CPN_F_MAINTAIN_PARM = "BP-F-F-MAINTAIN-PARM";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String CPN_F_MAINTAIN_NHIS = "BP-REC-NHIS";
    String WS_ERR_MSG = " ";
    String WS_TXT = " ";
    double WS_AMT = 0;
    short WS_CNT = 0;
    short WS_J = 0;
    int WS_DATE = 0;
    char WS_FAV_KND = ' ';
    char WS_DATACHG_FLG = ' ';
    String WS_FAV_TYP = " ";
    char WS_COLL_FLG = ' ';
    char WS_CAL_MTH = ' ';
    char WS_CAL_CYC_1 = ' ';
    char WS_COL_TYPE = ' ';
    char WS_ARG_SPL = ' ';
    char WS_TBL_BANK_FLAG = ' ';
    char WS_FCOM_FND_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFPARM BPCFPARM = new BPCFPARM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPVFFAV BPVOFAV = new BPVFFAV();
    BPVFFAV BPVFFAV = new BPVFFAV();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPVFCOM BPVFCOM = new BPVFCOM();
    SCCGWA SCCGWA;
    BPCOFFAV BPCOUFAV;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCOFFAV BPCOUFAV) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOUFAV = BPCOUFAV;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFUFAV return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPVFFAV);
        IBS.init(SCCGWA, BPCFPARM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B090_CHECK_DATA_CN();
            if (pgmRtn) return;
        } else {
            B090_CHECK_DATA();
            if (pgmRtn) return;
        }
        if (BPCOUFAV.FUNC == 'I') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCOUFAV.FUNC == 'A') {
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
            B020_01_HISTORY_RECORD();
            if (pgmRtn) return;
        } else if (BPCOUFAV.FUNC == 'U') {
            if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
                B030_MODIFY_PROCESS_CN();
                if (pgmRtn) return;
            } else {
                B030_MODIFY_PROCESS();
                if (pgmRtn) return;
            }
            B020_01_HISTORY_RECORD();
            if (pgmRtn) return;
        } else if (BPCOUFAV.FUNC == 'D') {
            B040_DELETE_PROCESS();
            if (pgmRtn) return;
            B020_01_HISTORY_RECORD();
            if (pgmRtn) return;
        } else if (BPCOUFAV.FUNC == 'B') {
            B050_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPVFFAV);
