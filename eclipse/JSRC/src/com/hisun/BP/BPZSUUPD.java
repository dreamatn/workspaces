package com.hisun.BP;

import com.hisun.AI.*;
import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSUUPD {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_CMP_TXN_HIS = "BP-REC-NHIS";
    String K_CMP_MAIN_BPTEXUPF = "BP-R-MAINT-UPDATA";
    String K_FMT_CD_HOL = "BP748";
    String K_FMT_CD_AM = "BP749";
    String K_FMT_CD_ODE = "BP750";
    String K_FMT_CD_EXRT = "BP751";
    String K_FMT_CD_RATE = "BP752";
    String K_HIS_REMARKS = "EXCEL UPLOAD  MAINTENANCE";
    String WS_ERR_MSG = " ";
    int WS_REC_LEN = 0;
    String WS_UP_TYPE = " ";
    String WS_MSG_TXT = " ";
    char WK_CHECK_RESULT = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPREXUPF BPREXUPF = new BPREXUPF();
    BPREXUPF BPROXUPF = new BPREXUPF();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCOEXRT BPCOEXRT = new BPCOEXRT();
    BPCOEHOL BPCOEHOL = new BPCOEHOL();
    BPCOERTE BPCOERTE = new BPCOERTE();
    BPCECNGL BPCECNGL = new BPCECNGL();
    AICOODEL AICOODEL = new AICOODEL();
    BPCRMUPD BPCRMUPD = new BPCRMUPD();
    BPCEXCHK BPCEXCHK = new BPCEXCHK();
    SCCIMSG SCCIMSG = new SCCIMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    BPCSUUPD BPCSUUPD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSUUPD BPCSUUPD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSUUPD = BPCSUUPD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSUUPD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_CHK_UPLOAD_DATA();
        if (pgmRtn) return;
        if (BPCSUUPD.FUNC == 'U') {
            B010_UPDATE_EXUPF_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSUUPD.FUNC == 'C') {
            B020_CREATE_EXUPF_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSUUPD.FUNC == 'D') {
            B030_DELETE_EXUPF_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCSUUPD.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCSUUPD.TYPE.trim().length() == 0) {
            if (BPCSUUPD.KEY.BATCH_NO == null) BPCSUUPD.KEY.BATCH_NO = "";
            JIBS_tmp_int = BPCSUUPD.KEY.BATCH_NO.length();
            for (int i=0;i<36-JIBS_tmp_int;i++) BPCSUUPD.KEY.BATCH_NO += " ";
            BPCSUUPD.TYPE = BPCSUUPD.KEY.BATCH_NO.substring(9 - 1, 9 + 2 - 1);
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSUUPD.KEY);
        if (JIBS_tmp_str[0].trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BATCH_NO_EMPTY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSUUPD.EX_DATA.trim().length() == 0 
            && BPCSUUPD.FUNC != 'D') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UP_DATA_EMPTY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B200_CHK_UPLOAD_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCEXCHK);
        BPCEXCHK.EXCEL_DATA = BPCSUUPD.EX_DATA;
        WS_UP_TYPE = BPCSUUPD.TYPE;
        if (WS_UP_TYPE.equalsIgnoreCase("01")) {
            CEP.TRC(SCCGWA, "EXCHK-EXCEL-EXCH");
            BPCEXCHK.EXCEL_TYPE = "01";
            S000_CALL_BPZCEXC();
            if (pgmRtn) return;
        } else if (WS_UP_TYPE.equalsIgnoreCase("02")) {
            CEP.TRC(SCCGWA, "EXCHK-EXCEL-HOL");
            BPCEXCHK.EXCEL_TYPE = "02";
            S000_CALL_BPZEXHOL();
            if (pgmRtn) return;
        } else if (WS_UP_TYPE.equalsIgnoreCase("03")) {
            CEP.TRC(SCCGWA, "EXCHK-EXCEL-ODE");
            BPCEXCHK.EXCEL_TYPE = "03";
            S000_CALL_AIZEODEL();
            if (pgmRtn) return;
        } else if (WS_UP_TYPE.equalsIgnoreCase("04")) {
            CEP.TRC(SCCGWA, "EXCHK-EXCEL-AM");
            BPCEXCHK.EXCEL_TYPE = "04";
            S000_CALL_BPZUCEXL();
            if (pgmRtn) return;
        } else if (WS_UP_TYPE.equalsIgnoreCase("05")) {
            CEP.TRC(SCCGWA, "EXCHK-EXCEL-RATE");
            BPCEXCHK.EXCEL_TYPE = "05";
            S000_CALL_BPZEXCRT();
            if (pgmRtn) return;
        } else {
        }
        WK_CHECK_RESULT = ' ';
        CEP.TRC(SCCGWA, BPCEXCHK.DATA_FLG);
        if (BPCEXCHK.DATA_FLG == '0') {
            WK_CHECK_RESULT = 'N';
            IBS.init(SCCGWA, SCCIMSG);
            SCCIMSG.ID = IBS.CLS2CPY(SCCGWA, BPCEXCHK.RC);
            S000_CALL_SCZIMSG();
            if (pgmRtn) return;
            if (WS_MSG_TXT == null) WS_MSG_TXT = "";
            JIBS_tmp_int = WS_MSG_TXT.length();
            for (int i=0;i<88-JIBS_tmp_int;i++) WS_MSG_TXT += " ";
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCEXCHK.RC);
            WS_MSG_TXT = JIBS_tmp_str[0] + WS_MSG_TXT.substring(8);
            if (WS_MSG_TXT == null) WS_MSG_TXT = "";
            JIBS_tmp_int = WS_MSG_TXT.length();
            for (int i=0;i<88-JIBS_tmp_int;i++) WS_MSG_TXT += " ";
            if (SCCIMSG.TXT == null) SCCIMSG.TXT = "";
            JIBS_tmp_int = SCCIMSG.TXT.length();
            for (int i=0;i<88-JIBS_tmp_int;i++) SCCIMSG.TXT += " ";
            WS_MSG_TXT = WS_MSG_TXT.substring(0, 9 - 1) + SCCIMSG.TXT + WS_MSG_TXT.substring(9 + SCCIMSG.TXT.length() - 1);
            CEP.TRC(SCCGWA, SCCIMSG.TXT);
            CEP.TRC(SCCGWA, WS_MSG_TXT);
        } else {
            WK_CHECK_RESULT = 'Y';
            WS_MSG_TXT = " ";
        }
    }
    public void B010_UPDATE_EXUPF_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPREXUPF);
        IBS.init(SCCGWA, BPROXUPF);
        IBS.init(SCCGWA, BPCRMUPD);
        BPCRMUPD.INFO.POINTER = BPREXUPF;
        BPCRMUPD.INFO.LEN = 183;
        BPCRMUPD.INFO.FUNC = 'R';
        CEP.TRC(SCCGWA, BPCSUUPD.KEY.BATCH_NO);
        CEP.TRC(SCCGWA, BPCSUUPD.KEY.SEQ);
        BPREXUPF.KEY.BATCH_NO = BPCSUUPD.KEY.BATCH_NO;
        BPREXUPF.KEY.SEQ = BPCSUUPD.KEY.SEQ;
        S000_CALL_BPZRMUPD();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, BPREXUPF, BPROXUPF);
        BPCRMUPD.INFO.FUNC = 'U';
        BPREXUPF.KEY.BATCH_NO = BPCSUUPD.KEY.BATCH_NO;
        BPREXUPF.KEY.SEQ = BPCSUUPD.KEY.SEQ;
        CEP.TRC(SCCGWA, BPCSUUPD.EX_DATA);
        CEP.TRC(SCCGWA, BPREXUPF.RECORD.trim().length());
        CEP.TRC(SCCGWA, BPCSUUPD.EX_DATA);
        BPREXUPF.RECORD = BPCSUUPD.EX_DATA;
        BPREXUPF.CHK_FLAG = WK_CHECK_RESULT;
        CEP.TRC(SCCGWA, WS_MSG_TXT);
        BPREXUPF.CHK_MSG = WS_MSG_TXT;
        CEP.TRC(SCCGWA, BPREXUPF.CHK_MSG);
        S000_CALL_BPZRMUPD();
        if (pgmRtn) return;
        B300_OUTPUT_DATA_PROCESS();
        if (pgmRtn) return;
    }
    public void B020_CREATE_EXUPF_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPREXUPF);
        IBS.init(SCCGWA, BPROXUPF);
        IBS.init(SCCGWA, BPCRMUPD);
        BPCRMUPD.INFO.FUNC = 'C';
        BPREXUPF.KEY.BATCH_NO = BPCSUUPD.KEY.BATCH_NO;
        BPREXUPF.KEY.SEQ = BPCSUUPD.KEY.SEQ;
        CEP.TRC(SCCGWA, BPREXUPF.KEY.BATCH_NO);
        CEP.TRC(SCCGWA, BPREXUPF.KEY.SEQ);
        CEP.TRC(SCCGWA, BPCSUUPD.EX_DATA);
        CEP.TRC(SCCGWA, BPREXUPF.RECORD.trim().length());
        BPREXUPF.RECORD = BPCSUUPD.EX_DATA;
        BPREXUPF.CHK_FLAG = WK_CHECK_RESULT;
        CEP.TRC(SCCGWA, WS_MSG_TXT);
        BPREXUPF.CHK_MSG = WS_MSG_TXT;
        CEP.TRC(SCCGWA, BPREXUPF.RECORD);
        CEP.TRC(SCCGWA, BPREXUPF.CHK_FLAG);
        CEP.TRC(SCCGWA, BPREXUPF.CHK_MSG);
        BPCRMUPD.INFO.POINTER = BPREXUPF;
        BPCRMUPD.INFO.LEN = 183;
        S000_CALL_BPZRMUPD();
        if (pgmRtn) return;
        B300_OUTPUT_DATA_PROCESS();
        if (pgmRtn) return;
    }
    public void B030_DELETE_EXUPF_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPREXUPF);
        IBS.init(SCCGWA, BPROXUPF);
        IBS.init(SCCGWA, BPCRMUPD);
        BPCRMUPD.INFO.POINTER = BPREXUPF;
        BPCRMUPD.INFO.LEN = 183;
        BPCRMUPD.INFO.FUNC = 'R';
        CEP.TRC(SCCGWA, BPCSUUPD.KEY.BATCH_NO);
        CEP.TRC(SCCGWA, BPCSUUPD.KEY.SEQ);
        BPREXUPF.KEY.BATCH_NO = BPCSUUPD.KEY.BATCH_NO;
        BPREXUPF.KEY.SEQ = BPCSUUPD.KEY.SEQ;
        S000_CALL_BPZRMUPD();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, BPREXUPF, BPROXUPF);
        BPCRMUPD.INFO.FUNC = 'D';
        S000_CALL_BPZRMUPD();
        if (pgmRtn) return;
        B300_OUTPUT_DATA_PROCESS();
        if (pgmRtn) return;
    }
    public void S000_CALL_HIS_PROC() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.FMT_ID = "BPVXUPF";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.REF_NO = IBS.CLS2CPY(SCCGWA, BPCSUUPD.KEY);
        BPCPNHIS.INFO.TX_TYP_CD = "BPCUPF";
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TYP_CD);
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.OLD_DAT_PT = BPROXUPF;
        BPCPNHIS.INFO.NEW_DAT_PT = BPREXUPF;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_TXN_HIS, BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B300_OUTPUT_DATA_PROCESS() throws IOException,SQLException,Exception {
        if (BPCSUUPD.TYPE.equalsIgnoreCase("01")) {
            IBS.init(SCCGWA, BPCOEXRT);
            BPCOEXRT.KEY.BATCN_NO = BPREXUPF.KEY.BATCH_NO;
            BPCOEXRT.KEY.SEQ_NO = BPREXUPF.KEY.SEQ;
            WS_REC_LEN = 65;
            if (BPREXUPF.RECORD == null) BPREXUPF.RECORD = "";
            JIBS_tmp_int = BPREXUPF.RECORD.length();
            for (int i=0;i<1000-JIBS_tmp_int;i++) BPREXUPF.RECORD += " ";
            IBS.CPY2CLS(SCCGWA, BPREXUPF.RECORD.substring(0, WS_REC_LEN), BPCOEXRT.DATA);
            CEP.TRC(SCCGWA, BPCOEXRT.DATA);
            CEP.TRC(SCCGWA, BPCOEXRT.DATA.EXR_TYP);
            CEP.TRC(SCCGWA, BPCOEXRT.DATA.CRE_DT);
            CEP.TRC(SCCGWA, BPCOEXRT.DATA.CRE_TM);
            CEP.TRC(SCCGWA, BPCOEXRT.DATA.CCY);
            CEP.TRC(SCCGWA, BPCOEXRT.DATA.FWD_TENOR);
            CEP.TRC(SCCGWA, BPCOEXRT.DATA.MID_RATE);
            CEP.TRC(SCCGWA, BPCOEXRT.DATA.BUY_RATE);
            CEP.TRC(SCCGWA, BPCOEXRT.DATA.SELL_RATE);
            IBS.init(SCCGWA, SCCFMT);
            SCCFMT.FMTID = K_FMT_CD_EXRT;
            SCCFMT.DATA_PTR = BPCOEXRT;
            SCCFMT.DATA_LEN = 110;
            IBS.FMT(SCCGWA, SCCFMT);
        } else if (BPCSUUPD.TYPE.equalsIgnoreCase("02")) {
            IBS.init(SCCGWA, BPCOEHOL);
            BPCOEHOL.KEY.BATCN_NO = BPREXUPF.KEY.BATCH_NO;
            BPCOEHOL.KEY.SEQ_NO = BPREXUPF.KEY.SEQ;
            WS_REC_LEN = 19;
            if (BPREXUPF.RECORD == null) BPREXUPF.RECORD = "";
            JIBS_tmp_int = BPREXUPF.RECORD.length();
            for (int i=0;i<1000-JIBS_tmp_int;i++) BPREXUPF.RECORD += " ";
            IBS.CPY2CLS(SCCGWA, BPREXUPF.RECORD.substring(0, WS_REC_LEN), BPCOEHOL.DATA);
            IBS.init(SCCGWA, SCCFMT);
            SCCFMT.FMTID = K_FMT_CD_HOL;
            SCCFMT.DATA_PTR = BPCOEHOL;
            SCCFMT.DATA_LEN = 9239;
            IBS.FMT(SCCGWA, SCCFMT);
        } else if (BPCSUUPD.TYPE.equalsIgnoreCase("03")) {
            IBS.init(SCCGWA, AICOODEL);
            AICOODEL.KEY.BATCN_NO = BPREXUPF.KEY.BATCH_NO;
            AICOODEL.KEY.SEQ_NO = BPREXUPF.KEY.SEQ;
            AICOODEL.KEY.EXEC_TYPE = "03";
            WS_REC_LEN = 1215;
            if (BPREXUPF.RECORD == null) BPREXUPF.RECORD = "";
            JIBS_tmp_int = BPREXUPF.RECORD.length();
            for (int i=0;i<1000-JIBS_tmp_int;i++) BPREXUPF.RECORD += " ";
            IBS.CPY2CLS(SCCGWA, BPREXUPF.RECORD.substring(0, WS_REC_LEN), AICOODEL.DATA);
            IBS.init(SCCGWA, SCCFMT);
            SCCFMT.FMTID = K_FMT_CD_ODE;
            SCCFMT.DATA_PTR = AICOODEL;
            SCCFMT.DATA_LEN = 1262;
            IBS.FMT(SCCGWA, SCCFMT);
        } else if (BPCSUUPD.TYPE.equalsIgnoreCase("04")) {
            IBS.init(SCCGWA, BPCECNGL);
            BPCECNGL.KEY.BATCN_NO = BPREXUPF.KEY.BATCH_NO;
            BPCECNGL.KEY.SEQ_NO = BPREXUPF.KEY.SEQ;
            WS_REC_LEN = 34;
            if (BPREXUPF.RECORD == null) BPREXUPF.RECORD = "";
            JIBS_tmp_int = BPREXUPF.RECORD.length();
            for (int i=0;i<1000-JIBS_tmp_int;i++) BPREXUPF.RECORD += " ";
            IBS.CPY2CLS(SCCGWA, BPREXUPF.RECORD.substring(0, WS_REC_LEN), BPCECNGL.DAT);
            IBS.init(SCCGWA, SCCFMT);
            SCCFMT.FMTID = K_FMT_CD_AM;
            SCCFMT.DATA_PTR = BPCECNGL;
            SCCFMT.DATA_LEN = 79;
            IBS.FMT(SCCGWA, SCCFMT);
        } else if (BPCSUUPD.TYPE.equalsIgnoreCase("05")) {
            IBS.init(SCCGWA, BPCOERTE);
            BPCOERTE.KEY.BATCN_NO = BPREXUPF.KEY.BATCH_NO;
            BPCOERTE.KEY.SEQ_NO = BPREXUPF.KEY.SEQ;
            WS_REC_LEN = 40;
            if (BPREXUPF.RECORD == null) BPREXUPF.RECORD = "";
            JIBS_tmp_int = BPREXUPF.RECORD.length();
            for (int i=0;i<1000-JIBS_tmp_int;i++) BPREXUPF.RECORD += " ";
            IBS.CPY2CLS(SCCGWA, BPREXUPF.RECORD.substring(0, WS_REC_LEN), BPCOERTE.DATA);
            IBS.init(SCCGWA, SCCFMT);
            SCCFMT.FMTID = K_FMT_CD_RATE;
            SCCFMT.DATA_PTR = BPCOERTE;
            SCCFMT.DATA_LEN = 85;
            IBS.FMT(SCCGWA, SCCFMT);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID TYPE(" + BPCSUUPD.TYPE + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void S000_CALL_BPZRMUPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_MAIN_BPTEXUPF, BPCRMUPD);
        if (BPCRMUPD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRMUPD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZCEXC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-EXCEL-CHK", BPCEXCHK);
    }
    public void S000_CALL_BPZEXHOL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-EXCEL-HOL-CHECK", BPCEXCHK);
    }
    public void S000_CALL_AIZEODEL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-EXCEL-ODE-CHECK", BPCEXCHK);
    }
    public void S000_CALL_BPZUCEXL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-EXCEL-CNGL-CHK", BPCEXCHK);
    }
    public void S000_CALL_BPZEXCRT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-EXCEL-RATE-CHK", BPCEXCHK);
    }
    public void S000_CALL_SCZIMSG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-MSG-INQ", SCCIMSG);
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
