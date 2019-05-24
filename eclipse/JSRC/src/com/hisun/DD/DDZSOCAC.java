package com.hisun.DD;

import com.hisun.CI.*;
import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.VT.*;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSOCAC {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm DDTMST_RD;
    DBParm DDTINF_RD;
    DBParm DDTCCY_RD;
    DBParm DCTSSTS_RD;
    String K_OUTPUT_FMT1 = "DD504";
    String K_OUTPUT_FMT2 = "DD520";
    String K_APP_MMO = "DD";
    String K_CI_STS_TBL = "5840";
    String K_HIS_CPB_NAME = "DDCHOPAC";
    String K_HIS_REMARKS = "OPEN ACCOUNT";
    String K_AC_CCY = "156";
    char K_CCY_TYPE = '1';
    String K_HIS_MMO = "P114";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    String WS_AC_NO = " ";
    DDZSOCAC_WS_AC_NO1 WS_AC_NO1 = new DDZSOCAC_WS_AC_NO1();
    short WS_RC_CODE = 0;
    short WS_CNT = 0;
    DDZSOCAC_WS_BKAC_DATA WS_BKAC_DATA = new DDZSOCAC_WS_BKAC_DATA();
    DDZSOCAC_WS_PRINT_WEEK WS_PRINT_WEEK = new DDZSOCAC_WS_PRINT_WEEK();
    DDZSOCAC_WS_PRINT_MON WS_PRINT_MON = new DDZSOCAC_WS_PRINT_MON();
    DDZSOCAC_WS_PRINT_DAY WS_PRINT_DAY = new DDZSOCAC_WS_PRINT_DAY();
    char WS_AC_STS = ' ';
    DDZSOCAC_WS_MMDD WS_MMDD = new DDZSOCAC_WS_MMDD();
    int WS_EFF_DATE = 0;
    int WS_BR = 0;
    char WS_CCY_FOUND_FLG = ' ';
    char WS_AC_TYPE = ' ';
    short WS_COUNT = 0;
    short WS_ACTYP_C = 0;
    short WS_NUM_D = 0;
    short WS_NUM_C = 0;
    char WS_BRANCH_FLG = ' ';
    String WS_FRG_IND = " ";
    char WS_NRA_FLG = ' ';
    char WS_VCH_TYPE_FLG = ' ';
    char WS_STOP_FLG = ' ';
    char WS_CIAC_END_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCOOCAC DDCOOCAC = new DDCOOCAC();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    DDCIPSBK DDCIPSBK = new DDCIPSBK();
    BPCCGAC BPCCGAC = new BPCCGAC();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCUCNGM BPCUCNGM = new BPCUCNGM();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCQCNGL BPCQCNGL = new BPCQCNGL();
    BPCACAAC BPCACAAC = new BPCACAAC();
    BPCUACNO BPCUACNO = new BPCUACNO();
    BPCSMPRD BPCSMPRD = new BPCSMPRD();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCTCALF BPCTCALF = new BPCTCALF();
    BPCPCKPD BPCPCKPD = new BPCPCKPD();
    CICMACR CICMACR = new CICMACR();
    CICCUST CICCUST = new CICCUST();
    CICOPCS CICOPCS = new CICOPCS();
    CICQACR CICQACR = new CICQACR();
    SCCBSP SCCBSP = new SCCBSP();
    SCCBSPS SCCBSPS = new SCCBSPS();
    DDCOSINO DDCOSINO = new DDCOSINO();
    CICSACR CICSACR = new CICSACR();
    CICSACAC CICSACAC = new CICSACAC();
    CICQACAC CICQACAC = new CICQACAC();
    BPRPRMT BPRPRMT = new BPRPRMT();
    CICQCIAC CICQCIAC = new CICQCIAC();
    BPCPRMM BPCPRMM = new BPCPRMM();
    DDCPBROP DDCPBROP = new DDCPBROP();
    BPCSOCAC BPCPOCAC = new BPCSOCAC();
    DDCUFEES DDCUFEES = new DDCUFEES();
    CICSFEI CICSFEI = new CICSFEI();
    CICSFEA CICSFEA = new CICSFEA();
    CICMFRL CICMFRL = new CICMFRL();
    VTCUJMDR VTCUJMDR = new VTCUJMDR();
    DDRMST DDRMST = new DDRMST();
    DDRINF DDRINF = new DDRINF();
    DDRCCY DDRCCY = new DDRCCY();
    DCRSSTS DCRSSTS = new DCRSSTS();
    BPROCAC BPROCAC = new BPROCAC();
    SCCGWA SCCGWA;
    DDCSOCAC DDCSOCAC;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA, DDCSOCAC DDCSOCAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSOCAC = DDCSOCAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZSOCAC return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        IBS.init(SCCGWA, DDCOOCAC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSOCAC.PROD_CODE);
        B010_CHECK_DATA_VALIDITY();
        CEP.TRC(SCCGWA, DDCSOCAC.FUNC);
        if (DDCSOCAC.FUNC != '1') {
            B020_GENERATE_ACNO();
            B040_CRT_DD_AC_PROC();
            if (!DDCSOCAC.FRG_IND.equalsIgnoreCase("OSA")) {
                B050_CRT_DD_AC_INF_PROC();
            }
            B060_CUS_RLT_PROC();
        } else {
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = DDCSOCAC.AC;
            T000_READ_UPDATE_DDTMST();
        }
        B020_GENERATE_ACAC_PROC();
        B070_CRT_DD_AC_CCY_PROC();
        B130_INQ_GL_MASTER();
        B080_WRITE_GL_MASTER_PROC();
        if (CICCUST.O_DATA.O_CI_TYP == '3') {
            B170_REGISTER_VTTJMAC();
        }
        CEP.TRC(SCCGWA, DDCSOCAC.FRG_IND);
        if (!DDCSOCAC.FRG_IND.equalsIgnoreCase("OSA")) {
            B090_NON_FIN_HIS_PROC();
        }
        B100_CRT_BP_OCAC_PROC_CUS();
        B100_CRT_BP_OCAC_PROC();
        B030_PSBK_PROC();
        B040_CRT_CIZSACAC_PROC();
        if (!DDCSOCAC.FR_OP_NO.equalsIgnoreCase("N/A") 
            && DDCSOCAC.FR_OP_NO.trim().length() > 0) {
            B161_REG_CUS_AC_FR_OP_NO();
        }
        B120_WAVE_ANNUAL_FEES();
        if (DDCSOCAC.TLR_FLG == 'Y') {
            S000_SET_FEE_INFO();
            S000_CALL_FEE();
        }
        B110_TRANS_DATA_OUTPUT();
        CEP.TRC(SCCGWA, DDCSOCAC.PAY_MTH);
    }
    public void B005_CHECK_L_AC_EXIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQCIAC);
        CICQCIAC.FUNC = '1';
        CICQCIAC.DATA.CI_NO = DDCSOCAC.CI_NO;
        CICQCIAC.DATA.PROD_CD = DDCSOCAC.PROD_CODE;
        S000_CALL_CIZQCIAC();
        if (CICQCIAC.RC.RC_CODE == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_L_AC_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void B010_CHECK_DATA_VALIDITY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSOCAC.CI_NO);
        CEP.TRC(SCCGWA, DDCSOCAC.PROD_CODE);
        CEP.TRC(SCCGWA, DDCSOCAC.AC_TYP);
        CEP.TRC(SCCGWA, DDCSOCAC.PAY_MTH);
        CEP.TRC(SCCGWA, DDCSOCAC.POST_AC);
        B010_10_CHK_INPUT_DATA();
        B010_30_CHK_CI_INF();
        B010_50_CHK_PRD_INF();
        B010_20_CHK_AC_TYPE();
    }
    public void B010_10_CHK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDCSOCAC.CI_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CI_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCSOCAC.AC_CNM.trim().length() == 0 
            && DDCSOCAC.AC_ENM.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_CNM_NOT_AC_ENM;
            S000_ERR_MSG_PROC();
        }
        if (DDCSOCAC.CCY_FLG == ' ') {
            DDCSOCAC.CCY_FLG = 'S';
        }
        if (DDCSOCAC.CCY_FLG != 'S' 
            && DDCSOCAC.CCY_FLG != 'M') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_FLG_M_INVALID;
            S000_ERR_MSG_PROC();
        }
        if (DDCSOCAC.AC_CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_S_AC_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, DDCPBROP);
        BPRPRMT.KEY.TYP = "PDD21";
        BPRPRMT.KEY.CD = "" + SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        JIBS_tmp_int = BPRPRMT.KEY.CD.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPRPRMT.KEY.CD = "0" + BPRPRMT.KEY.CD;
        BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCPBROP.DATA_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
        BPCPRMM.FUNC = '3';
        BPCPRMM.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMM();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        DDCPBROP.DATA_TXT.COM_FLG = JIBS_tmp_str[0].charAt(0);
        CEP.TRC(SCCGWA, DDCPBROP.DATA_TXT.COM_FLG);
        IBS.init(SCCGWA, CICQCIAC);
        CICQCIAC.FUNC = '1';
        CICQCIAC.DATA.CI_NO = DDCSOCAC.CI_NO;
        CICQCIAC.DATA.FRM_APP = "DD";
        S000_CALL_CIZQCIAC();
        if (CICQCIAC.RC.RC_CODE == 0) {
            WS_CIAC_END_FLG = 'N';
            for (WS_CNT = 1; WS_CIAC_END_FLG != 'Y' 
                && WS_CNT <= 100; WS_CNT += 1) {
                if (CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_CNT-1].ENTY_NO.trim().length() == 0) {
                    WS_CIAC_END_FLG = 'Y';
                } else {
                    IBS.init(SCCGWA, DDRMST);
                    DDRMST.KEY.CUS_AC = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_CNT-1].ENTY_NO;
                    T000_READ_UPDATE_DDTMST();
                    CEP.TRC(SCCGWA, DDRMST.AC_TYPE);
                    if (DDCSOCAC.AC_TYP == 'C' 
                        && DDRMST.AC_TYPE == 'C' 
                        && DDRMST.AC_STS != 'C') {
                        CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_4032);
                    }
                    CEP.TRC(SCCGWA, DDRMST.OWNER_BR);
                    CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.BBR);
                    if (DDCSOCAC.AC_TYP == 'C' 
                        && DDRMST.AC_TYPE == 'D' 
                        && DDRMST.AC_STS != 'C' 
                        && DDRMST.OWNER_BR == BPCPORUP.DATA_INFO.BBR) {
                        CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.CUS_HAVE_D_AC);
                    }
                    if (DDCSOCAC.AC_TYP == 'D' 
                        && DDRMST.AC_TYPE == 'C' 
                        && DDRMST.AC_STS != 'C' 
                        && DDRMST.OWNER_BR == BPCPORUP.DATA_INFO.BBR) {
                        CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.CUS_HAVE_C_AC);
                    }
                }
            }
        }
        if (DDCSOCAC.PROD_CODE.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_NEW_PROD_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, DDCSOCAC.AC_TYP);
        if (DDCSOCAC.AC_TYP == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_TYP_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCSOCAC.AC_TYP != 'A' 
            && DDCSOCAC.AC_TYP != 'C' 
            && DDCSOCAC.AC_TYP != 'D' 
            && DDCSOCAC.AC_TYP != 'E' 
            && DDCSOCAC.AC_TYP != 'F' 
            && DDCSOCAC.AC_TYP != 'G' 
            && DDCSOCAC.AC_TYP != 'H' 
            && DDCSOCAC.AC_TYP != 'I' 
            && DDCSOCAC.AC_TYP != 'J' 
            && DDCSOCAC.AC_TYP != 'K' 
            && DDCSOCAC.AC_TYP != 'L' 
            && DDCSOCAC.AC_TYP != 'M' 
            && DDCSOCAC.AC_TYP != 'N' 
            && DDCSOCAC.AC_TYP != 'P' 
            && DDCSOCAC.AC_TYP != 'Q' 
            && DDCSOCAC.AC_TYP != 'X' 
            && DDCSOCAC.AC_TYP != 'R') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_TYP_ERR;
            S000_ERR_MSG_PROC();
        }
        if (DDCSOCAC.AC_TYP == 'D' 
            && (DDCSOCAC.CASH_FLG == '1' 
            || DDCSOCAC.CASH_FLG == '3')) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CASH_FLG_ERR);
        }
        CEP.TRC(SCCGWA, DDCSOCAC.CCY_TYPE);
        if ((DDCSOCAC.AC_CCY.equalsIgnoreCase("156") 
            && DDCSOCAC.CCY_TYPE != '1') 
            || (!DDCSOCAC.AC_CCY.equalsIgnoreCase("156") 
            && (DDCSOCAC.CCY_TYPE != '1' 
            && DDCSOCAC.CCY_TYPE != '2'))) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_TYP_INVALID);
        }
        CEP.TRC(SCCGWA, DDCSOCAC.AC_CCY);
        if (DDCSOCAC.PAY_MTH == ' ') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_DRAW_MTH_M_INPUT);
        }
        if (DDCSOCAC.PAY_MTH != '2' 
            && DDCSOCAC.PAY_MTH != '5') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_DRAW_MTH_ERR);
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        S000_CALL_BPZPQORG();
        CEP.TRC(SCCGWA, BPCPQORG.TRA_TYP);
        if (DDCSOCAC.FRG_IND == null) DDCSOCAC.FRG_IND = "";
        JIBS_tmp_int = DDCSOCAC.FRG_IND.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) DDCSOCAC.FRG_IND += " ";
        if ((!BPCPQORG.TRA_TYP.equalsIgnoreCase("00")) 
            && (!DDCSOCAC.FRG_IND.substring(0, 2).equalsIgnoreCase("FT"))) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_FTBR_CANT_OP_NFTAC);
        }
        CEP.TRC(SCCGWA, BPCPQORG.ATTR);
        if (BPCPQORG.ATTR != '2' 
            && BPCPQORG.ATTR != '3') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_BR_NOT_PQORG_ATTR);
        }
        if (!DDCSOCAC.AC_CCY.equalsIgnoreCase("156")) {
            CEP.TRC(SCCGWA, BPCPQORG.FX_BUSI);
            if (BPCPQORG.FX_BUSI.equalsIgnoreCase("00")) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_TR_BR_NO_FX_AUTH);
            }
        }
        if (DDCSOCAC.POST_AC.trim().length() > 0) {
            B011_CHK_POST_AC_PROC();
        }
        if (DDCSOCAC.REG_FLG == ' ') {
            DDCSOCAC.REG_FLG = '0';
        }
        if (DDCSOCAC.REG_FLG != '0' 
            && DDCSOCAC.REG_FLG != '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_REG_FLG_INVALID);
        }
        if (!DDCSOCAC.FR_OP_NO.equalsIgnoreCase("N/A") 
            && DDCSOCAC.FR_OP_NO.trim().length() > 0) {
            if (DDCSOCAC.LMT_TYP != '0' 
                && DDCSOCAC.LMT_TYP != '1' 
                && DDCSOCAC.LMT_TYP != '2' 
                && DDCSOCAC.LMT_TYP != '3') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ERR_LMT_TYP);
            }
        }
    }
    public void B011_CHK_POST_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'C';
        CICQACAC.DATA.AGR_NO = DDCSOCAC.POST_AC;
        CICQACAC.DATA.CCY_ACAC = DDCSOCAC.AC_CCY;
        CICQACAC.DATA.CR_FLG = DDCSOCAC.CCY_TYPE;
        S000_CALL_CIZQACAC();
    }
    public void B010_30_CHK_CI_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'C';
        CICCUST.DATA.CI_NO = DDCSOCAC.CI_NO;
        CEP.TRC(SCCGWA, DDCSOCAC.CI_NO);
        S000_CALL_CIZCUST();
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_SID_FLG);
        if (CICCUST.O_DATA.O_SID_FLG == '1' 
            && DDCSOCAC.FRG_IND.equalsIgnoreCase("NRA")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CHN_CI_CANT_OPNRA);
        }
        CEP.TRC(SCCGWA, DDCSOCAC.AC_TYP);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_TYP);
        CEP.TRC(SCCGWA, DDCSOCAC.AC_CCY);
        if (DDCSOCAC.AC_CCY.trim().length() > 0 
            && DDCSOCAC.AC_TYP != 'X') {
            if (!DDCSOCAC.AC_CCY.equalsIgnoreCase("156") 
                && DDCSOCAC.CCY_TYPE != '2' 
                && CICCUST.O_DATA.O_CI_TYP != '3') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_TYP_INVALID);
            }
        }
        IBS.init(SCCGWA, BPCPCKPD);
        BPCPCKPD.CI_NO = DDCSOCAC.CI_NO;
        BPCPCKPD.PRDT_CODE = DDCSOCAC.PROD_CODE;
        S000_CALL_BPZPCKPD();
        IBS.init(SCCGWA, BPCFCSTS);
        BPCFCSTS.AP_MMO = K_APP_MMO;
        BPCFCSTS.TBL_NO = K_CI_STS_TBL;
        if (CICCUST.O_DATA.O_STSW == null) CICCUST.O_DATA.O_STSW = "";
        JIBS_tmp_int = CICCUST.O_DATA.O_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICCUST.O_DATA.O_STSW += " ";
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        BPCFCSTS.STATUS_WORD = CICCUST.O_DATA.O_STSW.substring(0, 80) + BPCFCSTS.STATUS_WORD.substring(80);
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + DDRMST.AC_STS_WORD + BPCFCSTS.STATUS_WORD.substring(101 + 99 - 1);
        CEP.TRC(SCCGWA, BPCFCSTS.AP_MMO);
        CEP.TRC(SCCGWA, BPCFCSTS.TBL_NO);
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
        S000_CALL_BPZFCSTS();
    }
    public void B010_50_CHK_PRD_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQPRD);
        DDCIQPRD.INPUT_DATA.PROD_CODE = DDCSOCAC.PROD_CODE;
        DDCIQPRD.INPUT_DATA.CCY = DDCSOCAC.AC_CCY;
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
        S000_CALL_DDZIQPRD();
        CEP.TRC(SCCGWA, DDCSOCAC.CR_CR_FL);
        CEP.TRC(SCCGWA, DDVMPRD.VAL.CROS_CR_LMT);
        if (DDCSOCAC.CR_CR_FL == '1' 
            && DDVMPRD.VAL.CROS_CR_LMT != '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_4034);
        }
        CEP.TRC(SCCGWA, DDCSOCAC.FRG_CODE);
        CEP.TRC(SCCGWA, DDVMPRD.VAL.CUR_TYPE);
        CEP.TRC(SCCGWA, DDCSOCAC.CR_DR_FL);
        CEP.TRC(SCCGWA, DDVMPRD.VAL.CROS_DR_LMT);
        if (DDCSOCAC.CR_DR_FL == '1' 
            && DDVMPRD.VAL.CROS_DR_LMT != '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DR_FLG_SURPASS_PRODUCT);
        }
        if (DDVMPRD.VAL.CUST_TYPE == '1') {
            WS_AC_STS = 'A';
            WS_EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            WS_AC_STS = 'V';
            WS_EFF_DATE = 0;
        }
    }
    public void B010_20_CHK_AC_TYPE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_AC_TYPE);
        WS_AC_TYPE = DDCSOCAC.AC_TYP;
        CEP.TRC(SCCGWA, DDVMPRD.VAL.CUR_TYPE);
        CEP.TRC(SCCGWA, DDVMPRD.VAL.CCY[1-1]);
        CEP.TRC(SCCGWA, DDVMPRD.VAL.CCY[2-1]);
        CEP.TRC(SCCGWA, DDVMPRD.VAL.CCY[3-1]);
        CEP.TRC(SCCGWA, DDVMPRD.VAL.CCY[4-1]);
        CEP.TRC(SCCGWA, DDVMPRD.VAL.CCY[5-1]);
        CEP.TRC(SCCGWA, DDVMPRD.VAL.CCY[6-1]);
        CEP.TRC(SCCGWA, DDVMPRD.VAL.CCY[7-1]);
        CEP.TRC(SCCGWA, DDVMPRD.VAL.CCY[8-1]);
        CEP.TRC(SCCGWA, DDVMPRD.VAL.CCY[9-1]);
        CEP.TRC(SCCGWA, DDVMPRD.VAL.CCY[10-1]);
        CEP.TRC(SCCGWA, DDVMPRD.VAL.CCY[11-1]);
        CEP.TRC(SCCGWA, DDVMPRD.VAL.CCY[12-1]);
        if (DDVMPRD.VAL.CUR_TYPE != 'A') {
            WS_CCY_FOUND_FLG = 'N';
            for (WS_CNT = 1; WS_CNT <= 20 
                && WS_CCY_FOUND_FLG != 'Y'; WS_CNT += 1) {
                if (DDVMPRD.VAL.CCY[WS_CNT-1].equalsIgnoreCase(DDCSOCAC.AC_CCY)) {
                    WS_CCY_FOUND_FLG = 'Y';
                }
            }
            if (WS_CCY_FOUND_FLG == 'N') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PRD_CCY_NOT_DEF;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, DDCSOCAC.FRG_IND);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_SID_FLG);
        CEP.TRC(SCCGWA, BPCPQORG.TRA_TYP);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_TYP);
        if (CICCUST.O_DATA.O_SID_FLG == '1' 
            && !BPCPQORG.TRA_TYP.equalsIgnoreCase("00") 
            && CICCUST.O_DATA.O_CI_TYP == '2') {
            WS_FRG_IND = "FTE";
        }
        if (CICCUST.O_DATA.O_SID_FLG == '1' 
            && !BPCPQORG.TRA_TYP.equalsIgnoreCase("00") 
            && CICCUST.O_DATA.O_CI_TYP == '3') {
            WS_FRG_IND = "FTU";
        }
        if (CICCUST.O_DATA.O_SID_FLG == '1' 
            && BPCPQORG.TRA_TYP.equalsIgnoreCase("00")) {
            WS_FRG_IND = "OTH";
        }
        if (CICCUST.O_DATA.O_SID_FLG == '2' 
            && !BPCPQORG.TRA_TYP.equalsIgnoreCase("00") 
            && CICCUST.O_DATA.O_CI_TYP == '2') {
            WS_FRG_IND = "FTN";
        }
        if (CICCUST.O_DATA.O_SID_FLG == '2' 
            && BPCPQORG.TRA_TYP.equalsIgnoreCase("00")) {
            WS_FRG_IND = "NRA";
            WS_NRA_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, WS_FRG_IND);
        if (WS_FRG_IND.compareTo(DDCSOCAC.FRG_IND) > 0 
            && !WS_FRG_IND.equalsIgnoreCase("NRA")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CHN_CI_CANT_OPNCI);
        }
        if ((!DDCSOCAC.FRG_IND.equalsIgnoreCase("NRA") 
            && !DDCSOCAC.FRG_IND.equalsIgnoreCase("OTH")) 
            && WS_FRG_IND.equalsIgnoreCase("NRA")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CHN_CI_CANT_OPNCI);
        }
    }
    public void B020_GENERATE_ACNO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCCGAC);
        if (DDCSOCAC.AC.trim().length() > 0) {
            IBS.init(SCCGWA, BPCUACNO);
            BPCUACNO.DATA.AC_NO = DDCSOCAC.AC;
            BPCUACNO.FUNC = 'U';
            CEP.TRC(SCCGWA, BPCUACNO.DATA.AC_NO);
            CEP.TRC(SCCGWA, BPCUACNO.FUNC);
            S000_CALL_BPZUACNO();
            CEP.TRC(SCCGWA, BPCUACNO.DATA.CI_NO);
            if (!BPCUACNO.DATA.CI_NO.equalsIgnoreCase(DDCSOCAC.CI_NO)) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CINO_NOT_MATCH);
            }
            if (DDCSOCAC.FRG_IND.equalsIgnoreCase("NRA")) {
                CEP.TRC(SCCGWA, "-- NRA AC --");
                if (WS_AC_NO == null) WS_AC_NO = "";
                JIBS_tmp_int = WS_AC_NO.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) WS_AC_NO += " ";
                WS_AC_NO = "NRA" + WS_AC_NO.substring(3);
                if (WS_AC_NO == null) WS_AC_NO = "";
                JIBS_tmp_int = WS_AC_NO.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) WS_AC_NO += " ";
                if (DDCSOCAC.AC == null) DDCSOCAC.AC = "";
                JIBS_tmp_int = DDCSOCAC.AC.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) DDCSOCAC.AC += " ";
                WS_AC_NO = WS_AC_NO.substring(0, 4 - 1) + DDCSOCAC.AC + WS_AC_NO.substring(4 + 16 - 1);
                DDCSOCAC.AC = WS_AC_NO;
            } else {
                if (DDCSOCAC.FRG_IND == null) DDCSOCAC.FRG_IND = "";
                JIBS_tmp_int = DDCSOCAC.FRG_IND.length();
                for (int i=0;i<3-JIBS_tmp_int;i++) DDCSOCAC.FRG_IND += " ";
                if (DDCSOCAC.FRG_IND.substring(0, 2).equalsIgnoreCase("FT") 
                    && !BPCPQORG.TRA_TYP.equalsIgnoreCase("00")) {
                    CEP.TRC(SCCGWA, "-- FT AC --");
                    if (WS_AC_NO == null) WS_AC_NO = "";
                    JIBS_tmp_int = WS_AC_NO.length();
                    for (int i=0;i<32-JIBS_tmp_int;i++) WS_AC_NO += " ";
                    if (DDCSOCAC.FRG_IND == null) DDCSOCAC.FRG_IND = "";
                    JIBS_tmp_int = DDCSOCAC.FRG_IND.length();
                    for (int i=0;i<3-JIBS_tmp_int;i++) DDCSOCAC.FRG_IND += " ";
                    WS_AC_NO = DDCSOCAC.FRG_IND + WS_AC_NO.substring(3);
                    if (WS_AC_NO == null) WS_AC_NO = "";
                    JIBS_tmp_int = WS_AC_NO.length();
                    for (int i=0;i<32-JIBS_tmp_int;i++) WS_AC_NO += " ";
                    if (DDCSOCAC.AC == null) DDCSOCAC.AC = "";
                    JIBS_tmp_int = DDCSOCAC.AC.length();
                    for (int i=0;i<32-JIBS_tmp_int;i++) DDCSOCAC.AC += " ";
                    WS_AC_NO = WS_AC_NO.substring(0, 4 - 1) + DDCSOCAC.AC + WS_AC_NO.substring(4 + 16 - 1);
                    DDCSOCAC.AC = WS_AC_NO;
                }
            }
            CEP.TRC(SCCGWA, DDCSOCAC.AC);
        }
        if (DDCSOCAC.AC.trim().length() == 0) {
            BPCCGAC.DATA.AC_KIND = '1';
            BPCCGAC.DATA.CI_AC_TYPE = '1';
            BPCCGAC.DATA.CI_AC_FLG = '5';
            S000_CALL_BPZGACNO();
            CEP.TRC(SCCGWA, DDCSOCAC.FRG_IND);
            CEP.TRC(SCCGWA, BPCPQORG.TRA_TYP);
            if (DDCSOCAC.FRG_IND.equalsIgnoreCase("NRA")) {
                CEP.TRC(SCCGWA, "--NRA AC--");
                WS_AC_NO1.WS_NRA_HEAD = "NRA";
                WS_AC_NO1.WS_NRA_NO = BPCCGAC.DATA.CI_AC;
                WS_AC_NO = IBS.CLS2CPY(SCCGWA, WS_AC_NO1);
            } else {
                if (DDCSOCAC.FRG_IND == null) DDCSOCAC.FRG_IND = "";
                JIBS_tmp_int = DDCSOCAC.FRG_IND.length();
                for (int i=0;i<3-JIBS_tmp_int;i++) DDCSOCAC.FRG_IND += " ";
                if (DDCSOCAC.FRG_IND.substring(0, 2).equalsIgnoreCase("FT")) {
                    if (!BPCPQORG.TRA_TYP.equalsIgnoreCase("00")) {
                        CEP.TRC(SCCGWA, "--FT AC--");
                        WS_AC_NO1.WS_NRA_HEAD = DDCSOCAC.FRG_IND;
                        WS_AC_NO1.WS_NRA_NO = BPCCGAC.DATA.CI_AC;
                        WS_AC_NO = IBS.CLS2CPY(SCCGWA, WS_AC_NO1);
                    } else {
                        CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_FTAC_ONL_OP_IN_FTBR);
                    }
                } else {
                    CEP.TRC(SCCGWA, "--COMMON AC--");
                    WS_AC_NO = BPCCGAC.DATA.CI_AC;
                }
            }
            CEP.TRC(SCCGWA, WS_AC_NO);
            DDCSOCAC.AC = WS_AC_NO;
        }
    }
    public void B020_GENERATE_ACAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCCGAC);
        BPCCGAC.DATA.AC_KIND = '2';
        BPCCGAC.DATA.ACO_AC_FLG = '8';
        BPCCGAC.DATA.ACO_AC_MMO = "11";
        BPCCGAC.DATA.ACO_AC_DEF = 833;
        S000_CALL_BPZGACNO();
        CEP.TRC(SCCGWA, BPCCGAC.DATA.ACO_AC);
        DDCSOCAC.ACAC = BPCCGAC.DATA.ACO_AC;
    }
    public void B030_PSBK_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIPSBK);
        DDCIPSBK.FUNC = 'F';
        DDCIPSBK.AC = DDCSOCAC.AC;
        DDCIPSBK.VCH_TYPE = '3';
        DDCIPSBK.BV_TYPE = '9';
        DDCIPSBK.PAY_MTH = DDCSOCAC.PAY_MTH;
        DDCIPSBK.UCIP_FLG = 'N';
        DDCIPSBK.ID_TYPE = CICCUST.O_DATA.O_ID_TYPE;
        DDCIPSBK.ID_NO = CICCUST.O_DATA.O_ID_NO;
        DDCIPSBK.AC_CNAME = CICCUST.O_DATA.O_CI_NM;
        DDCIPSBK.AC_ENAME = CICCUST.O_DATA.O_CI_ENM;
        S000_CALL_DDZIPSBK();
    }
    public void B040_CRT_CIZSACAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACAC);
        CICSACAC.FUNC = 'A';
        CICSACAC.DATA.ACAC_NO = DDCSOCAC.ACAC;
        CICSACAC.DATA.AGR_NO = DDCSOCAC.AC;
        CICSACAC.DATA.CCY = DDCSOCAC.AC_CCY;
        CICSACAC.DATA.CR_FLG = DDCSOCAC.CCY_TYPE;
        CICSACAC.DATA.ACAC_CTL = "00000000000000000000";
        if ((DDCSOCAC.FUNC != '1')) {
            if (CICSACAC.DATA.ACAC_CTL == null) CICSACAC.DATA.ACAC_CTL = "";
            JIBS_tmp_int = CICSACAC.DATA.ACAC_CTL.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) CICSACAC.DATA.ACAC_CTL += " ";
            CICSACAC.DATA.ACAC_CTL = "01" + CICSACAC.DATA.ACAC_CTL.substring(2);
        }
        CICSACAC.DATA.PROD_CD = DDCSOCAC.PROD_CODE;
        CICSACAC.DATA.FRM_APP = "DD";
        CICSACAC.DATA.OPN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICSACAC.DATA.OPEN_DT = SCCGWA.COMM_AREA.AC_DATE;
        CICSACAC.DATA.OWNER_BK = BPCPORUP.DATA_INFO.BR;
        S000_CALL_CIZSACAC();
    }
    public void B040_CRT_DD_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSOCAC.AC;
        DDRMST.OWNER_BK = BPCPORUP.DATA_INFO.BR;
        DDRMST.PROD_CODE = DDCSOCAC.PROD_CODE;
        DDRMST.AC_STS = 'V';
        DDRMST.AC_STS_WORD = "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        DDRMST.AC_STS_WORD = "1" + DDRMST.AC_STS_WORD.substring(1);
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 20 - 1) + "1" + DDRMST.AC_STS_WORD.substring(20 + 1 - 1);
        if (DDCSOCAC.PROD_CODE.equalsIgnoreCase("2206080101")) {
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 62 - 1) + "1" + DDRMST.AC_STS_WORD.substring(62 + 1 - 1);
        }
        if (DDVMPRD.VAL.TD_FLG == 'Y') {
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 65 - 1) + "1" + DDRMST.AC_STS_WORD.substring(65 + 1 - 1);
        }
        CEP.TRC(SCCGWA, DDCSOCAC.AC_TYP);
        CEP.TRC(SCCGWA, DDCSOCAC.CHCK_IND);
        DDRMST.CCY_FLG = DDCSOCAC.CCY_FLG;
        DDRMST.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.OPEN_DP = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRMST.OWNER_BR = BPCPORUP.DATA_INFO.BBR;
        DDRMST.OWNER_BRDP = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRMST.OPEN_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRMST.CLOSE_DATE = 0;
        DDRMST.LAST_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRMST.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.LAST_FN_DATE = 0;
        DDRMST.CARD_FLG = ' ';
        DDRMST.PRDAC_CD = "   ";
        DDRMST.AC_TYPE = DDCSOCAC.AC_TYP;
        DDRMST.FRG_TYPE = "  ";
        DDRMST.FRG_CODE = DDCSOCAC.FRG_CODE;
        DDRMST.FRG_OPEN_NO = DDCSOCAC.FR_OP_NO;
        DDRMST.CHCK_IND = DDCSOCAC.CHCK_IND;
        DDRMST.FRG_IND = DDCSOCAC.FRG_IND;
        DDRMST.AC_PURP = DDCSOCAC.AC_PURP;
        DDRMST.PAY_MTH = DDCSOCAC.PAY_MTH;
        DDRMST.FEE_METH = DDCSOCAC.FEE_METH;
        if (DDCSOCAC.CASH_FLG == ' ') {
            DDRMST.CASH_FLG = DDVMPRD.VAL.CASH_TXN_TYPE;
        } else {
            DDRMST.CASH_FLG = DDCSOCAC.CASH_FLG;
        }
        DDRMST.CROS_CR_FLG = DDCSOCAC.CR_CR_FL;
        DDRMST.CROS_DR_FLG = DDCSOCAC.CR_DR_FL;
        DDRMST.SPC_KIND = DDCSOCAC.SPC_KIND;
        DDRMST.GEN_RSN = ' ';
        DDRMST.CI_TYP = CICCUST.O_DATA.O_CI_TYP;
        DDRMST.AC_OIC_NO = DDCSOCAC.OIC_NO;
        DDRMST.AC_OIC_CODE = DDCSOCAC.OIC_CODE;
        DDRMST.SUB_DP = DDCSOCAC.SUB_DP;
        DDRMST.RLT_PROD_CODE = DDVMPRD.VAL.TD_PROD;
        if (DDVMPRD.VAL.TD_FLG == 'Y') {
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 65 - 1) + "1" + DDRMST.AC_STS_WORD.substring(65 + 1 - 1);
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        JIBS_tmp_str[0] = "" + DDCSOCAC.REG_FLG;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 22 - 1) + JIBS_tmp_str[0] + DDRMST.AC_STS_WORD.substring(22 + 1 - 1);
        DDRMST.YW_TYP = DDCSOCAC.YW_TYP;
        DDRMST.INT_TYP = DDCSOCAC.INT_TYP;
        DDRMST.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRMST.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        if (DDCSOCAC.FRG_IND.equalsIgnoreCase("OSA")) {
            DDRMST.CHCK_IND = '2';
            DDRMST.AC_STS = 'N';
            DDRMST.AC_STS_WORD = "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        }
        CEP.TRC(SCCGWA, DDCSOCAC.TLR_FLG);
        if (DDCSOCAC.TLR_FLG == 'Y') {
            DDRMST.AC_STS = 'O';
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            DDRMST.AC_STS_WORD = "01" + DDRMST.AC_STS_WORD.substring(2);
        }
        if (DDCSOCAC.AC_TYP == 'N' 
            || DDCSOCAC.AC_TYP == 'J' 
            || DDCSOCAC.AC_TYP == 'O') {
            DDRMST.AC_STS = 'N';
            DDRMST.AC_STS_WORD = "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
            DDRMST.CHCK_IND = '3';
        }
        CEP.TRC(SCCGWA, DDCSOCAC.AC_CCY);
        if (DDCSOCAC.AC_CCY.trim().length() > 0 
            && !DDCSOCAC.AC_CCY.equalsIgnoreCase("156")) {
            DDRMST.AC_STS = 'N';
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 2 - 1) + "0" + DDRMST.AC_STS_WORD.substring(2 + 1 - 1);
        }
        CEP.TRC(SCCGWA, DDRMST.AC_STS);
        T000_WRITE_MST_PROC();
    }
    public void B050_CRT_DD_AC_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRINF);
        DDRINF.KEY.CUS_AC = DDCSOCAC.AC;
        DDRINF.AMT_TYPE = DDCSOCAC.FIN_TYP;
        DDRINF.TXN_TYPE = DDCSOCAC.TXN_TYP;
        T000_WRITE_INF_PROC();
    }
    public void B060_CUS_RLT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACR);
        CICSACR.FUNC = 'A';
        CICSACR.DATA.AGR_NO = DDCSOCAC.AC;
        CICSACR.DATA.ENTY_TYP = '1';
        CICSACR.DATA.CI_NO = CICCUST.O_DATA.O_CI_NO;
        CICSACR.DATA.STSW = "10010000";
        CICSACR.DATA.PROD_CD = DDCSOCAC.PROD_CODE;
        CICSACR.DATA.CCY = DDCSOCAC.AC_CCY;
        CICSACR.DATA.SHOW_FLG = 'Y';
        CICSACR.DATA.AC_CNM = DDCSOCAC.AC_CNM;
        CICSACR.DATA.AC_ENM = DDCSOCAC.AC_ENM;
        if ((DDCSOCAC.AC_TYP == 'N' 
                || DDCSOCAC.AC_TYP == 'O' 
                || DDCSOCAC.AC_TYP == 'J')) {
            CICSACR.DATA.CNTRCT_TYP = "033";
        } else {
            CICSACR.DATA.CNTRCT_TYP = "199";
        }
        if (DDCSOCAC.AC_TYP == 'N') {
            CICSACR.CTL_FLG.EXP_FLG = 'Y';
            CICSACR.CTL_STSW = IBS.CLS2CPY(SCCGWA, CICSACR.CTL_FLG);
        }
        CICSACR.DATA.FRM_APP = "DD";
        CICSACR.DATA.OPN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICSACR.DATA.OPEN_DT = SCCGWA.COMM_AREA.AC_DATE;
        S000_CALL_CIZSACR();
    }
    public void B070_CRT_DD_AC_CCY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = DDCSOCAC.ACAC;
        DDRCCY.OWNER_BK = BPCPORUP.DATA_INFO.BR;
        DDRCCY.CUS_AC = DDCSOCAC.AC;
        DDRCCY.CCY = DDCSOCAC.AC_CCY;
        DDRCCY.CCY_TYPE = DDCSOCAC.CCY_TYPE;
        DDRCCY.STS = 'N';
        DDRCCY.STS_WORD = "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (DDVMPRD.VAL.AUFR_FLG == 'N') {
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 20 - 1) + "1" + DDRCCY.STS_WORD.substring(20 + 1 - 1);
        }
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 2 - 1) + "3" + DDRCCY.STS_WORD.substring(2 + 1 - 1);
        if (DDCSOCAC.AC_TYP == 'A' 
            && !DDCSOCAC.AC_CCY.equalsIgnoreCase("156")) {
            DDRCCY.AC_FT = 'V';
        }
        if (DDCSOCAC.AC_TYP == 'J' 
            || DDCSOCAC.AC_TYP == 'N' 
            || DDCSOCAC.AC_TYP == 'O') {
            DDRCCY.AC_TYPE = '3';
        } else {
            DDRCCY.AC_TYPE = '1';
        }
        CEP.TRC(SCCGWA, WS_NRA_FLG);
        if (WS_NRA_FLG == 'Y') {
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 68 - 1) + "1" + DDRCCY.STS_WORD.substring(68 + 1 - 1);
        }
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        CEP.TRC(SCCGWA, DDRCCY.STS_WORD.substring(68 - 1, 68 + 1 - 1));
        DDRCCY.PROD_CODE = DDCSOCAC.PROD_CODE;
        DDRCCY.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.LAST_FN_DATE = 0;
        DDRCCY.OPEN_DP = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRCCY.OPEN_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRCCY.OWNER_BR = BPCPORUP.DATA_INFO.BBR;
        DDRCCY.OWNER_BRDP = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRCCY.POST_AC = DDCSOCAC.POST_AC;
        if (DDVMPRD.VAL.CAL_DINT_METH == '1' 
            || DDVMPRD.VAL.CAL_DINT_METH == '2') {
            DDRCCY.CINT_FLG = 'Y';
        } else {
            DDRCCY.CINT_FLG = 'N';
        }
        DDRCCY.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRCCY.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
        CEP.TRC(SCCGWA, DDRCCY.CCY);
        CEP.TRC(SCCGWA, DDRCCY.CCY_TYPE);
        T000_WRITE_CCY_PROC();
    }
    public void B080_WRITE_GL_MASTER_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSOCAC.PROD_CODE);
        IBS.init(SCCGWA, BPCUCNGM);
        BPCUCNGM.KEY.AC = DDRCCY.KEY.AC;
        BPCUCNGM.KEY.CNTR_TYPE = DDCIQPRD.OUTPUT_DATA.PRDT_MODEL;
        BPCUCNGM.PROD_TYPE = DDCSOCAC.PROD_CODE;
        BPCUCNGM.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUCNGM.DATA[1-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[1-1].MSTNO;
        BPCUCNGM.DATA[2-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[2-1].MSTNO;
        BPCUCNGM.FUNC = 'A';
        CEP.TRC(SCCGWA, BPCUCNGM.KEY.AC);
        CEP.TRC(SCCGWA, BPCUCNGM.KEY.CNTR_TYPE);
        CEP.TRC(SCCGWA, BPCUCNGM.PROD_TYPE);
        CEP.TRC(SCCGWA, BPCUCNGM.BR);
        CEP.TRC(SCCGWA, BPCUCNGM.DATA[1-1].GLMST);
        CEP.TRC(SCCGWA, BPCUCNGM.DATA[2-1].GLMST);
        S000_CALL_BPZUCNGM();
    }
    public void B090_NON_FIN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.AC = DDCSOCAC.AC;
        BPCPNHIS.INFO.CI_NO = DDCSOCAC.CI_NO;
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.FMT_ID = K_HIS_CPB_NAME;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.TX_TYP_CD = K_HIS_MMO;
        BPCPNHIS.INFO.CCY = DDCSOCAC.AC_CCY;
        BPCPNHIS.INFO.CCY_TYPE = DDCSOCAC.CCY_TYPE;
        CEP.TRC(SCCGWA, DDCSOCAC.AC_CCY);
        CEP.TRC(SCCGWA, DDCSOCAC.CCY_TYPE);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.JRNNO);
        S000_CALL_BPZPNHIS();
    }
    public void B100_CRT_BP_OCAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOCAC);
        BPCPOCAC.FUNC = 'C';
        CEP.TRC(SCCGWA, DDCSOCAC.ACAC);
        BPCPOCAC.ACO_AC = DDCSOCAC.ACAC;
        BPCPOCAC.AC = DDCSOCAC.AC;
        BPCPOCAC.STS = 'N';
        BPCPOCAC.WORK_TYP = "14";
        if (CICCUST.O_DATA.O_CI_TYP == '3') {
            BPCPOCAC.WORK_TYP = "30";
        }
        if (DDCSOCAC.AC_TYP == 'N' 
            || DDCSOCAC.AC_TYP == 'J' 
            || DDCSOCAC.AC_TYP == 'O') {
            BPCPOCAC.WORK_TYP = "29";
        }
        BPCPOCAC.CAL_TYP = "" + DDCSOCAC.AC_TYP;
        JIBS_tmp_int = BPCPOCAC.CAL_TYP.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) BPCPOCAC.CAL_TYP = "0" + BPCPOCAC.CAL_TYP;
        BPCPOCAC.CI_TYPE = CICCUST.O_DATA.O_CI_TYP;
        BPCPOCAC.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
        BPCPOCAC.ID_NO = CICCUST.O_DATA.O_ID_NO;
        BPCPOCAC.CI_CNM = CICCUST.O_DATA.O_CI_NM;
        BPCPOCAC.CCY = DDCSOCAC.AC_CCY;
        BPCPOCAC.CCY_TYPE = DDCSOCAC.CCY_TYPE;
        BPCPOCAC.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOCAC.CREATE_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOCAC.OPEN_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPOCAC.CREATE_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPOCAC.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        BPCPOCAC.PROD_CD = DDCSOCAC.PROD_CODE;
        BPCPOCAC.AC_CNM = DDCSOCAC.AC_CNM;
        S000_CALL_BPZSOCAC();
    }
    public void B100_CRT_BP_OCAC_PROC_CUS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOCAC);
        BPCPOCAC.FUNC = 'C';
        CEP.TRC(SCCGWA, DDCSOCAC.ACAC);
        BPCPOCAC.AC = DDCSOCAC.AC;
        BPCPOCAC.STS = 'N';
        BPCPOCAC.WORK_TYP = "14";
        if (CICCUST.O_DATA.O_CI_TYP == '3') {
            BPCPOCAC.WORK_TYP = "30";
        }
        if (DDCSOCAC.AC_TYP == 'N' 
            || DDCSOCAC.AC_TYP == 'J' 
            || DDCSOCAC.AC_TYP == 'O') {
            BPCPOCAC.WORK_TYP = "29";
        }
        BPCPOCAC.CAL_TYP = "" + DDCSOCAC.AC_TYP;
        JIBS_tmp_int = BPCPOCAC.CAL_TYP.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) BPCPOCAC.CAL_TYP = "0" + BPCPOCAC.CAL_TYP;
        BPCPOCAC.CI_TYPE = CICCUST.O_DATA.O_CI_TYP;
        BPCPOCAC.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
        BPCPOCAC.ID_NO = CICCUST.O_DATA.O_ID_NO;
        BPCPOCAC.CI_CNM = CICCUST.O_DATA.O_CI_NM;
        BPCPOCAC.CCY = DDCSOCAC.AC_CCY;
        BPCPOCAC.CCY_TYPE = DDCSOCAC.CCY_TYPE;
        BPCPOCAC.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOCAC.CREATE_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOCAC.OPEN_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPOCAC.CREATE_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPOCAC.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        BPCPOCAC.PROD_CD = DDCSOCAC.PROD_CODE;
        BPCPOCAC.AC_CNM = DDCSOCAC.AC_CNM;
        S000_CALL_BPZSOCAC();
    }
    public void T000_READ_UPDATE_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.where = "CUS_AC = :DDRMST.KEY.CUS_AC";
        IBS.READ(SCCGWA, DDRMST, this, DDTMST_RD);
    }
    public void S000_SET_FEE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = DDCSOCAC.AC;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = DDCSOCAC.AC_CCY;
        BPCFFTXI.TX_DATA.CCY_TYPE = DDCSOCAC.CCY_TYPE;
        BPCFFTXI.TX_DATA.SVR_CD = SCCGWA.COMM_AREA.SERV_CODE;
        BPCFFTXI.TX_DATA.CI_NO = DDCSOCAC.CI_NO;
        CEP.TRC(SCCGWA, DDCSOCAC.FEE_METH);
        if (DDCSOCAC.FEE_METH == 'Y') {
            BPCFFTXI.TX_DATA.PROC_TYPE = '6';
        }
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC);
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY);
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CCY_TYPE);
        S000_CALL_BPZFFTXI();
    }
    public void S000_CALL_FEE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTCALF);
        BPCTCALF.INPUT_AREA.FUNC_CODE = 'C';
        BPCTCALF.INPUT_AREA.TX_CCY = DDCSOCAC.AC_CCY;
        BPCTCALF.INPUT_AREA.TX_AC = DDCSOCAC.AC;
        BPCTCALF.INPUT_AREA.TX_CNT = 1;
        BPCTCALF.INPUT_AREA.TX_AMT = 0;
        BPCTCALF.INPUT_AREA.TX_CI = DDCSOCAC.CI_NO;
        BPCTCALF.INPUT_AREA.PROD_CODE = DDCSOCAC.PROD_CODE;
        BPCTCALF.INPUT_AREA.PROD_CODE1 = DDCSOCAC.PROD_CODE;
        S000_CALL_BPZFCALF();
    }
    public void B110_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOOCAC);
        DDCOOCAC.CI_NO = DDCSOCAC.CI_NO;
        DDCOOCAC.PROD_CODE = DDCSOCAC.PROD_CODE;
        DDCOOCAC.AC = DDCSOCAC.AC;
        DDCOOCAC.AC_CNM = DDCSOCAC.AC_CNM;
        DDCOOCAC.AC_ENM = DDCSOCAC.AC_ENM;
        DDCOOCAC.AC_CCY = DDCSOCAC.AC_CCY;
        DDCOOCAC.CCY_TYPE = DDCSOCAC.CCY_TYPE;
        DDCOOCAC.AC_TYP = DDCSOCAC.AC_TYP;
        DDCOOCAC.SPC_KIND = DDCSOCAC.SPC_KIND;
        DDCOOCAC.FRG_CODE = DDCSOCAC.FRG_CODE;
        DDCOOCAC.FR_OP_NO = DDCSOCAC.FR_OP_NO;
        DDCOOCAC.FRG_IND = DDCSOCAC.FRG_IND;
        DDCOOCAC.AC_PURP = DDCSOCAC.AC_PURP;
        DDCOOCAC.PAY_MTH = DDCSOCAC.PAY_MTH;
        DDCOOCAC.FEE_METH = DDCSOCAC.FEE_METH;
        DDCOOCAC.CR_CR_FL = DDCSOCAC.CR_CR_FL;
        DDCOOCAC.CR_DR_FL = DDCSOCAC.CR_DR_FL;
        DDCOOCAC.CASH_FLG = DDCSOCAC.CASH_FLG;
        DDCOOCAC.OPEN_DP = DDCSOCAC.OPEN_DP;
        DDCOOCAC.CHCK_IND = DDCSOCAC.CHCK_IND;
        DDCOOCAC.OIC_NO = DDCSOCAC.OIC_NO;
        DDCOOCAC.OIC_CODE = DDCSOCAC.OIC_CODE;
        DDCOOCAC.SUB_DP = DDCSOCAC.SUB_DP;
        DDCOOCAC.FIN_TYP = DDCSOCAC.FIN_TYP;
        DDCOOCAC.TXN_TYP = DDCSOCAC.TXN_TYP;
        DDCOOCAC.POST_AC = DDCSOCAC.POST_AC;
        CEP.TRC(SCCGWA, DDCOOCAC.CI_NO);
        CEP.TRC(SCCGWA, DDCOOCAC.PROD_CODE);
        CEP.TRC(SCCGWA, DDCOOCAC.AC);
        CEP.TRC(SCCGWA, DDCOOCAC.AC_CNM);
        CEP.TRC(SCCGWA, DDCOOCAC.AC_ENM);
        CEP.TRC(SCCGWA, DDCOOCAC.AC_CCY);
        CEP.TRC(SCCGWA, DDCOOCAC.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCOOCAC.AC_TYP);
        CEP.TRC(SCCGWA, DDCOOCAC.SPC_KIND);
        CEP.TRC(SCCGWA, DDCOOCAC.EXP_DATE);
        CEP.TRC(SCCGWA, DDCOOCAC.FRG_CODE);
        CEP.TRC(SCCGWA, DDCOOCAC.FR_OP_NO);
        CEP.TRC(SCCGWA, DDCOOCAC.FRG_IND);
        CEP.TRC(SCCGWA, DDCOOCAC.AC_PURP);
        CEP.TRC(SCCGWA, DDCOOCAC.PAY_MTH);
        CEP.TRC(SCCGWA, DDCOOCAC.FEE_METH);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT1;
        SCCFMT.DATA_PTR = DDCOOCAC;
        SCCFMT.DATA_LEN = 714;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B130_INQ_GL_MASTER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCNGL);
        IBS.init(SCCGWA, BPCACAAC);
        BPCQCNGL.DAT.INPUT.CNTR_TYPE = DDCIQPRD.OUTPUT_DATA.PRDT_MODEL;
        CEP.TRC(SCCGWA, DDCIQPRD.OUTPUT_DATA.PRDT_MODEL);
        CEP.TRC(SCCGWA, DDCSOCAC.PROD_CODE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        BPCQCNGL.DAT.INPUT.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCQCNGL.DAT.INPUT.OTH_PTR_LEN = 21;
        BPCACAAC.PROD_TYPE = DDCSOCAC.PROD_CODE;
        BPCACAAC.CI_TYPE = CICCUST.O_DATA.O_CI_TYP;
        BPCACAAC.FIN_TYP = CICCUST.O_DATA.O_FIN_TYPE;
        BPCACAAC.AC_TYP = DDCSOCAC.AC_TYP;
        BPCACAAC.PROP_TYP = DDCSOCAC.FIN_TYP;
        CEP.TRC(SCCGWA, BPCACAAC.PROD_TYPE);
        CEP.TRC(SCCGWA, BPCACAAC.CI_TYPE);
        CEP.TRC(SCCGWA, BPCACAAC.FIN_TYP);
        CEP.TRC(SCCGWA, BPCACAAC.AC_TYP);
        CEP.TRC(SCCGWA, BPCACAAC.PROP_TYP);
        BPCQCNGL.DAT.INPUT.OTH_PTR = BPCACAAC;
        S000_CALL_BPZQCNGL();
    }
    public void B120_WAVE_ANNUAL_FEES() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUFEES);
        DDCUFEES.FUNC = '3';
        DDCUFEES.AC_NO = DDCSOCAC.AC;
        DDCUFEES.CI_NO = DDCSOCAC.CI_NO;
        S000_CALL_DDZUFEES();
    }
    public void B150_SEAL_INFO_ADD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRSSTS);
        DCRSSTS.KEY.AC = DDCSOCAC.AC;
        DCRSSTS.KEY.CCY = DDCSOCAC.AC_CCY;
        DCRSSTS.KEY.CCY_TYPE = DDCSOCAC.CCY_TYPE;
        DCRSSTS.KEY.SEQ = 1;
        DCRSSTS.STS = '1';
        DCRSSTS.AC_TYPE = DDCSOCAC.AC_TYP;
        DCRSSTS.OPEN_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRSSTS.OPEN_DP = DDRMST.OPEN_DP;
        DCRSSTS.FRM_APP = "DD";
        DCRSSTS.CROS_DR_FLG = DDCSOCAC.CR_DR_FL;
        DCRSSTS.REMARK = " ";
        DCRSSTS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRSSTS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRSSTS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRSSTS.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_WRITE_DCTSSTS();
    }
    public void B160_REG_FR_OP_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSFEI);
        CICSFEI.FUNC = 'A';
        CICSFEI.DATA.SVS_ADC_NO = DDCSOCAC.FR_OP_NO;
        CICSFEI.DATA.CI_NO = DDCSOCAC.CI_NO;
        CICSFEI.DATA.AGR_NO = DDCSOCAC.AC;
        CICSFEI.DATA.LMT_TYP = DDCSOCAC.LMT_TYP;
        CICSFEI.DATA.LMT_CCY = DDCSOCAC.AC_CCY;
        if (DDCSOCAC.LMT_TYP == '1') {
            CICSFEI.DATA.BAL_LMT = DDCSOCAC.LMT_AMT;
        } else if (DDCSOCAC.LMT_TYP == '2') {
            CICSFEI.DATA.CR_TOT_LMT_AMT = DDCSOCAC.LMT_AMT;
        } else if (DDCSOCAC.LMT_TYP == '3') {
            CICSFEI.DATA.DR_TOT_LMT_AMT = DDCSOCAC.LMT_AMT;
        } else {
        }
        S000_CALL_CIZSFEI();
    }
    public void B161_REG_CUS_AC_FR_OP_NO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSOCAC.FR_OP_NO);
        CEP.TRC(SCCGWA, DDCSOCAC.AC);
        IBS.init(SCCGWA, CICMFRL);
        CICMFRL.FUNC = 'A';
        CICMFRL.DATA.SVS_ADC_NO = DDCSOCAC.FR_OP_NO;
        CICMFRL.DATA.AGR_NO = DDCSOCAC.AC;
        CICMFRL.DATA.CI_NO = DDCSOCAC.CI_NO;
        S000_CALL_CIZMFRL();
    }
    public void T000_WRITE_MST_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRMST.KEY.CUS_AC);
        CEP.TRC(SCCGWA, DDRMST.PROD_CODE);
        CEP.TRC(SCCGWA, DDRMST.AC_STS);
        CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD);
        CEP.TRC(SCCGWA, DDRMST.CCY_FLG);
        CEP.TRC(SCCGWA, DDRMST.OPEN_DATE);
        CEP.TRC(SCCGWA, DDRMST.EFF_DATE);
        CEP.TRC(SCCGWA, DDRMST.EXP_DATE);
        CEP.TRC(SCCGWA, DDRMST.PRDAC_CD);
        CEP.TRC(SCCGWA, DDRMST.AC_TYPE);
        CEP.TRC(SCCGWA, DDRMST.OPEN_BK);
        CEP.TRC(SCCGWA, DDRMST.OPEN_DP);
        CEP.TRC(SCCGWA, DDRMST.OPEN_TLR);
        CEP.TRC(SCCGWA, DDRMST.CLOSE_DATE);
        CEP.TRC(SCCGWA, DDRMST.LAST_TLR);
        CEP.TRC(SCCGWA, DDRMST.LAST_DATE);
        CEP.TRC(SCCGWA, DDRMST.LAST_FN_DATE);
        CEP.TRC(SCCGWA, DDRMST.CARD_FLG);
        CEP.TRC(SCCGWA, DDRMST.FRG_TYPE);
        CEP.TRC(SCCGWA, DDRMST.FRG_CODE);
        CEP.TRC(SCCGWA, DDRMST.FRG_OPEN_NO);
        CEP.TRC(SCCGWA, DDRMST.CHCK_IND);
        CEP.TRC(SCCGWA, DDRMST.FRG_IND);
        CEP.TRC(SCCGWA, DDRMST.AC_PURP);
        CEP.TRC(SCCGWA, DDRMST.PAY_MTH);
        CEP.TRC(SCCGWA, DDRMST.CASH_FLG);
        CEP.TRC(SCCGWA, DDRMST.CROS_DR_FLG);
        CEP.TRC(SCCGWA, DDRMST.CROS_CR_FLG);
        CEP.TRC(SCCGWA, DDRMST.SPC_KIND);
        CEP.TRC(SCCGWA, DDRMST.GEN_RSN);
        CEP.TRC(SCCGWA, DDRMST.CI_TYP);
        CEP.TRC(SCCGWA, DDRMST.PBC_APPR_DATE);
        CEP.TRC(SCCGWA, DDRMST.CRT_DATE);
        CEP.TRC(SCCGWA, DDRMST.CRT_TLR);
        CEP.TRC(SCCGWA, DDRMST.UPDTBL_DATE);
        CEP.TRC(SCCGWA, DDRMST.UPDTBL_TLR);
        CEP.TRC(SCCGWA, DDRMST.TS);
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void B170_REGISTER_VTTJMAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, VTCUJMDR);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_SID_FLG);
        if (CICCUST.O_DATA.O_IDE_STSW == null) CICCUST.O_DATA.O_IDE_STSW = "";
        JIBS_tmp_int = CICCUST.O_DATA.O_IDE_STSW.length();
        for (int i=0;i<30-JIBS_tmp_int;i++) CICCUST.O_DATA.O_IDE_STSW += " ";
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_IDE_STSW.substring(7 - 1, 7 + 1 - 1));
        VTCUJMDR.INPUT_DATA.FUNC = 'A';
        VTCUJMDR.INPUT_DATA.OTH.OVER_FLG = CICCUST.O_DATA.O_SID_FLG;
        if (CICCUST.O_DATA.O_IDE_STSW == null) CICCUST.O_DATA.O_IDE_STSW = "";
        JIBS_tmp_int = CICCUST.O_DATA.O_IDE_STSW.length();
        for (int i=0;i<30-JIBS_tmp_int;i++) CICCUST.O_DATA.O_IDE_STSW += " ";
        if (CICCUST.O_DATA.O_IDE_STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
            VTCUJMDR.INPUT_DATA.OTH.NCB_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, VTCUJMDR.INPUT_DATA.OTH.OVER_FLG);
        CEP.TRC(SCCGWA, VTCUJMDR.INPUT_DATA.OTH.NCB_FLG);
        CEP.TRC(SCCGWA, VTCUJMDR.INPUT_DATA.OTH.WEI_FLG);
        VTCUJMDR.INPUT_DATA.AC = DDCSOCAC.ACAC;
        VTCUJMDR.INPUT_DATA.CCY = DDCSOCAC.AC_CCY;
        VTCUJMDR.INPUT_DATA.BR = BPCPORUP.DATA_INFO.BR;
        VTCUJMDR.INPUT_DATA.PROD_CD = DDCSOCAC.PROD_CODE;
        S000_CALL_VTCUJMDR();
    }
    public void S000_CALL_VTCUJMDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "VT-P-JMAC-REGISTER", VTCUJMDR);
        CEP.TRC(SCCGWA, VTCUJMDR.RC.RC_CODE);
        CEP.TRC(SCCGWA, VTCUJMDR.RC.RC_MMO);
        if (VTCUJMDR.RC.RC_CODE != 0) {
            WS_ERR_MSG = VTCUJMDR.RC.RC_MMO;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_WRITE_INF_PROC() throws IOException,SQLException,Exception {
        DDTINF_RD = new DBParm();
        DDTINF_RD.TableName = "DDTINF";
        DDTINF_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DDRINF, DDTINF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_WRITE_CCY_PROC() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY1_REC_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_WRITE_DCTSSTS() throws IOException,SQLException,Exception {
        DCTSSTS_RD = new DBParm();
        DCTSSTS_RD.TableName = "DCTSSTS";
        DCTSSTS_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DCRSSTS, DCTSSTS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZGACNO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-GENERTE-ACNO", BPCCGAC);
        CEP.TRC(SCCGWA, BPCCGAC.RC);
        if (BPCCGAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCCGAC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZUACNO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-USING-OBLAC", BPCUACNO);
        CEP.TRC(SCCGWA, BPCUACNO.RC);
        if (BPCUACNO.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUACNO.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_LINK_CIZQACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR", CICQACR, true);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFCSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-STS-TBL-AUTH", BPCFCSTS);
        if (BPCFCSTS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFCSTS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZUCNGM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-MAINT-CNGM", BPCUCNGM);
        CEP.TRC(SCCGWA, BPCUCNGM.RC);
        if (BPCUCNGM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUCNGM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSOCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-OCAC-INFO", BPCPOCAC);
    }
    public void S000_CALL_CIZMACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-ACR", CICMACR);
        if (CICMACR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICMACR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZSACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACR", CICSACR);
        if (CICSACR.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSACR.RC);
        }
    }
    public void S000_CALL_CISOCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
    }
    public void S000_CALL_CIZOPCS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-OPEN-CI-SIMPLY", CICOPCS, true);
    }
    public void S000_CALL_DDZIQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-DDPRD", DDCIQPRD);
        if (DDCIQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQPRD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZIPSBK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-PSBK-PROC", DDCIPSBK);
    }
    public void S000_CALL_BPZQCNGL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-CNGL", BPCQCNGL);
        CEP.TRC(SCCGWA, BPCQCNGL.RC);
        if (BPCQCNGL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCNGL.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSMPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-PRDT-INFO", BPCSMPRD);
        if (BPCSMPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSMPRD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPCKPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-PRDT-COM-CHECK", BPCPCKPD);
        if (BPCPCKPD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPCKPD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-TX-INFO", BPCFFTXI);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC.RC_RTNCODE);
        if (BPCPRMM.RC.RC_RTNCODE != 0 
            && BPCPRMM.RC.RC_RTNCODE != 1801 
            && BPCPRMM.RC.RC_RTNCODE != 180) {
            CEP.ERR(SCCGWA, BPCPRMM.RC);
        }
    }
    public void S000_CALL_BPZFCALF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-CAL-FEE", BPCTCALF);
        if (BPCTCALF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTCALF.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZQCIAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST-ACR", CICQCIAC);
        CEP.TRC(SCCGWA, CICQCIAC.RC);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACAC.RC);
        }
    }
    public void S000_CALL_CIZSACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACAC", CICSACAC);
        if (CICSACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSACAC.RC);
        }
    }
    public void S000_CALL_DDZUFEES() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-M-WAVE-FEES", DDCUFEES);
        CEP.TRC(SCCGWA, DDCUFEES.RC);
        if (DDCUFEES.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DDCUFEES.RC);
        }
    }
    public void S000_CALL_CIZSFEI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-FEA-INF", CICSFEI);
        CEP.TRC(SCCGWA, CICSFEI.RC);
        if (CICSFEI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSFEI.RC);
        }
    }
    public void S000_CALL_CIZSFEA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-FEA-AC-REL", CICSFEI);
        CEP.TRC(SCCGWA, CICSFEA.RC);
        if (CICSFEA.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSFEA.RC);
        }
    }
    public void S000_CALL_CIZMFRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-PROC-FLRL", CICMFRL);
        CEP.TRC(SCCGWA, CICMFRL.RC);
        if (CICMFRL.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICMFRL.RC);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_INF() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INFO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
