package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSBUPD {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_CMP_MAIN_BPTEXUPF = "BP-R-MAINT-UPDATA";
    String WS_ERR_MSG = " ";
    BPZSBUPD_WS_BATNO_HD WS_BATNO_HD = new BPZSBUPD_WS_BATNO_HD();
    char WS_OUTPUT_FLG = ' ';
    char WS_STOP = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCRMUPD BPCRMUPD = new BPCRMUPD();
    BPREXUPF BPREXUPF = new BPREXUPF();
    BPCOBUPD BPCOBUPD = new BPCOBUPD();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    BPCSBUPD BPCSBUPD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSBUPD BPCSBUPD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSBUPD = BPCSBUPD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSBUPD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCSBUPD.FUNC == 'B') {
            B010_BROWSE_EXUPF_PROCESS();
            if (pgmRtn) return;
            B030_SET_SUB_TRN();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCSBUPD.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_BROWSE_EXUPF_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 969;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPREXUPF);
        IBS.init(SCCGWA, BPCRMUPD);
        BPREXUPF.KEY.BATCH_NO = BPCSBUPD.BATCH_NO;
        BPCRMUPD.INFO.POINTER = BPREXUPF;
        BPCRMUPD.INFO.LEN = 183;
        BPCRMUPD.INFO.FUNC = 'B';
        BPCRMUPD.INFO.OPT = 'S';
        BPREXUPF.KEY.BATCH_NO = BPCSBUPD.BATCH_NO;
        CEP.TRC(SCCGWA, BPCSBUPD.BATCH_NO);
        CEP.TRC(SCCGWA, BPREXUPF.KEY.BATCH_NO);
        S000_CALL_BPZRMUPD();
        if (pgmRtn) return;
        BPCRMUPD.INFO.FUNC = 'B';
        BPCRMUPD.INFO.OPT = 'N';
        S000_CALL_BPZRMUPD();
        if (pgmRtn) return;
        WS_STOP = 'N';
        WS_BATNO_HD.WS_REC_CNT = 0;
        while (BPCRMUPD.RETURN_INFO != 'N' 
            && WS_STOP != 'Y' 
            && SCCMPAG.FUNC != 'E') {
            if (BPCSBUPD.BATCH_NO.trim().length() == 0) {
                R000_OUTPUT_DATA_PROCESS();
                if (pgmRtn) return;
            } else {
                if (BPREXUPF.KEY.BATCH_NO.equalsIgnoreCase(BPCSBUPD.BATCH_NO)) {
                    R000_OUTPUT_DATA_PROCESS();
                    if (pgmRtn) return;
                    WS_BATNO_HD.WS_REC_CNT = BPREXUPF.KEY.SEQ;
                } else {
                    WS_STOP = 'Y';
                }
            }
            BPCRMUPD.INFO.FUNC = 'B';
            BPCRMUPD.INFO.OPT = 'N';
            S000_CALL_BPZRMUPD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCRMUPD.RETURN_INFO);
        }
        CEP.TRC(SCCGWA, "BEGIN BROWSE END");
        BPCRMUPD.INFO.FUNC = 'B';
        BPCRMUPD.INFO.OPT = 'E';
        S000_CALL_BPZRMUPD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BROWSE END");
        WS_BATNO_HD.WS_TMP_BATNO = BPCSBUPD.BATCH_NO;
        WS_BATNO_HD.WS_REC_CNT = WS_BATNO_HD.WS_REC_CNT + 1;
        WS_BATNO_HD.WS_REC_TYPE = BPCSBUPD.UP_TYPE;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_BATNO_HD);
        SCCMPAG.DATA_LEN = 48;
        CEP.TRC(SCCGWA, WS_BATNO_HD);
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_OUTPUT_DATA_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOBUPD);
        BPCOBUPD.BATCH_NO = BPREXUPF.KEY.BATCH_NO;
        BPCOBUPD.SEQ_NO = BPREXUPF.KEY.SEQ;
        BPCOBUPD.UP_TYPE = BPCSBUPD.UP_TYPE;
        BPCOBUPD.CHK_FLAG = BPREXUPF.CHK_FLAG;
        BPCOBUPD.CHK_MSG = BPREXUPF.CHK_MSG;
        BPCOBUPD.FILLER = 0X02;
        BPCOBUPD.UP_DATA = BPREXUPF.RECORD;
        CEP.TRC(SCCGWA, BPCOBUPD.UP_DATA);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        CEP.TRC(SCCGWA, BPCOBUPD);
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOBUPD);
        SCCMPAG.DATA_LEN = 969;
        CEP.TRC(SCCGWA, SCCMPAG.DATA_LEN);
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B030_SET_SUB_TRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCSUBS);
        SCCSUBS.AP_CODE = 999;
        if (BPCSBUPD.UP_TYPE.equalsIgnoreCase("01")) {
            SCCSUBS.TR_CODE = 9250;
        } else if (BPCSBUPD.UP_TYPE.equalsIgnoreCase("02")) {
            SCCSUBS.TR_CODE = 9206;
        } else if (BPCSBUPD.UP_TYPE.equalsIgnoreCase("03")) {
            SCCSUBS.TR_CODE = 9216;
        } else if (BPCSBUPD.UP_TYPE.equalsIgnoreCase("04")) {
            SCCSUBS.TR_CODE = 9217;
        } else if (BPCSBUPD.UP_TYPE.equalsIgnoreCase("05")) {
            SCCSUBS.TR_CODE = 9225;
        } else {
            SCCSUBS.TR_CODE = 9206;
        }
        S000_SET_SUBS_TRN();
        if (pgmRtn) return;
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SET-SUBS-TRANS", SCCSUBS);
    }
    public void S000_CALL_BPZRMUPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_MAIN_BPTEXUPF, BPCRMUPD);
        if (BPCRMUPD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRMUPD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
