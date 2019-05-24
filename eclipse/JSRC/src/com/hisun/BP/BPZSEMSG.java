package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSEMSG {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_CMP_MAIN_BPTDIARY = "BP-R-MAINT-UPDATA";
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    short WS_J = 0;
    char WS_STOP = ' ';
    BPZSEMSG_WS_ERROR_MSG_DATA WS_ERROR_MSG_DATA = new BPZSEMSG_WS_ERROR_MSG_DATA();
    BPZSEMSG_WS_ERROR_ALL_MSG_DATA[] WS_ERROR_ALL_MSG_DATA = new BPZSEMSG_WS_ERROR_ALL_MSG_DATA[70];
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCOEHOL BPCOEHOL = new BPCOEHOL();
    BPREXMSG BPREXMSG = new BPREXMSG();
    BPCREMSG BPCREMSG = new BPCREMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    BPCSEMSG BPCSEMSG;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public BPZSEMSG() {
        for (int i=0;i<70;i++) WS_ERROR_ALL_MSG_DATA[i] = new BPZSEMSG_WS_ERROR_ALL_MSG_DATA();
    }
    public void MP(SCCGWA SCCGWA, BPCSEMSG BPCSEMSG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSEMSG = BPCSEMSG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSEMSG return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCSEMSG.FUNC == 'B') {
            B010_INQ_MSG_PROC();
            if (pgmRtn) return;
        } else if (BPCSEMSG.FUNC == 'Q') {
            B020_INQ_MSG_ALL_PROC();
            if (pgmRtn) return;
        } else if (BPCSEMSG.FUNC == 'I') {
            B030_INQ_MSG_PROC_2();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCSEMSG.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_INQ_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "OUT HEAD");
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 161;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "STARTBR");
        CEP.TRC(SCCGWA, BPCSEMSG.BATNO);
        CEP.TRC(SCCGWA, BPCSEMSG.SEQ);
        IBS.init(SCCGWA, BPREXMSG);
        IBS.init(SCCGWA, BPCREMSG);
        BPREXMSG.KEY.BATNO = BPCSEMSG.BATNO;
        BPREXMSG.KEY.SEQ = BPCSEMSG.SEQ;
        CEP.TRC(SCCGWA, BPREXMSG.KEY.BATNO);
        BPCREMSG.FUNC = 'Q';
        BPCREMSG.OPT = 'S';
        CEP.TRC(SCCGWA, "0");
        BPCREMSG.REC_LEN = 197;
        CEP.TRC(SCCGWA, "1");
        BPCREMSG.REC_PT = BPREXMSG;
        CEP.TRC(SCCGWA, "2");
        S000_CALL_BPZRXMSG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "READNEXT");
        BPCREMSG.OPT = 'R';
        S000_CALL_BPZRXMSG();
        if (pgmRtn) return;
        WS_STOP = 'N';
        while (WS_STOP != 'Y' 
            && SCCMPAG.FUNC != 'E') {
            CEP.TRC(SCCGWA, "READ NEXT RECORD");
            R000_OUTPUT_DATA_PROCESS();
            if (pgmRtn) return;
            BPCREMSG.OPT = 'R';
            S000_CALL_BPZRXMSG();
            if (pgmRtn) return;
        }
        BPCREMSG.OPT = 'E';
        S000_CALL_BPZRXMSG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BROWSE END");
    }
    public void B020_INQ_MSG_ALL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPREXMSG);
        IBS.init(SCCGWA, BPCREMSG);
        R000_GET_BATCH_NO();
        if (pgmRtn) return;
        BPCREMSG.FUNC = 'Q';
        BPCREMSG.OPT = 'A';
        BPCREMSG.REC_LEN = 197;
        BPCREMSG.REC_PT = BPREXMSG;
        S000_CALL_BPZRXMSG();
        if (pgmRtn) return;
        BPCREMSG.OPT = 'R';
        S000_CALL_BPZRXMSG();
        if (pgmRtn) return;
        WS_STOP = 'N';
        WS_I = 0;
        while (WS_STOP != 'Y' 
            && WS_I < 70) {
            CEP.TRC(SCCGWA, "11111");
            CEP.TRC(SCCGWA, BPREXMSG.KEY.SEQ);
            CEP.TRC(SCCGWA, BPREXMSG.KEY.NO);
            CEP.TRC(SCCGWA, BPREXMSG.ERROR_FIELD);
            CEP.TRC(SCCGWA, BPREXMSG.MSG_TXT);
            WS_I = (short) (WS_I + 1);
            WS_ERROR_ALL_MSG_DATA[WS_I-1].WS_O_SEQ = BPREXMSG.KEY.SEQ;
            WS_ERROR_ALL_MSG_DATA[WS_I-1].WS_O_NO = BPREXMSG.KEY.NO;
            WS_ERROR_ALL_MSG_DATA[WS_I-1].WS_O_ERROR_FIELD = BPREXMSG.ERROR_FIELD;
            WS_ERROR_ALL_MSG_DATA[WS_I-1].WS_O_ERROR_MSG_TXT = BPREXMSG.MSG_TXT;
            BPCREMSG.OPT = 'R';
            S000_CALL_BPZRXMSG();
            if (pgmRtn) return;
        }
        WS_J = WS_I;
        CEP.TRC(SCCGWA, WS_J);
        BPCREMSG.OPT = 'E';
        S000_CALL_BPZRXMSG();
        if (pgmRtn) return;
        R010_OUTPUT_MSG_PROCESS();
        if (pgmRtn) return;
    }
    public void B030_INQ_MSG_PROC_2() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "OUT HEAD");
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 161;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "STARTBR");
        CEP.TRC(SCCGWA, BPCSEMSG.BATNO);
        CEP.TRC(SCCGWA, BPCSEMSG.SEQ);
        IBS.init(SCCGWA, BPREXMSG);
        IBS.init(SCCGWA, BPCREMSG);
        BPREXMSG.KEY.BATNO = BPCSEMSG.BATNO;
        BPREXMSG.KEY.SEQ = BPCSEMSG.SEQ;
        CEP.TRC(SCCGWA, BPREXMSG.KEY.BATNO);
        BPCREMSG.FUNC = 'Q';
        BPCREMSG.OPT = 'B';
        CEP.TRC(SCCGWA, "0");
        BPCREMSG.REC_LEN = 197;
        CEP.TRC(SCCGWA, "1");
        BPCREMSG.REC_PT = BPREXMSG;
        CEP.TRC(SCCGWA, "2");
        S000_CALL_BPZRXMSG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "READNEXT");
        BPCREMSG.OPT = 'R';
        S000_CALL_BPZRXMSG();
        if (pgmRtn) return;
        WS_STOP = 'N';
        while (WS_STOP != 'Y') {
            CEP.TRC(SCCGWA, "READ NEXT RECORD");
            R000_OUTPUT_DATA_PROCESS();
            if (pgmRtn) return;
            BPCREMSG.OPT = 'R';
            S000_CALL_BPZRXMSG();
            if (pgmRtn) return;
        }
        BPCREMSG.OPT = 'E';
        S000_CALL_BPZRXMSG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BROWSE END");
    }
    public void R000_OUTPUT_DATA_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "OUTPUT");
        IBS.init(SCCGWA, WS_ERROR_MSG_DATA);
        WS_ERROR_MSG_DATA.WS_SEQ = BPREXMSG.KEY.SEQ;
        WS_ERROR_MSG_DATA.WS_NO = BPREXMSG.KEY.NO;
        WS_ERROR_MSG_DATA.WS_ERROR_FIELD = BPREXMSG.ERROR_FIELD;
        WS_ERROR_MSG_DATA.WS_ERROR_MSG_TXT = BPREXMSG.MSG_TXT;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_ERROR_MSG_DATA);
        SCCMPAG.DATA_LEN = 161;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R010_OUTPUT_MSG_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOEHOL);
        BPCOEHOL.KEY.BATCN_NO = BPCSEMSG.BATNO;
        BPCOEHOL.CNT = 0;
        for (WS_I = 1; WS_I <= WS_J 
            && WS_I <= 70; WS_I += 1) {
            CEP.TRC(SCCGWA, "AAAAAAA");
            BPCOEHOL.UPD_DATA[WS_I-1].SEQ = WS_ERROR_ALL_MSG_DATA[WS_I-1].WS_O_SEQ;
            BPCOEHOL.UPD_DATA[WS_I-1].NO = WS_ERROR_ALL_MSG_DATA[WS_I-1].WS_O_NO;
            BPCOEHOL.UPD_DATA[WS_I-1].FIELD = WS_ERROR_ALL_MSG_DATA[WS_I-1].WS_O_ERROR_FIELD;
            BPCOEHOL.UPD_DATA[WS_I-1].MSG = WS_ERROR_ALL_MSG_DATA[WS_I-1].WS_O_ERROR_MSG_TXT;
            if (BPCOEHOL.UPD_DATA[WS_I-1].MSG.trim().length() > 0) {
                BPCOEHOL.CNT = BPCOEHOL.CNT + 1;
            }
            CEP.TRC(SCCGWA, BPCOEHOL.UPD_DATA[WS_I-1].SEQ);
            CEP.TRC(SCCGWA, BPCOEHOL.UPD_DATA[WS_I-1].NO);
            CEP.TRC(SCCGWA, BPCOEHOL.UPD_DATA[WS_I-1].FIELD);
            CEP.TRC(SCCGWA, BPCOEHOL.UPD_DATA[WS_I-1].MSG);
            CEP.TRC(SCCGWA, BPCOEHOL.CNT);
        }
        CEP.TRC(SCCGWA, "111111111111111111111111");
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "BP754";
        SCCFMT.DATA_PTR = BPCOEHOL;
        SCCFMT.DATA_LEN = 9239;
        CEP.TRC(SCCGWA, "YAO");
        CEP.TRC(SCCGWA, BPCOEHOL.CNT);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZRXMSG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-EXL-MSG", BPCREMSG);
        CEP.TRC(SCCGWA, BPCREMSG.RC.RC_CODE);
        if (BPCREMSG.RETURN_INFO == 'N') {
            WS_STOP = 'Y';
        }
    }
    public void R000_GET_BATCH_NO() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 36; WS_I += 1) {
            if (BPCSEMSG.BATNO == null) BPCSEMSG.BATNO = "";
            JIBS_tmp_int = BPCSEMSG.BATNO.length();
            for (int i=0;i<36-JIBS_tmp_int;i++) BPCSEMSG.BATNO += " ";
            if (BPCSEMSG.BATNO.substring(WS_I - 1, WS_I + 1 - 1).trim().length() == 0) {
                WS_J = WS_I;
                WS_I = 36;
            }
        }
        WS_I = (short) (WS_J - 1);
        if (WS_J == 1) {
            BPREXMSG.KEY.BATNO = "" + SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            JIBS_tmp_int = BPREXMSG.KEY.BATNO.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPREXMSG.KEY.BATNO = "0" + BPREXMSG.KEY.BATNO;
        } else {
            if (BPCSEMSG.BATNO == null) BPCSEMSG.BATNO = "";
            JIBS_tmp_int = BPCSEMSG.BATNO.length();
            for (int i=0;i<36-JIBS_tmp_int;i++) BPCSEMSG.BATNO += " ";
            if (BPCSEMSG.BATNO.substring(36 - 1, 36 + 1 - 1).trim().length() > 0) {
                BPREXMSG.KEY.BATNO = BPCSEMSG.BATNO;
            } else {
                WS_I = (short) (WS_J - 1);
                if (BPREXMSG.KEY.BATNO == null) BPREXMSG.KEY.BATNO = "";
                JIBS_tmp_int = BPREXMSG.KEY.BATNO.length();
                for (int i=0;i<36-JIBS_tmp_int;i++) BPREXMSG.KEY.BATNO += " ";
                if (BPCSEMSG.BATNO == null) BPCSEMSG.BATNO = "";
                JIBS_tmp_int = BPCSEMSG.BATNO.length();
                for (int i=0;i<36-JIBS_tmp_int;i++) BPCSEMSG.BATNO += " ";
                BPREXMSG.KEY.BATNO = BPCSEMSG.BATNO + BPREXMSG.KEY.BATNO.substring(WS_I);
                WS_I = (short) (36 - WS_I);
                if (BPREXMSG.KEY.BATNO == null) BPREXMSG.KEY.BATNO = "";
                JIBS_tmp_int = BPREXMSG.KEY.BATNO.length();
                for (int i=0;i<36-JIBS_tmp_int;i++) BPREXMSG.KEY.BATNO += " ";
                JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                BPREXMSG.KEY.BATNO = BPREXMSG.KEY.BATNO.substring(0, WS_J - 1) + JIBS_tmp_str[0] + BPREXMSG.KEY.BATNO.substring(WS_J + WS_I - 1);
            }
        }
        CEP.TRC(SCCGWA, BPREXMSG.KEY.BATNO);
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
