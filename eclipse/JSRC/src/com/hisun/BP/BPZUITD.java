package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class BPZUITD {
    boolean pgmRtn = false;
    String K_BP_PARM_MAINTAIN = "BP-PARM-MAINTAIN";
    String K_PARM_TYPE = "AMITD";
    String K_HIS_REMARKS = "GL MASTER MAINTENANCE";
    int WS_UPD_DT = 0;
    int WS_UPD_TM = 0;
    int WS_ITM_CNT = 0;
    int WS_I = 0;
    String WS_MSG_ERR = " ";
    BPZUITD_WS_KEY WS_KEY = new BPZUITD_WS_KEY();
    BPZUITD_WS_VAL WS_VAL = new BPZUITD_WS_VAL();
    char WS_FND_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCPARM BPCPARM = new BPCPARM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPRPARM BPRPARM = new BPRPARM();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRITD BPRITD = new BPRITD();
    BPRITD BPRNITD = new BPRITD();
    BPRITD BPROITD = new BPRITD();
    BPCHITD BPCHITDN = new BPCHITD();
    BPCHITD BPCHITDO = new BPCHITD();
    SCCGWA SCCGWA;
    BPCUITD BPCUITD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCUITD BPCUITD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUITD = BPCUITD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUITD return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        WS_FND_FLG = ' ';
        BPCUITD.RC.RC_MMO = "BP";
        BPCUITD.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCUITD.DATA);
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (BPCUITD.INFO.FUNC == 'I') {
            B020_QUERY_RECORD_PROC();
            if (pgmRtn) return;
            B021_MOVE_DATA();
            if (pgmRtn) return;
        } else if (BPCUITD.INFO.FUNC == 'Q') {
            B020_QUERY_RECORD_PROC();
            if (pgmRtn) return;
            B021_MOVE_DATA();
            if (pgmRtn) return;
        } else if (BPCUITD.INFO.FUNC == 'A') {
            B030_ADD_RECORD_PROC();
            if (pgmRtn) return;
            B021_MOVE_DATA();
            if (pgmRtn) return;
        } else if (BPCUITD.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
            B021_MOVE_DATA();
            if (pgmRtn) return;
        } else if (BPCUITD.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
            B021_MOVE_DATA();
            if (pgmRtn) return;
        } else {
            if (BPCUITD.INFO.FUNC != 'Q') {
                WS_MSG_ERR = BPCMSG_ERROR_MSG.BP_FUNC_ERROR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, BPRITD);
        BPRITD.KEY.TYP = "AMITD";
        BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRITD.KEY.REDEFINES5.BOOK_FLG = BPCUITD.DATA.KEY.REDEFINES17.BOOK_FLG;
        BPRITD.KEY.CD = IBS.CLS2CPY(SCCGWA, BPRITD.KEY.REDEFINES5);
        BPRITD.KEY.REDEFINES5.CNTR_TYPE = BPCUITD.DATA.KEY.REDEFINES17.CNTR_TYPE;
        BPRITD.KEY.CD = IBS.CLS2CPY(SCCGWA, BPRITD.KEY.REDEFINES5);
        BPCPRMM.FUNC = '3';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B030_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, BPRITD);
        BPRITD.KEY.TYP = "AMITD";
        BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        if ("20991231".trim().length() == 0) BPCPRMM.EXP_DT = 0;
        else BPCPRMM.EXP_DT = Integer.parseInt("20991231");
        S000_INPUT_DATA_PROCESS();
        if (pgmRtn) return;
        BPRITD.DATA_LEN = 1958;
        BPCPRMM.FUNC = '0';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, BPRITD);
        IBS.init(SCCGWA, BPCHITDO);
        IBS.init(SCCGWA, BPCHITDN);
        BPRITD.KEY.TYP = "AMITD";
        BPRITD.KEY.REDEFINES5.BOOK_FLG = BPCUITD.DATA.KEY.REDEFINES17.BOOK_FLG;
        BPRITD.KEY.CD = IBS.CLS2CPY(SCCGWA, BPRITD.KEY.REDEFINES5);
        BPRITD.KEY.REDEFINES5.CNTR_TYPE = BPCUITD.DATA.KEY.REDEFINES17.CNTR_TYPE;
        BPRITD.KEY.CD = IBS.CLS2CPY(SCCGWA, BPRITD.KEY.REDEFINES5);
        BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPRMM.FUNC = '4';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCUITD.DATA.DATA_TXT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRITD.DATA_TXT);
        if (JIBS_tmp_str[1].equalsIgnoreCase(JIBS_tmp_str[0])) {
            WS_MSG_ERR = BPCMSG_ERROR_MSG.BP_DATA_NOT_CHANGE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_UPD_DT = BPRITD.DATA_TXT.UPD_DATE;
        WS_UPD_TM = BPRITD.DATA_TXT.UPD_TIME;
        BPCHITDO.KEY.BOOK_FLG = BPRITD.KEY.REDEFINES5.BOOK_FLG;
        BPCHITDO.KEY.CNTR_TYPE = BPRITD.KEY.REDEFINES5.CNTR_TYPE;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRITD.DATA_TXT);
