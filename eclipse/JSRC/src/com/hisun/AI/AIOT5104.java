package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT5104 {
    int JIBS_tmp_int;
    char WK_FUNC = ' ';
    String PGM_SCSSCKDT = "SCSSCKDT";
    String AI_HIS_REMARKS = "GL A/C NO. MAINTAIN HISTORY";
    String K_CPY_AIRITM = "AIRITM";
    char WK_MEMO_FLG = '8';
    String WS_ERR_MSG = " ";
    char WS_ITM_AWA = ' ';
    String WS_COA_NO = " ";
    int WS_ITM_LEN = 0;
    AIOT5104_WS_ITM_46_NO WS_ITM_46_NO = new AIOT5104_WS_ITM_46_NO();
    AIOT5104_WS_ITM_64_NO WS_ITM_64_NO = new AIOT5104_WS_ITM_64_NO();
    AIOT5104_WS_ITM_424_NO WS_ITM_424_NO = new AIOT5104_WS_ITM_424_NO();
    AIOT5104_WS_ITM_19_NO WS_ITM_19_NO = new AIOT5104_WS_ITM_19_NO();
    int WS_INT_X = 0;
    int WS_INT_Y = 0;
    int WS_INT_Z = 0;
    AIOT5104_WS_ITM_L WS_ITM_L = new AIOT5104_WS_ITM_L();
    char WS_AIRITM_FLG = ' ';
    char WS_END_FLG = ' ';
    char WS_GET_LEN_FLG = ' ';
    char WS_AITCMIB_FLG = ' ';
    char WS_MST_BAL_FLG = ' ';
    char WS_READ_ITM_CTL = ' ';
    char WS_AIRCMIB_FLG = ' ';
    char WS_AIRMIB_FLG = ' ';
    char WS_AIRGINF_FLG = ' ';
    BPCQBKPM BPCQBKPM = new BPCQBKPM();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AICSITAD AICSITAD = new AICSITAD();
    AIRITM AIROITM = new AIRITM();
    AIRITM AIRNITM = new AIRITM();
    AIRMIB AIRMIB = new AIRMIB();
    AIRCMIB AIRMCMIB = new AIRCMIB();
    AICPCITM AICCITM = new AICPCITM();
    AIRGINF AIRGINF = new AIRGINF();
    AICSCMIB AICSCMIB = new AICSCMIB();
    AICRCMIB AICRCMIB = new AICRCMIB();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCCKDT SCCCKDT = new SCCCKDT();
    AIRITM AIRITM = new AIRITM();
    AIRCMIB AIRCMIB = new AIRCMIB();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AIB0005_AWA_0005 AIB0005_AWA_0005;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIOT5104 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB0005_AWA_0005>");
        AIB0005_AWA_0005 = (AIB0005_AWA_0005) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_MAINTAIN_REOPEN_ITM_PROC();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (AIB0005_AWA_0005.FUNC == ' ' 
            || AIB0005_AWA_0005.COA_FLG.trim().length() == 0 
            || AIB0005_AWA_0005.COA_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, AIB0005_AWA_0005.FUNC);
            CEP.TRC(SCCGWA, AIB0005_AWA_0005.COA_FLG);
            CEP.TRC(SCCGWA, AIB0005_AWA_0005.COA_NO);
            CEP.TRC(SCCGWA, AIB0005_AWA_0005.CNAME);
            CEP.TRC(SCCGWA, AIB0005_AWA_0005.COA_TYP);
            CEP.TRC(SCCGWA, AIB0005_AWA_0005.POSTTYP);
            CEP.TRC(SCCGWA, AIB0005_AWA_0005.ITM_LVL);
            CEP.TRC(SCCGWA, AIB0005_AWA_0005.LOW_IND);
            CEP.TRC(SCCGWA, AIB0005_AWA_0005.MIB_FLG);
            CEP.TRC(SCCGWA, AIB0005_AWA_0005.ODE_FLG);
            CEP.TRC(SCCGWA, AIB0005_AWA_0005.DR_CR);
            CEP.TRC(SCCGWA, AIB0005_AWA_0005.RED_FLG);
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.MUST_INPUT);
        }
        CEP.TRC(SCCGWA, AIB0005_AWA_0005.FUNC);
        WK_FUNC = AIB0005_AWA_0005.FUNC;
        if (WK_FUNC != 'R') {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_IPT_FUNC_ERR);
        }
        CEP.TRC(SCCGWA, AIB0005_AWA_0005.COA_FLG);
        if (AIB0005_AWA_0005.COA_FLG.trim().length() > 0) {
            IBS.init(SCCGWA, BPCQBKPM);
            BPCQBKPM.FUNC = 'B';
            BPCQBKPM.COA_TYP = AIB0005_AWA_0005.COA_FLG;
            S000_CALL_BPZQBKPM();
            WS_ITM_LEN = BPCQBKPM.DATA[1-1].ITM_LEN;
        }
    }
    public void B200_MAINTAIN_REOPEN_ITM_PROC() throws IOException,SQLException,Exception {
        B210_CHK_ITM_ATTR();
        B220_REOPEN_ITM();
    }
    public void B210_CHK_ITM_ATTR() throws IOException,SQLException,Exception {
        WS_INT_Z = 0;
        WS_GET_LEN_FLG = ' ';
        CEP.TRC(SCCGWA, WS_GET_LEN_FLG);
        for (WS_INT_X = 1; WS_INT_X <= 10 
            && WS_GET_LEN_FLG != 'Y'; WS_INT_X += 1) {
            if (AIB0005_AWA_0005.COA_NO == null) AIB0005_AWA_0005.COA_NO = "";
            JIBS_tmp_int = AIB0005_AWA_0005.COA_NO.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) AIB0005_AWA_0005.COA_NO += " ";
            CEP.TRC(SCCGWA, AIB0005_AWA_0005.COA_NO.substring(WS_INT_X - 1, WS_INT_X + 1 - 1));
            if (AIB0005_AWA_0005.COA_NO == null) AIB0005_AWA_0005.COA_NO = "";
            JIBS_tmp_int = AIB0005_AWA_0005.COA_NO.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) AIB0005_AWA_0005.COA_NO += " ";
            if (!IBS.isNumeric(AIB0005_AWA_0005.COA_NO.substring(WS_INT_X - 1, WS_INT_X + 1 - 1))) {
                WS_GET_LEN_FLG = 'Y';
            } else {
                WS_INT_Z += 1;
            }
        }
        if (WS_INT_Z == 3) {
            WS_ITM_19_NO.WS_FST1_NO = AIB0005_AWA_0005.COA_TYP.charAt(0);
            WS_ITM_19_NO.WS_TMP9_NO = AIB0005_AWA_0005.COA_NO;
            AIB0005_AWA_0005.COA_NO = IBS.CLS2CPY(SCCGWA, WS_ITM_19_NO);
        } else if (WS_INT_Z == 7
            || WS_INT_Z == 9) {
            WS_ITM_19_NO.WS_FST1_NO = AIB0005_AWA_0005.COA_TYP.charAt(0);
            WS_ITM_19_NO.WS_TMP9_NO = AIB0005_AWA_0005.COA_NO;
            AIB0005_AWA_0005.COA_NO = IBS.CLS2CPY(SCCGWA, WS_ITM_19_NO);
            WS_ITM_19_NO.WS_TMP9_NO = AIB0005_AWA_0005.UP_ITM;
            AIB0005_AWA_0005.UP_ITM = IBS.CLS2CPY(SCCGWA, WS_ITM_19_NO);
        }
    }
    public void B220_REOPEN_ITM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRITM);
        IBS.init(SCCGWA, AICCITM);
        AICCITM.INPUT_DATA.COA_FLG = AIB0005_AWA_0005.COA_FLG;
        AICCITM.INPUT_DATA.COA_NO = AIB0005_AWA_0005.COA_NO;
        AICCITM.INPUT_DATA.FUNC = 'R';
        S000_CALL_AIZPCITM();
        R000_TRANS_ITAD();
    }
    public void S000_CALL_BPZQBKPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-Q-MAINT-AMBKP", BPCQBKPM);
        CEP.TRC(SCCGWA, BPCQBKPM.RC);
        if (BPCQBKPM.RC.RC_RTNCODE != 0) {
            CEP.ERR(SCCGWA, BPCQBKPM.RC);
        }
    }
    public void R000_TRANS_ITAD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICSITAD);
        AICSITAD.COMM_DATA.ADJ_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICSITAD.COMM_DATA.COA_TYP = AIB0005_AWA_0005.COA_FLG;
        AICSITAD.COMM_DATA.ITM_OLD = AIB0005_AWA_0005.COA_NO;
        AICSITAD.COMM_DATA.ITM_NEW = AIB0005_AWA_0005.COA_NO;
        AICSITAD.COMM_DATA.FUNC_FLG = AIB0005_AWA_0005.FUNC;
        AICSITAD.COMM_DATA.ITMNEW_D = AIB0005_AWA_0005.CNAME;
        AICSITAD.COMM_DATA.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        AICSITAD.FUNC = 'A';
        S000_CALL_AIZSITAD();
    }
    public void S000_CALL_AIZSITAD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-SVR-AIZSITAD", AICSITAD);
        if (AICSITAD.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, AICSITAD.RC);
        }
    }
    public void S000_CALL_AIZPCITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-DEAL-CITM", AICCITM);
        if (AICCITM.RC.RTNCODE != 0) {
            CEP.ERR(SCCGWA, AICCITM.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
