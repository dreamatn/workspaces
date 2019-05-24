package com.hisun.BP;

import com.hisun.EQ.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5137 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_S_SRC_IRATE_UPD = "BP-S-SRC-IRATE-UPD ";
    String K_PARM_CCY = "CCY";
    int K_PARM_BR = "999999";
    String K_PARMC = "PARMC";
    String CPN_R_FEE_BPZRMBPM = "BP-R-MBRW-PARM      ";
    String K_OUTPUT_FMT = "BP833";
    String CPN_INQ_BRW_CCY = "BP-R-BRW-CCY";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_CCY = " ";
    String WS_CCY_CD = " ";
    String WS_CCY_NAME = " ";
    String WS_BASE_NAME = " ";
    BPOT5137_WS_BASE_NAME_R WS_BASE_NAME_R = new BPOT5137_WS_BASE_NAME_R();
    String WS_TENO_NAME = " ";
    int WS_CCY_CNT = 0;
    String WS_BASE_TYP = " ";
    String WS_TENO = " ";
    short WS_I = 0;
    short WS_CNT1 = 0;
    int WS_EFF_DT = 0;
    char WS_CCY_CD_FLG = ' ';
    char WS_CCY_FLG = ' ';
    BPOT5137_WS_RC WS_RC = new BPOT5137_WS_RC();
    EQCMSG_ERROR_MSG EQCMSG_ERROR_MSG = new EQCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPRPRMT BPRPCCY = new BPRPRMT();
    BPRPARM BPRMBPM = new BPRPARM();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCSSRCU BPCSSRCU = new BPCSSRCU();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPRBCCY BPRBCCY = new BPRBCCY();
    BPCRBCCY BPCRBCCY = new BPCRBCCY();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB5137_AWA_5137 BPB5137_AWA_5137;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT5137 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5137_AWA_5137>");
        BPB5137_AWA_5137 = (BPB5137_AWA_5137) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB5137_AWA_5137.IR_NAME);
        CEP.TRC(SCCGWA, BPB5137_AWA_5137.IR_CODE);
        CEP.TRC(SCCGWA, BPB5137_AWA_5137.CCY_NAME);
        CEP.TRC(SCCGWA, BPB5137_AWA_5137.REF_IND);
        CEP.TRC(SCCGWA, BPB5137_AWA_5137.PER_NAME);
        CEP.TRC(SCCGWA, BPB5137_AWA_5137.BID);
        CEP.TRC(SCCGWA, BPB5137_AWA_5137.START_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, "*** TRANSFER CCY ***");
        IBS.init(SCCGWA, BPRBCCY);
        IBS.init(SCCGWA, BPCRBCCY);
        WS_CCY_CD_FLG = 'C';
        BPRBCCY.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        BPRBCCY.KEY.CD = "000";
        WS_CCY_NAME = BPB5137_AWA_5137.CCY_NAME;
        WS_EFF_DT = 19000101;
        CEP.TRC(SCCGWA, "*** CALL BPZRBCCY ***");
        T000_STARTBR_BPZRBCCY();
        if (pgmRtn) return;
        T000_READNEXT_BPZRBCCY();
        if (pgmRtn) return;
        for (WS_CNT1 = 1; WS_CNT1 <= 99 
            && WS_CCY_CD_FLG != 'S' 
            && BPCRBCCY.RETURN_INFO != 'N'; WS_CNT1 += 1) {
            CEP.TRC(SCCGWA, BPRBCCY.KEY.CD);
            CEP.TRC(SCCGWA, BPRBCCY.DESC);
            if (BPRBCCY.DESC.equalsIgnoreCase(WS_CCY_NAME)) {
                WS_CCY_CD_FLG = 'S';
                WS_CCY_CD = BPRBCCY.KEY.CD;
            }
            T000_READNEXT_BPZRBCCY();
            if (pgmRtn) return;
        }
        T000_ENDBR_BPZRBCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_CNT1);
        CEP.TRC(SCCGWA, WS_CCY_CD_FLG);
        if (WS_CNT1 > 99 
            || WS_CCY_CD_FLG == 'C') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CCY_REC_NOTFND, WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "*** TRANSFER BASE-TYP ***");
        BPRPCCY.KEY.CD = " ";
        IBS.init(SCCGWA, BPRMBPM);
        WS_CCY_CD_FLG = 'C';
        BPRPCCY.KEY.TYP = K_PARMC;
        BPRPCCY.KEY.CD = "RBASE000";
        WS_BASE_NAME = BPB5137_AWA_5137.REF_IND;
        IBS.CPY2CLS(SCCGWA, BPB5137_AWA_5137.REF_IND, WS_BASE_NAME_R);
        if (WS_BASE_NAME_R.WS_SUB_BR > 0 
            && WS_BASE_NAME_R.WS_SUB_BR < 999999) {
            CEP.TRC(SCCGWA, "*** SUB-BR CHECK ***");
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
            BPCPQORG.BR = WS_BASE_NAME_R.WS_SUB_BR;
            WS_BASE_NAME = WS_BASE_NAME_R.WS_BASE_NAME_2;
            CEP.TRC(SCCGWA, WS_BASE_NAME_R.WS_SUB_BR);
            CEP.TRC(SCCGWA, WS_BASE_NAME);
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            if (BPCPQORG.RC.RC_CODE != 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_ORG_NOTFND, WS_RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            CEP.TRC(SCCGWA, WS_BASE_NAME_R.WS_SUB_BR);
            CEP.TRC(SCCGWA, WS_BASE_NAME);
            WS_BASE_NAME_R.WS_SUB_BR = 0;
        }
        WS_EFF_DT = 19000101;
        CEP.TRC(SCCGWA, "*** CALL BPRMBPM ***");
        BPCRMBPM.PTR = BPRMBPM;
        T000_STARTBR_BPRPCCY();
        if (pgmRtn) return;
        T000_READNEXT_BPRPCCY();
        if (pgmRtn) return;
        for (WS_CNT1 = 1; WS_CCY_CD_FLG != 'S' 
            && BPCRMBPM.RETURN_INFO != 'L' 
            && BPCRMBPM.RETURN_INFO != 'N'; WS_CNT1 += 1) {
            CEP.TRC(SCCGWA, BPRMBPM.KEY.CODE);
            CEP.TRC(SCCGWA, BPRMBPM.DESC);
            CEP.TRC(SCCGWA, WS_BASE_NAME);
            if (BPRMBPM.DESC.equalsIgnoreCase(WS_BASE_NAME)) {
                WS_CCY_CD_FLG = 'S';
                if (BPRMBPM.KEY.CODE == null) BPRMBPM.KEY.CODE = "";
                JIBS_tmp_int = BPRMBPM.KEY.CODE.length();
                for (int i=0;i<40-JIBS_tmp_int;i++) BPRMBPM.KEY.CODE += " ";
                WS_BASE_TYP = BPRMBPM.KEY.CODE.substring(6 - 1, 6 + 3 - 1);
            }
            T000_READNEXT_BPRPCCY();
            if (pgmRtn) return;
        }
        T000_ENDBR_BPRPCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_CNT1);
        CEP.TRC(SCCGWA, WS_CCY_CD_FLG);
        if (WS_CNT1 > 9999 
            || WS_CCY_CD_FLG == 'C') {
            CEP.TRC(SCCGWA, "*** BASE-TYP NOT FOUND ***");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BASE_RATE_NOTFND, WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_BASE_TYP);
        CEP.TRC(SCCGWA, "*** TRANSFER TENO ***");
        BPRPCCY.KEY.CD = " ";
        IBS.init(SCCGWA, BPRMBPM);
        WS_CCY_CD_FLG = 'C';
        BPRPCCY.KEY.TYP = K_PARMC;
        BPRPCCY.KEY.CD = "RTENO0000";
        WS_TENO_NAME = BPB5137_AWA_5137.PER_NAME;
        WS_EFF_DT = 19000101;
        CEP.TRC(SCCGWA, "*** CALL BPRMBPM ***");
        BPCRMBPM.PTR = BPRMBPM;
        T000_STARTBR_BPRPCCY();
        if (pgmRtn) return;
        T000_READNEXT_BPRPCCY();
        if (pgmRtn) return;
        for (WS_CNT1 = 1; WS_CCY_CD_FLG != 'S' 
            && BPCRMBPM.RETURN_INFO != 'L' 
            && BPCRMBPM.RETURN_INFO != 'N'; WS_CNT1 += 1) {
            CEP.TRC(SCCGWA, BPRMBPM.KEY.CODE);
            CEP.TRC(SCCGWA, BPRMBPM.DESC);
            CEP.TRC(SCCGWA, WS_TENO_NAME);
            CEP.TRC(SCCGWA, "NCB1026001");
            if (BPRMBPM.DESC.equalsIgnoreCase(WS_TENO_NAME)) {
                WS_CCY_CD_FLG = 'S';
                if (BPRMBPM.KEY.CODE == null) BPRMBPM.KEY.CODE = "";
                JIBS_tmp_int = BPRMBPM.KEY.CODE.length();
                for (int i=0;i<40-JIBS_tmp_int;i++) BPRMBPM.KEY.CODE += " ";
                WS_TENO = BPRMBPM.KEY.CODE.substring(6 - 1, 6 + 4 - 1);
            }
            T000_READNEXT_BPRPCCY();
            if (pgmRtn) return;
        }
        T000_ENDBR_BPRPCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_CNT1);
        CEP.TRC(SCCGWA, WS_CCY_CD_FLG);
        if (WS_CNT1 > 9999 
            || WS_CCY_CD_FLG == 'C') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TENOR_ERR, WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_TENO);
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSSRCU);
        if (WS_BASE_NAME_R.WS_SUB_BR > 0) {
            BPCSSRCU.BR = WS_BASE_NAME_R.WS_SUB_BR;
        } else {
            BPCSSRCU.BR = K_PARM_BR;
        }
        BPCSSRCU.BASE_TYP = WS_BASE_TYP;
        BPCSSRCU.TBL_A[1-1].CCY = WS_CCY_CD;
        if (BPB5137_AWA_5137.START_DT <= SCCGWA.COMM_AREA.AC_DATE) {
            BPCSSRCU.DT = SCCGWA.COMM_AREA.AC_DATE;
            CEP.TRC(SCCGWA, "*** CURR RATE : ");
            CEP.TRC(SCCGWA, BPCSSRCU.DT);
        } else {
            BPCSSRCU.DT = BPB5137_AWA_5137.START_DT;
            CEP.TRC(SCCGWA, "*** FUTR RATE : ");
            CEP.TRC(SCCGWA, BPCSSRCU.DT);
        }
        CEP.TRC(SCCGWA, BPCSSRCU.BR);
        CEP.TRC(SCCGWA, BPCSSRCU.BASE_TYP);
        CEP.TRC(SCCGWA, BPCSSRCU.DT);
        CEP.TRC(SCCGWA, BPCSSRCU.TBL_A[1-1].CCY);
        BPCSSRCU.TENOR_A[1-1].TENOR = WS_TENO;
        CEP.TRC(SCCGWA, BPCSSRCU.TENOR_A[1-1].TENOR);
        BPCSSRCU.TENOR_A[2-1].TENOR = " ";
        BPCSSRCU.TENOR_A[3-1].TENOR = " ";
        BPCSSRCU.TENOR_A[4-1].TENOR = " ";
        BPCSSRCU.TENOR_A[5-1].TENOR = " ";
        BPCSSRCU.TENOR_A[6-1].TENOR = " ";
        BPCSSRCU.TENOR_A[7-1].TENOR = " ";
        BPCSSRCU.TENOR_A[8-1].TENOR = " ";
        BPCSSRCU.TENOR_A[9-1].TENOR = " ";
        BPCSSRCU.TENOR_A[10-1].TENOR = " ";
        BPCSSRCU.TENOR_A[11-1].TENOR = " ";
        BPCSSRCU.TENOR_A[12-1].TENOR = " ";
        BPCSSRCU.TENOR_A[13-1].TENOR = " ";
        BPCSSRCU.TENOR_A[14-1].TENOR = " ";
        BPCSSRCU.TENOR_A[15-1].TENOR = " ";
        BPCSSRCU.TENOR_A[16-1].TENOR = " ";
        BPCSSRCU.TENOR_A[17-1].TENOR = " ";
        BPCSSRCU.TENOR_A[18-1].TENOR = " ";
        BPCSSRCU.TENOR_A[19-1].TENOR = " ";
        BPCSSRCU.TENOR_A[20-1].TENOR = " ";
        BPCSSRCU.TBL_A[1-1].RATE_A.RATE_TBL_A[1-1].RATE = BPB5137_AWA_5137.BID;
        CEP.TRC(SCCGWA, BPCSSRCU.TBL_A[1-1].RATE_A.RATE_TBL_A[1-1].RATE);
        BPCSSRCU.TBL_A[1-1].RATE_A.RATE_TBL_A[2-1].RATE = 0;
        BPCSSRCU.TBL_A[1-1].RATE_A.RATE_TBL_A[3-1].RATE = 0;
        BPCSSRCU.TBL_A[1-1].RATE_A.RATE_TBL_A[4-1].RATE = 0;
        BPCSSRCU.TBL_A[1-1].RATE_A.RATE_TBL_A[5-1].RATE = 0;
        BPCSSRCU.TBL_A[1-1].RATE_A.RATE_TBL_A[6-1].RATE = 0;
        BPCSSRCU.TBL_A[1-1].RATE_A.RATE_TBL_A[7-1].RATE = 0;
        BPCSSRCU.TBL_A[1-1].RATE_A.RATE_TBL_A[8-1].RATE = 0;
        BPCSSRCU.TBL_A[1-1].RATE_A.RATE_TBL_A[9-1].RATE = 0;
        BPCSSRCU.TBL_A[1-1].RATE_A.RATE_TBL_A[10-1].RATE = 0;
        BPCSSRCU.TBL_A[1-1].RATE_A.RATE_TBL_A[11-1].RATE = 0;
        BPCSSRCU.TBL_A[1-1].RATE_A.RATE_TBL_A[12-1].RATE = 0;
        BPCSSRCU.TBL_A[1-1].RATE_A.RATE_TBL_A[13-1].RATE = 0;
        BPCSSRCU.TBL_A[1-1].RATE_A.RATE_TBL_A[14-1].RATE = 0;
        BPCSSRCU.TBL_A[1-1].RATE_A.RATE_TBL_A[15-1].RATE = 0;
        BPCSSRCU.TBL_A[1-1].RATE_A.RATE_TBL_A[16-1].RATE = 0;
        BPCSSRCU.TBL_A[1-1].RATE_A.RATE_TBL_A[17-1].RATE = 0;
        BPCSSRCU.TBL_A[1-1].RATE_A.RATE_TBL_A[18-1].RATE = 0;
        BPCSSRCU.TBL_A[1-1].RATE_A.RATE_TBL_A[19-1].RATE = 0;
        BPCSSRCU.TBL_A[1-1].RATE_A.RATE_TBL_A[20-1].RATE = 0;
        CEP.TRC(SCCGWA, "BEFORE VARYING");
        for (WS_I = 1; WS_I <= 20; WS_I += 1) {
            CEP.TRC(SCCGWA, WS_I);
            BPCSSRCU.REF_A[WS_I-1].REF_BR = 0;
            BPCSSRCU.REF_A[WS_I-1].REF_CCY = " ";
            BPCSSRCU.REF_A[WS_I-1].REF_TYP = " ";
            BPCSSRCU.REF_A[WS_I-1].REF_TERM = " ";
            BPCSSRCU.REF_A[WS_I-1].REF_OPT = ' ';
            BPCSSRCU.REF_A[WS_I-1].REF_DIFF = 0;
        }
        CEP.TRC(SCCGWA, BPCSSRCU);
        S000_CALL_BPZSSRCU();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPRPCCY() throws IOException,SQLException,Exception {
        BPCRMBPM.FUNC = 'S';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
    }
    public void T000_READNEXT_BPRPCCY() throws IOException,SQLException,Exception {
        BPCRMBPM.FUNC = 'R';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
    }
    public void T000_ENDBR_BPRPCCY() throws IOException,SQLException,Exception {
        BPCRMBPM.FUNC = 'E';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPZRBCCY() throws IOException,SQLException,Exception {
        BPCRBCCY.INFO.OP_FUNC = 'S';
        BPCRBCCY.INFO.POINTER = BPRBCCY;
        BPCRBCCY.INFO.LEN = 391;
        S000_CALL_BPZRBCCY();
        if (pgmRtn) return;
    }
    public void T000_READNEXT_BPZRBCCY() throws IOException,SQLException,Exception {
        BPCRBCCY.INFO.OP_FUNC = 'R';
        BPCRBCCY.INFO.POINTER = BPRBCCY;
        BPCRBCCY.INFO.LEN = 391;
        S000_CALL_BPZRBCCY();
        if (pgmRtn) return;
    }
    public void T000_ENDBR_BPZRBCCY() throws IOException,SQLException,Exception {
        BPCRBCCY.INFO.OP_FUNC = 'E';
        BPCRBCCY.INFO.POINTER = BPRBCCY;
        BPCRBCCY.INFO.LEN = 391;
        S000_CALL_BPZRBCCY();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRMBPM() throws IOException,SQLException,Exception {
        BPRMBPM.KEY.TYPE = BPRPCCY.KEY.TYP;
        BPRMBPM.KEY.CODE = BPRPCCY.KEY.CD;
        BPRMBPM.EFF_DATE = WS_EFF_DT;
        CEP.TRC(SCCGWA, BPRMBPM.KEY.TYPE);
        CEP.TRC(SCCGWA, BPRMBPM.KEY.CODE);
        CEP.TRC(SCCGWA, BPRMBPM.EFF_DATE);
        CEP.TRC(SCCGWA, BPCRMBPM.FUNC);
        IBS.CALLCPN(SCCGWA, CPN_R_FEE_BPZRMBPM, BPCRMBPM);
        CEP.TRC(SCCGWA, BPCRMBPM.FUNC);
        CEP.TRC(SCCGWA, BPCRMBPM.RC);
    }
    public void S000_CALL_BPZRBCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_INQ_BRW_CCY, BPCRBCCY);
    }
    public void S000_CALL_BPZSSRCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_SRC_IRATE_UPD, BPCSSRCU);
    }
    public void R000_CHECK_RETURNCODE_BROWSE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCRMBPM.RETURN_INFO);
        if (BPCRMBPM.RETURN_INFO == 'F') {
            WS_RC.WS_RTNCODE = 0;
        } else {
            if (BPCRMBPM.RETURN_INFO == 'L' 
                || BPCRMBPM.RETURN_INFO == 'N') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CCY_REC_NOTFND, WS_RC);
            } else {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TABLE_ACC_ERR, WS_RC);
            }
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
        if (BPCPQORG.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_ORG_NOTFND)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_RC);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
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
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
