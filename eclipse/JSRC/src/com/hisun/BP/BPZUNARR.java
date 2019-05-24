package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class BPZUNARR {
    boolean pgmRtn = false;
    String APP_MMO_DBC = "DBC";
    String WK_CM_TX_CD = "0351200";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    char WS_UPDATE_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCUFHIS BPCUFHIS = new BPCUFHIS();
    BPRFHIST BPRFHIST = new BPRFHIST();
    SCCGWA SCCGWA;
    BPCUNARR BPCUNARR;
    public void MP(SCCGWA SCCGWA, BPCUNARR BPCUNARR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUNARR = BPCUNARR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUNARR return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCUNARR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCUFHIS);
        IBS.init(SCCGWA, BPRFHIST);
        BPRFHIST.KEY.AC_DT = BPCUNARR.INP_DATA.AC_DT;
        BPRFHIST.KEY.JRNNO = BPCUNARR.INP_DATA.JRNNO;
        BPCUFHIS.DATA.FUNC = '3';
        S000_CALL_BPZUFHIS();
        if (pgmRtn) return;
        BPCUFHIS.DATA.FUNC = '4';
        S000_CALL_BPZUFHIS();
        if (pgmRtn) return;
        WS_I = 0;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUFHIS.RC);
        while (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_END_OF_TABLE)) {
            B010_TRANS_DATA_COND();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPRFHIST);
            BPCUFHIS.DATA.FUNC = '4';
            S000_CALL_BPZUFHIS();
            if (pgmRtn) return;
        }
        BPCUFHIS.DATA.FUNC = '5';
        S000_CALL_BPZUFHIS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_I);
        BPCUNARR.OUTPUT.CNT = WS_I;
        if (WS_I == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCUNARR.RC);
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCUNARR.RC);
        }
    }
    public void B010_TRANS_DATA_COND() throws IOException,SQLException,Exception {
        WS_UPDATE_FLG = 'N';
        CEP.TRC(SCCGWA, BPCUNARR.FUNC);
        if (BPCUNARR.FUNC == 'N') {
            BPRFHIST.NARRATIVE = BPCUNARR.INP_DATA.NARRATIVE;
            WS_UPDATE_FLG = 'Y';
        } else if (BPCUNARR.FUNC == 'T') {
            if (BPRFHIST.KEY.AC.equalsIgnoreCase(BPCUNARR.INP_DATA.AC)) {
                BPRFHIST.TX_TOOL = BPCUNARR.INP_DATA.TX_TOOL;
                WS_UPDATE_FLG = 'Y';
            }
        } else if (BPCUNARR.FUNC == 'S') {
            if (BPRFHIST.APP_MMO.equalsIgnoreCase(APP_MMO_DBC) 
                || BPRFHIST.TX_CD.equalsIgnoreCase(WK_CM_TX_CD)) {
                BPRFHIST.TX_STS = BPCUNARR.INP_DATA.TX_STS;
                WS_UPDATE_FLG = 'Y';
            }
        } else if (BPCUNARR.FUNC == 'R') {
            if (BPRFHIST.KEY.AC.equalsIgnoreCase(BPCUNARR.INP_DATA.AC)) {
                B030_FILL_RLT_DATA();
                if (pgmRtn) return;
            }
        }
        if (WS_UPDATE_FLG == 'Y') {
            BPCUFHIS.DATA.FUNC = '2';
            S000_CALL_BPZUFHIS();
            if (pgmRtn) return;
            WS_I += 1;
        }
    }
    public void B030_FILL_RLT_DATA() throws IOException,SQLException,Exception {
        if (BPRFHIST.RLT_AC.trim().length() == 0) {
            BPRFHIST.RLT_AC = BPCUNARR.INP_DATA.RLT_AC;
            WS_UPDATE_FLG = 'Y';
        }
