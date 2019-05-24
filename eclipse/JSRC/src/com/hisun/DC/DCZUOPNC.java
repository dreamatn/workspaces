package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.CI.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZUOPNC {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    brParm DCTIAACR_BR = new brParm();
    DBParm DCTIAACR_RD;
    DBParm DCTSPAC_RD;
    boolean pgmRtn = false;
    short K_AC_LEN = 0;
    String WS_ERR_MSG = " ";
    int WS_ACR_NUM = 0;
    int WS_SEQ_NUM = 0;
    char WS_RL_FLAG = ' ';
    char WS_END_FLG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCCIMSTO DCCIMSTO = new DCCIMSTO();
    DCCIPACI DCCIPACI = new DCCIPACI();
    CICMACR CICMACR = new CICMACR();
    DCCICCYA DCCICCYA = new DCCICCYA();
    String WS_IAACR_VIA_AC = " ";
    String WS_IAACR_SUB_AC = " ";
    DCRIAACR DCRIAACR = new DCRIAACR();
    DCRSPAC DCRSPAC = new DCRSPAC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCUOPNC DCCUOPNC;
    public void MP(SCCGWA SCCGWA, DCCUOPNC DCCUOPNC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUOPNC = DCCUOPNC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZUOPNC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_OPN_VIA_PROC();
        if (pgmRtn) return;
        B025_WRITE_BAL_PROC();
        if (pgmRtn) return;
        B030_OPN_OCAC_PROC();
        if (pgmRtn) return;
        B050_ACR_OPR_PROC();
        if (pgmRtn) return;
        B060_PAC_OPR_PROC();
        if (pgmRtn) return;
        B070_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DCCUOPNC.INP_DATA.CI_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CI_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUOPNC.INP_DATA.VIA_FLG == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_VIA_FLG_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            if (DCCUOPNC.INP_DATA.VIA_FLG != '0' 
                && DCCUOPNC.INP_DATA.VIA_FLG != '1') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_VIA_FLG_INVALID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCUOPNC.INP_DATA.PRD_TYPE.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_TYPE_MST_IN;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUOPNC.INP_DATA.PRD_CODE.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PRD_COD_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUOPNC.INP_DATA.VCH_TYPE == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_VCH_TYPE_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUOPNC.INP_DATA.VCH_TYPE != '0' 
            && DCCUOPNC.INP_DATA.VCH_TYPE != '1' 
            && DCCUOPNC.INP_DATA.VCH_TYPE != '3' 
            && DCCUOPNC.INP_DATA.VCH_TYPE != '4' 
            && DCCUOPNC.INP_DATA.VCH_TYPE != '5' 
            && DCCUOPNC.INP_DATA.VCH_TYPE != '7' 
            && DCCUOPNC.INP_DATA.VCH_TYPE != ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_VCH_TYPE_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUOPNC.INP_DATA.APP.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_APP_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUOPNC.INP_DATA.APP.equalsIgnoreCase("DD") 
            && DCCUOPNC.INP_DATA.STD_AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SUB_AC_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUOPNC.INP_DATA.CCY.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUOPNC.INP_DATA.CCY_TYPE != '1' 
            && DCCUOPNC.INP_DATA.CCY_TYPE != '2') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CCY_TYP_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUOPNC.INP_DATA.CCY.equalsIgnoreCase("156") 
            && DCCUOPNC.INP_DATA.CCY_TYPE == '2') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CCY_TYP_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUOPNC.INP_DATA.DEFAULT_FLG != ' ' 
            && DCCUOPNC.INP_DATA.DEFAULT_FLG != 'Y' 
            && DCCUOPNC.INP_DATA.DEFAULT_FLG != 'N') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DEFAULT_FLG_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_OPN_VIA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCIMSTO);
        DCCIMSTO.INP_DATA.CI_NO = DCCUOPNC.INP_DATA.CI_NO;
        DCCIMSTO.INP_DATA.VIA_FLG = DCCUOPNC.INP_DATA.VIA_FLG;
        DCCIMSTO.INP_DATA.PRD_TYPE = DCCUOPNC.INP_DATA.PRD_TYPE;
        DCCIMSTO.INP_DATA.PRD_CODE = DCCUOPNC.INP_DATA.PRD_CODE;
        DCCIMSTO.INP_DATA.TERM = DCCUOPNC.INP_DATA.TERM;
        DCCIMSTO.INP_DATA.INT_RAT = DCCUOPNC.INP_DATA.INT_RAT;
        DCCIMSTO.INP_DATA.CCY = DCCUOPNC.INP_DATA.CCY;
        DCCIMSTO.INP_DATA.CCY_TYPE = DCCUOPNC.INP_DATA.CCY_TYPE;
        DCCIMSTO.INP_DATA.CI_SHOW_FLG = DCCUOPNC.INP_DATA.CI_SHOW_FLG;
        DCCIMSTO.INP_DATA.AC_CNM = DCCUOPNC.INP_DATA.AC_CNM;
        DCCIMSTO.INP_DATA.AC_ENM = DCCUOPNC.INP_DATA.AC_ENM;
        DCCIMSTO.INP_DATA.BR = DCCUOPNC.INP_DATA.BR;
        S000_CALL_DCZIMSTO();
        if (pgmRtn) return;
    }
    public void B025_WRITE_BAL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCICCYA);
        DCCICCYA.VIA_AC = DCCIMSTO.OUT_DATA.VIA_AC;
        DCCICCYA.CCY = DCCUOPNC.INP_DATA.CCY;
        DCCICCYA.CCY_TYPE = DCCUOPNC.INP_DATA.CCY_TYPE;
        S000_CALL_DCZICCYA();
        if (pgmRtn) return;
    }
    public void B030_OPN_OCAC_PROC() throws IOException,SQLException,Exception {
    }
    public void B040_CUS_RLT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICMACR);
        CICMACR.FUNC = '1';
        CICMACR.DATA.ENTY_TYP = '3';
        CICMACR.DATA.AGR_NO = DCCIMSTO.OUT_DATA.VIA_AC;
        CICMACR.DATA.CI_NO = DCCUOPNC.INP_DATA.CI_NO;
        CICMACR.DATA.PROD_CD = DCCUOPNC.INP_DATA.PRD_CODE;
        CICMACR.DATA.FRM_APP = "DC";
        CICMACR.DATA.OPN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICMACR.DATA.OPEN_DT = SCCGWA.COMM_AREA.AC_DATE;
        S000_CALL_CIZMACR();
        if (pgmRtn) return;
    }
    public void B060_PAC_OPR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCIPACI);
        DCCIPACI.FREE_TYPE = "007";
        DCCIPACI.FREE_AC = WS_IAACR_SUB_AC;
        DCCIPACI.STD_AC = DCCUOPNC.INP_DATA.STD_AC;
        S000_CALL_DCZIPACI();
        if (pgmRtn) return;
    }
    public void B050_ACR_OPR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIAACR);
        DCRIAACR.KEY.VIA_AC = DCCIMSTO.OUT_DATA.VIA_AC;
        WS_IAACR_VIA_AC = DCCIMSTO.OUT_DATA.VIA_AC;
        T000_STRBR_BY_VIA_AC_PROC();
        if (pgmRtn) return;
        T000_READ_NEXT_PROC();
        if (pgmRtn) return;
        while (WS_END_FLG != 'Y') {
            WS_SEQ_NUM = DCRIAACR.KEY.SEQ;
            WS_ACR_NUM = WS_ACR_NUM + 1;
            T000_READ_NEXT_PROC();
            if (pgmRtn) return;
        }
        T000_END_BROWSE_PROC();
        if (pgmRtn) return;
        if (WS_ACR_NUM >= 999999) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_RL_NUM_GT_MAX_NUM;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B050_10_CHK_ACR_PROC();
        if (pgmRtn) return;
        B050_20_CRT_ACR_PROC();
        if (pgmRtn) return;
    }
    public void B050_10_CHK_ACR_PROC() throws IOException,SQLException,Exception {
        WS_RL_FLAG = ' ';
        K_AC_LEN = DCCIMSTO.OUT_DATA.VIA_AC.trim().length();
        K_AC_LEN += 1;
        CEP.TRC(SCCGWA, K_AC_LEN);
        while (WS_RL_FLAG != 'N') {
            IBS.init(SCCGWA, DCRIAACR);
            WS_ACR_NUM = WS_ACR_NUM + 1;
            if (WS_ACR_NUM >= 999999) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_RL_NUM_GT_MAX_NUM;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_IAACR_SUB_AC = DCCIMSTO.OUT_DATA.VIA_AC;
            if (WS_IAACR_SUB_AC == null) WS_IAACR_SUB_AC = "";
            JIBS_tmp_int = WS_IAACR_SUB_AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) WS_IAACR_SUB_AC += " ";
            JIBS_tmp_str[0] = "" + WS_ACR_NUM;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            WS_IAACR_SUB_AC = WS_IAACR_SUB_AC.substring(0, K_AC_LEN - 1) + JIBS_tmp_str[0] + WS_IAACR_SUB_AC.substring(K_AC_LEN + 6 - 1);
            IBS.init(SCCGWA, DCRSPAC);
            DCRSPAC.KEY.FREE_AC = WS_IAACR_SUB_AC;
            CEP.TRC(SCCGWA, DCRSPAC.KEY.FREE_AC);
            T000_READ_DCTSPAC();
            if (pgmRtn) return;
        }
    }
    public void B050_20_CRT_ACR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIAACR);
        DCRIAACR.KEY.SEQ = WS_SEQ_NUM + 1;
        DCRIAACR.KEY.VIA_AC = DCCIMSTO.OUT_DATA.VIA_AC;
        DCRIAACR.FRM_APP = DCCUOPNC.INP_DATA.APP;
        if (DCCUOPNC.INP_DATA.STD_AC_FLG == 'Y') {
            DCRIAACR.SUB_AC = DCCUOPNC.INP_DATA.STD_AC;
            DCRIAACR.STD_AC_FLG = 'Y';
        } else {
            DCRIAACR.SUB_AC = DCCUOPNC.INP_DATA.STD_AC;
            DCRIAACR.STD_AC_FLG = 'N';
        }
        DCRIAACR.CCY = DCCUOPNC.INP_DATA.CCY;
        DCRIAACR.CCY_TYPE = DCCUOPNC.INP_DATA.CCY_TYPE;
        DCRIAACR.DEFAULT_FLG = DCCUOPNC.INP_DATA.DEFAULT_FLG;
        DCRIAACR.VCH_TYPE = DCCUOPNC.INP_DATA.VCH_TYPE;
        DCRIAACR.VCH_ID = DCCUOPNC.INP_DATA.VCH_ID;
        DCRIAACR.VCH_NO = DCCUOPNC.INP_DATA.VCH_NO;
        DCRIAACR.ACCR_FLG = '1';
        DCRIAACR.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRIAACR.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRIAACR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRIAACR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DCTIAACR();
        if (pgmRtn) return;
    }
    public void B070_OUTPUT_PROC() throws IOException,SQLException,Exception {
        DCCUOPNC.OUT_DATA.VIA_AC = DCCIMSTO.OUT_DATA.VIA_AC;
        DCCUOPNC.OUT_DATA.SUB_AC = WS_IAACR_SUB_AC;
        DCCUOPNC.OUT_DATA.SEQ = DCRIAACR.KEY.SEQ;
    }
    public void S000_CALL_DCZIMSTO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-MST-OPN", DCCIMSTO);
        if (DCCIMSTO.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCIMSTO.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZMACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-ACR", CICMACR);
        if (CICMACR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICMACR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZIPACI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-INT-SPAC", DCCIPACI);
        if (DCCIPACI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCIPACI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_DCZICCYA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-CCY-ADD-AMT", DCCICCYA);
        if (DCCICCYA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCICCYA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STRBR_BY_VIA_AC_PROC() throws IOException,SQLException,Exception {
        DCTIAACR_BR.rp = new DBParm();
        DCTIAACR_BR.rp.TableName = "DCTIAACR";
        DCTIAACR_BR.rp.upd = true;
        DCTIAACR_BR.rp.where = "VIA_AC = :WS_IAACR_VIA_AC";
        IBS.STARTBR(SCCGWA, DCRIAACR, this, DCTIAACR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIAACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_NEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRIAACR, this, DCTIAACR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_END_FLG = 'Y';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIAACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_END_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTIAACR_BR);
    }
    public void T000_WRITE_DCTIAACR() throws IOException,SQLException,Exception {
        DCTIAACR_RD = new DBParm();
        DCTIAACR_RD.TableName = "DCTIAACR";
        DCTIAACR_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DCRIAACR, DCTIAACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_IA_ACR_RCD_ALR_EXS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIAACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTIAACR() throws IOException,SQLException,Exception {
        DCTIAACR_RD = new DBParm();
        DCTIAACR_RD.TableName = "DCTIAACR";
        DCTIAACR_RD.where = "SUB_AC = :WS_IAACR_SUB_AC";
        DCTIAACR_RD.errhdl = true;
        IBS.READ(SCCGWA, DCRIAACR, this, DCTIAACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_RL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_RL_FLAG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ TABLE DCTIAACR ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIAACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTSPAC() throws IOException,SQLException,Exception {
        DCTSPAC_RD = new DBParm();
        DCTSPAC_RD.TableName = "DCTSPAC";
        DCTSPAC_RD.errhdl = true;
        IBS.READ(SCCGWA, DCRSPAC, DCTSPAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_RL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_RL_FLAG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ TABLE DCTSPAC ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTSPAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_C);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_Q);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_M);
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
