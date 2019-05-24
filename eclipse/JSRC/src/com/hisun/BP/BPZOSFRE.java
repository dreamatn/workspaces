package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZOSFRE {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_INQ_PUB_PARM = "BP-PARM-READ      ";
    String CPN_P_INQ_PC = "BP-P-INQ-PC         ";
    BPZOSFRE_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZOSFRE_WS_TEMP_VARIABLE();
    BPZOSFRE_WS_RC WS_RC = new BPZOSFRE_WS_RC();
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_NEXT_FLG = ' ';
    char WS_CURRENT_FLG = ' ';
    char WS_EOF_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPROSINT BPROSINT = new BPROSINT();
    BPREXRT BPREXRT = new BPREXRT();
    BPRPARM BPRPARM = new BPRPARM();
    BPROSFRE BPROSFRE = new BPROSFRE();
    TCCASMSG TCCASMSG = new TCCASMSG();
    BPRSFRE BPRSFRE = new BPRSFRE();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    SCCGWA SCCGWA;
    BPCOSFRE BPCOSFRE;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCOSFRE BPCOSFRE) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOSFRE = BPCOSFRE;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZOSFRE return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCRMBPM);
        BPCRMBPM.RC.RC_MMO = "BP";
        BPCRMBPM.PTR = BPRPARM;
        IBS.init(SCCGWA, BPROSFRE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCOSFRE.FUNC == 'A') {
            B010_CHECK_INPUT();
            if (pgmRtn) return;
        }
        if (BPROSFRE.DATA_TXT.UPD_IND != '2') {
            R020_LINK_TCCASMSG();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCOSFRE.VAL == null) BPCOSFRE.VAL = "";
        JIBS_tmp_int = BPCOSFRE.VAL.length();
        for (int i=0;i<9500-JIBS_tmp_int;i++) BPCOSFRE.VAL += " ";
        IBS.CPY2CLS(SCCGWA, BPCOSFRE.VAL.substring(0, BPCOSFRE.LEN), BPROSFRE.DATA_TXT);
        CEP.TRC(SCCGWA, BPCOSFRE.VAL);
        CEP.TRC(SCCGWA, BPROSFRE.DATA_TXT);
        BPROSFRE.KEY.CD = BPCOSFRE.CODE;
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = "RBASE";
        BPCOQPCD.INPUT_DATA.CODE = BPROSFRE.KEY.CD;
        CEP.TRC(SCCGWA, BPROSFRE.KEY.CD);
        CEP.TRC(SCCGWA, BPCOQPCD.INPUT_DATA.CODE);
        S000_CALL_BPZPQPCD();
        if (pgmRtn) return;
        if (BPROSFRE.DATA_TXT.UPD_IND != '0' 
            && BPROSFRE.DATA_TXT.UPD_IND != '1') {
            CEP.TRC(SCCGWA, "11111111111");
            CEP.TRC(SCCGWA, BPROSFRE.DATA_TXT.UPD_IND);
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UPD_FLG_INPUT_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPROSFRE.DATA_TXT.UPD_IND == '0') {
            if (BPROSFRE.DATA_TXT.UPD_APPO_TM == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UPD_APP_TM_MUSTINPUT;
            }
        }
        if (BPROSFRE.DATA_TXT.UPD_IND == '1') {
            if (BPROSFRE.DATA_TXT.UPD_STR_TM == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UPD_STR_TM_MUSTINPUT;
            }
            if (BPROSFRE.DATA_TXT.UPD_END_TM == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UPD_END_TM_MUSTINPUT;
            }
            if (BPROSFRE.DATA_TXT.UPD_CYC == ' ') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UPD_CYC_MUSTINPUT;
            }
            if (BPROSFRE.DATA_TXT.UPD_FREQ == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UPD_FREQ_MUSTINPUT;
            }
        }
    }
    public void R020_LINK_TCCASMSG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TCCASMSG);
        IBS.init(SCCGWA, BPRSFRE);
        CEP.TRC(SCCGWA, BPROSFRE.DATA_TXT.FUNCTION);
        CEP.TRC(SCCGWA, BPRSFRE.FUNC);
        if (BPROSFRE.DATA_TXT.FUNCTION == 'A') {
            BPRSFRE.FUNC = 'A';
        }
        if (BPROSFRE.DATA_TXT.FUNCTION == 'D') {
            BPRSFRE.FUNC = 'D';
        }
        if (BPROSFRE.DATA_TXT.FUNCTION == 'M') {
            BPRSFRE.FUNC = 'M';
        }
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPRSFRE.DATA.BANK_NO = JIBS_tmp_str[0].substring(0, 3);
        B010_STARTBR_PARM();
        if (pgmRtn) return;
        B015_READNEXT_PARM();
        if (pgmRtn) return;
        WS_EOF_FLG = 'N';
        while (BPCRMBPM.RETURN_INFO != 'L' 
            && WS_EOF_FLG != 'Y') {
            if (BPRPARM.BLOB_VAL == null) BPRPARM.BLOB_VAL = "";
            JIBS_tmp_int = BPRPARM.BLOB_VAL.length();
            for (int i=0;i<9500-JIBS_tmp_int;i++) BPRPARM.BLOB_VAL += " ";
            CEP.TRC(SCCGWA, BPRPARM.BLOB_VAL.substring(0, 3));
            CEP.TRC(SCCGWA, BPROSFRE.KEY.CD);
            if (BPRPARM.BLOB_VAL == null) BPRPARM.BLOB_VAL = "";
            JIBS_tmp_int = BPRPARM.BLOB_VAL.length();
            for (int i=0;i<9500-JIBS_tmp_int;i++) BPRPARM.BLOB_VAL += " ";
            if (!BPRPARM.BLOB_VAL.substring(0, 3).equalsIgnoreCase(BPROSFRE.KEY.CD)) {
                WS_EOF_FLG = 'N';
            } else {
                if (BPRPARM.KEY.CODE == null) BPRPARM.KEY.CODE = "";
                JIBS_tmp_int = BPRPARM.KEY.CODE.length();
                for (int i=0;i<40-JIBS_tmp_int;i++) BPRPARM.KEY.CODE += " ";
                BPRSFRE.DATA.OS_BASE_TYP = BPRPARM.KEY.CODE.substring(0, 8);
                if (BPRPARM.KEY.CODE == null) BPRPARM.KEY.CODE = "";
                JIBS_tmp_int = BPRPARM.KEY.CODE.length();
                for (int i=0;i<40-JIBS_tmp_int;i++) BPRPARM.KEY.CODE += " ";
                CEP.TRC(SCCGWA, BPRPARM.KEY.CODE.substring(0, 8));
                WS_EOF_FLG = 'Y';
            }
            CEP.TRC(SCCGWA, WS_EOF_FLG);
            B015_READNEXT_PARM();
            if (pgmRtn) return;
        }
        if (BPRPARM.BLOB_VAL == null) BPRPARM.BLOB_VAL = "";
        JIBS_tmp_int = BPRPARM.BLOB_VAL.length();
        for (int i=0;i<9500-JIBS_tmp_int;i++) BPRPARM.BLOB_VAL += " ";
        if (BPCRMBPM.RETURN_INFO == 'L' 
            && !BPRPARM.BLOB_VAL.substring(0, 3).equalsIgnoreCase(BPROSFRE.KEY.CD)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_TYPE_NOT_FOUND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B020_ENDBR_PARM();
        if (pgmRtn) return;
        BPRSFRE.DATA.BASE_TYPE = BPROSFRE.KEY.CD;
        CEP.TRC(SCCGWA, BPROSFRE.KEY.CD);
        BPRSFRE.DATA.UPD_IND = BPROSFRE.DATA_TXT.UPD_IND;
        BPRSFRE.DATA.UPD_APPO_TM = BPROSFRE.DATA_TXT.UPD_APPO_TM;
        BPRSFRE.DATA.UPD_STR_TM = BPROSFRE.DATA_TXT.UPD_STR_TM;
        BPRSFRE.DATA.UPD_END_TM = BPROSFRE.DATA_TXT.UPD_END_TM;
        BPRSFRE.DATA.UPD_CYC = BPROSFRE.DATA_TXT.UPD_CYC;
        BPRSFRE.DATA.UPD_FREQ = BPROSFRE.DATA_TXT.UPD_FREQ;
        CEP.TRC(SCCGWA, BPRSFRE.DATA.BANK_NO);
        CEP.TRC(SCCGWA, BPRSFRE.DATA.OS_BASE_TYP);
        CEP.TRC(SCCGWA, BPRSFRE.DATA.BASE_TYPE);
        CEP.TRC(SCCGWA, BPRSFRE.DATA.UPD_IND);
        CEP.TRC(SCCGWA, BPRSFRE.DATA.UPD_APPO_TM);
        CEP.TRC(SCCGWA, BPRSFRE.DATA.UPD_STR_TM);
        CEP.TRC(SCCGWA, BPRSFRE.DATA.UPD_END_TM);
        CEP.TRC(SCCGWA, BPRSFRE.DATA.UPD_CYC);
        CEP.TRC(SCCGWA, BPRSFRE.DATA.UPD_FREQ);
        TCCASMSG.REQ_APP = "INTGRT";
        TCCASMSG.MSG_CODE = "TCIR001";
        TCCASMSG.ENA_MODE = '1';
        TCCASMSG.LEN = 39;
        TCCASMSG.DATA = IBS.CLS2CPY(SCCGWA, BPRSFRE);
        CEP.TRC(SCCGWA, TCCASMSG.DATA);
        CEP.TRC(SCCGWA, TCCASMSG.REQ_APP);
        CEP.TRC(SCCGWA, TCCASMSG.MSG_CODE);
        IBS.CALLCPN(SCCGWA, "TC-ASYNC-MSG", TCCASMSG);
        CEP.TRC(SCCGWA, TCCASMSG.RTNCODE);
        if (TCCASMSG.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_RC);
            JIBS_tmp_str[1] = "" + TCCASMSG.RTNCODE;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_tmp_str[0] = JIBS_tmp_str[0].substring(0, 3 - 1) + JIBS_tmp_str[1] + JIBS_tmp_str[0].substring(3 + 4 - 1);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[1], WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_PC, BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_STARTBR_PARM() throws IOException,SQLException,Exception {
        BPCRMBPM.FUNC = 'S';
        BPRPARM.KEY.TYPE = "OSINT";
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
    }
    public void B015_READNEXT_PARM() throws IOException,SQLException,Exception {
        BPCRMBPM.FUNC = 'R';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
    }
    public void B020_ENDBR_PARM() throws IOException,SQLException,Exception {
        BPCRMBPM.FUNC = 'E';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = BPREXRT;
        IBS.CALLCPN(SCCGWA, CPN_INQ_PUB_PARM, BPCPRMR);
    }
    public void S000_CALL_BPZRMBPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MBRW-PARM", BPCRMBPM);
        if (BPCRMBPM.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCRMBPM.RC);
            CEP.ERR(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCOSFRE.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCOSFRE = ");
            CEP.TRC(SCCGWA, BPCOSFRE);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
