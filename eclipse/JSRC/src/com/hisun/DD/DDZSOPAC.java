package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.DC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSOPAC {
    String K_OUTPUT_FMT1 = "DD504";
    String K_OUTPUT_FMT2 = "DD520";
    String K_APP_MMO = "DD";
    String K_CI_STS_TBL = "0001";
    String K_HIS_CPB_NAME = "DDCHOPAC";
    String K_HIS_REMARKS = "OPEN ACCOUNT";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    String WS_AC_NO = " ";
    String WS_AC_CCY = "156";
    short WS_RC_CODE = 0;
    short WS_CNT = 0;
    DDZSOPAC_WS_BKAC_DATA WS_BKAC_DATA = new DDZSOPAC_WS_BKAC_DATA();
    DDZSOPAC_WS_PRINT_WEEK WS_PRINT_WEEK = new DDZSOPAC_WS_PRINT_WEEK();
    DDZSOPAC_WS_PRINT_MON WS_PRINT_MON = new DDZSOPAC_WS_PRINT_MON();
    DDZSOPAC_WS_PRINT_DAY WS_PRINT_DAY = new DDZSOPAC_WS_PRINT_DAY();
    char WS_AC_STS = ' ';
    DDZSOPAC_WS_MMDD WS_MMDD = new DDZSOPAC_WS_MMDD();
    int WS_EFF_DATE = 0;
    int WS_BR = 0;
    char WS_CCY_FOUND_FLG = ' ';
    char WS_AC_TYPE = ' ';
    char WS_BV_TYPE = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCOOPAC DDCOOPAC = new DDCOOPAC();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    DDCIPSBK DDCIPSBK = new DDCIPSBK();
    BPCCGAC BPCCGAC = new BPCCGAC();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCQCNGL BPCQCNGL = new BPCQCNGL();
    BPCAMMDP BPCAMMDP = new BPCAMMDP();
    DCCSSPOT DCCSSPOT = new DCCSSPOT();
    DCCSSPEC DCCSSPEC = new DCCSSPEC();
    BPCUCNGM BPCUCNGM = new BPCUCNGM();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    BPCPQORG BPCPQORG = new BPCPQORG();
    CICMACR CICMACR = new CICMACR();
    CICCUST CICCUST = new CICCUST();
    CICSSCH CICSSCH = new CICSSCH();
    CICOPCS CICOPCS = new CICOPCS();
    SCCBSP SCCBSP = new SCCBSP();
    SCCBSPS SCCBSPS = new SCCBSPS();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    DDCOSINO DDCOSINO = new DDCOSINO();
    DDCUOPAC DDCUOPAC = new DDCUOPAC();
    DDRMST DDRMST = new DDRMST();
    CIRADR CIRADR = new CIRADR();
    CIRCNT CIRCNT = new CIRCNT();
    CIRCDM CIRCDM = new CIRCDM();
    DDRCCY DDRCCY = new DDRCCY();
    BPROCAC BPROCAC = new BPROCAC();
    SCCGWA SCCGWA;
    DDCSOPAC DDCSOPAC;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, DDCSOPAC DDCSOPAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSOPAC = DDCSOPAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZSOPAC return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        IBS.init(SCCGWA, DDCOOPAC);
        IBS.init(SCCGWA, DDCUOPAC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_DATA_VALIDITY();
        if (DDCSOPAC.BV_TYPE == '4') {
            B035_CARD_PROC();
        } else {
            B030_OPAC_PROC();
        }
        B110_TRANS_DATA_OUTPUT();
    }
    public void B010_CHECK_DATA_VALIDITY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSOPAC.CI_NO);
        CEP.TRC(SCCGWA, DDCSOPAC.PROD_CODE);
        CEP.TRC(SCCGWA, DDCSOPAC.AC_TYP);
        CEP.TRC(SCCGWA, DDCSOPAC.PSBK_FLG);
        CEP.TRC(SCCGWA, DDCSOPAC.CARD_FLG);
        CEP.TRC(SCCGWA, DDCSOPAC.PSBK_NO);
        CEP.TRC(SCCGWA, DDCSOPAC.CARD_NO);
        CEP.TRC(SCCGWA, DDCSOPAC.CARD_TYP);
        CEP.TRC(SCCGWA, DDCSOPAC.CARD_PRD_CODE);
        CEP.TRC(SCCGWA, DDCSOPAC.CARD_FIRST_SEQ);
        CEP.TRC(SCCGWA, DDCSOPAC.CARD_MTH);
        CEP.TRC(SCCGWA, DDCSOPAC.CARD_SEQ);
        CEP.TRC(SCCGWA, DDCSOPAC.CARD_MODE);
        CEP.TRC(SCCGWA, DDCSOPAC.CARD_PRN_CNM);
        CEP.TRC(SCCGWA, DDCSOPAC.DRAW_MTH);
        CEP.TRC(SCCGWA, DDCSOPAC.PSWD);
        CEP.TRC(SCCGWA, DDCSOPAC.REMARK);
        CEP.TRC(SCCGWA, DDCSOPAC.CUS_MGR);
        CEP.TRC(SCCGWA, DDCSOPAC.REG_CENT);
        CEP.TRC(SCCGWA, DDCSOPAC.SUB_BIZ);
        CEP.TRC(SCCGWA, DDCSOPAC.AC_TYPE);
        CEP.TRC(SCCGWA, DDCSOPAC.AGID_TYP);
        CEP.TRC(SCCGWA, DDCSOPAC.AGID_NO);
        CEP.TRC(SCCGWA, DDCSOPAC.AG_NM);
        CEP.TRC(SCCGWA, DDCSOPAC.POST_AC);
        CEP.TRC(SCCGWA, DDCSOPAC.CCY);
        CEP.TRC(SCCGWA, DDCSOPAC.BV_TYPE);
        B010_10_CHK_INPUT_DATA();
        B010_30_CHK_CI_INF();
    }
    public void B010_10_CHK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDCSOPAC.CI_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CI_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCSOPAC.PROD_CODE.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_NEW_PROD_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCSOPAC.PSBK_FLG == '1' 
            && DDCSOPAC.AC_TYP == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_TYP_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCSOPAC.PSBK_FLG == '1' 
            && (DDCSOPAC.AC_TYP != 'A' 
            && DDCSOPAC.AC_TYP != 'B')) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_TYP_ERR);
        }
        if (DDCSOPAC.PSBK_FLG == '1' 
            && DDCSOPAC.DRAW_MTH == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DRAW_MTH_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCSOPAC.DRAW_MTH != '1' 
            && DDCSOPAC.DRAW_MTH != '2' 
            && DDCSOPAC.DRAW_MTH != '3' 
            && DDCSOPAC.DRAW_MTH != '4' 
            && DDCSOPAC.DRAW_MTH != '5') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_DRAW_MTH_ERR);
        }
        if (DDCSOPAC.CCY.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_M_INPUT);
        }
        if (DDCSOPAC.CCY_TYPE == ' ') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_TYPE_M_INPUT);
        }
    }
    public void B030_OPAC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "OPEN AC");
        CEP.TRC(SCCGWA, DDCSOPAC.CI_NO);
        IBS.init(SCCGWA, DDCUOPAC);
        DDCUOPAC.CI_NO = DDCSOPAC.CI_NO;
        DDCUOPAC.PROD_CODE = DDCSOPAC.PROD_CODE;
        DDCUOPAC.AC_TYP = DDCSOPAC.AC_TYP;
        DDCUOPAC.CCY = DDCSOPAC.CCY;
        CEP.TRC(SCCGWA, DDCSOPAC.CCY);
        CEP.TRC(SCCGWA, DDCUOPAC.CCY);
        DDCUOPAC.CCY_TYPE = DDCSOPAC.CCY_TYPE;
        DDCUOPAC.AC_ATTR_LAB = DDCSOPAC.AC_ATTR_LAB;
        DDCUOPAC.PSBK_NO = DDCSOPAC.PSBK_NO;
        DDCUOPAC.PSBK_FLG = DDCSOPAC.PSBK_FLG;
        DDCUOPAC.CARD_FLG = DDCSOPAC.CARD_FLG;
        DDCUOPAC.DRAW_MTH = DDCSOPAC.DRAW_MTH;
        DDCUOPAC.PSWD = DDCSOPAC.PSWD;
        DDCUOPAC.JOINT_CARD_FLG = DDCSOPAC.JOINT_CARD_FLG;
        DDCUOPAC.SOCIAL_CARD_FLG = DDCSOPAC.SOCIAL_CARD_FLG;
        DDCUOPAC.FRG_TYPE = DDCSOPAC.FRG_TYPE;
        DDCUOPAC.FRG_CODE = DDCSOPAC.FRG_CODE;
        DDCUOPAC.FR_OP_NO = DDCSOPAC.FR_OP_NO;
        DDCUOPAC.CUS_MGR = DDCSOPAC.CUS_MGR;
        DDCUOPAC.REG_CENT = DDCSOPAC.REG_CENT;
        DDCUOPAC.SUB_BIZ = DDCSOPAC.SUB_BIZ;
        DDCUOPAC.AC_TYPE = DDCSOPAC.AC_TYPE;
        DDCUOPAC.POST_AC = DDCSOPAC.POST_AC;
        DDCUOPAC.INT_TYP = DDCSOPAC.INT_TYP;
        DDCUOPAC.ACNO_FLG = 'C';
        DDCUOPAC.DR_FLG = DDCSOPAC.DR_FLG;
        DDCUOPAC.AGID_TYP = DDCSOPAC.AGID_TYP;
        DDCUOPAC.AGID_NO = DDCSOPAC.AGID_NO;
        DDCUOPAC.AG_NM = DDCSOPAC.AG_NM;
        DDCUOPAC.REMARK = DDCSOPAC.REMARK;
        DDCUOPAC.BV_TYPE = DDCSOPAC.BV_TYPE;
        DDCUOPAC.YW_TYP = DDCSOPAC.YW_TYP;
        DDCUOPAC.TRT_CTLW = DDCSOPAC.TRT_CTLW;
        DDCUOPAC.LMT_CCY = DDCSOPAC.LMT_CCY;
        DDCUOPAC.LMT_BAL = DDCSOPAC.LMT_BAL;
        DDCUOPAC.AGID_CTY = DDCSOPAC.AGID_CTY;
        DDCUOPAC.AGID_CNO = DDCSOPAC.AGID_CNO;
        DDCUOPAC.AGID_CNM = DDCSOPAC.AGID_CNM;
        S000_CALL_DDZUOPAC();
        CEP.TRC(SCCGWA, DDCUOPAC.AC);
        DDCSOPAC.AC = DDCUOPAC.AC;
    }
    public void B010_30_CHK_CI_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = DDCSOPAC.CI_NO;
        CICCUST.FUNC = 'C';
        S000_CALL_CISOCUST();
    }
    public void B010_50_CHK_PRD_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSOPAC.CARD_PRD_CODE);
        CEP.TRC(SCCGWA, DDCSOPAC.PROD_CODE);
        IBS.init(SCCGWA, DDCIQPRD);
        IBS.init(SCCGWA, DDVMPRD);
        DDCIQPRD.INPUT_DATA.PROD_CODE = DDCSOPAC.PROD_CODE;
        CEP.TRC(SCCGWA, DDCSOPAC.PROD_CODE);
        CEP.TRC(SCCGWA, DDCIQPRD.INPUT_DATA.PROD_CODE);
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
        S000_CALL_DDZIQPRD();
        CEP.TRC(SCCGWA, DDVMPRD.VAL.CUST_TYPE);
        CEP.TRC(SCCGWA, DDCSOPAC.PSBK_NO);
    }
    public void B035_CARD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSOPAC.CMAD_TYP);
        if (DDCSOPAC.CMAD_TYP == '1') {
            B035_01_PREFABRICATE_PROC();
        } else {
            if (DDCSOPAC.CMAD_TYP == '2') {
                B035_02_CUSTOMIZE_PROC();
            }
        }
    }
    public void B035_01_PREFABRICATE_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "PREFABRICATE");
        CEP.TRC(SCCGWA, DDCSOPAC.CARD_PRD_CODE);
        CEP.TRC(SCCGWA, DDCSOPAC.CARD_NO);
        IBS.init(SCCGWA, DCCSSPOT);
        CEP.TRC(SCCGWA, DDCSOPAC.AC);
        DCCSSPOT.AC_NO = DDCSOPAC.AC;
        DCCSSPOT.CARD_PROD_CD = DDCSOPAC.CARD_PRD_CODE;
        DCCSSPOT.CARD_NO = DDCSOPAC.CARD_NO;
        DCCSSPOT.HOLDER_IDTYP = CICCUST.DATA.ID_TYPE;
        DCCSSPOT.HOLDER_CINO = DDCSOPAC.CI_NO;
        DCCSSPOT.CARD_AUTO_OPNAC = 'N';
        DCCSSPOT.SAME_NM_FLG = 'Y';
        DCCSSPOT.DIFF_NM_FLG = 'Y';
        DCCSSPOT.OVERSEA_FLG = 'Y';
        DCCSSPOT.CARD_LNK_TYP = '1';
        DCCSSPOT.PROD_LMT_FLG = 'Y';
        DCCSSPOT.DIR_TRSF_FLG = 'N';
        DCCSSPOT.AC_TYPE = DDCSOPAC.AC_TYPE;
        DCCSSPOT.CUS_MGR = DDCSOPAC.CUS_MGR;
        DCCSSPOT.REG_CENT = DDCSOPAC.REG_CENT;
        DCCSSPOT.SUB_BIZ = DDCSOPAC.SUB_BIZ;
        DCCSSPOT.DB_FREE = DDCSOPAC.DB_FREE;
        S000_CALL_DCZSSPOT();
        CEP.TRC(SCCGWA, DDCSOPAC.AC);
        CEP.TRC(SCCGWA, DCCSSPOT.AC_NO);
    }
    public void B035_02_CUSTOMIZE_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "CUSTOMIZE");
        IBS.init(SCCGWA, DCCSSPEC);
        CEP.TRC(SCCGWA, DDCUOPAC.AC);
        CEP.TRC(SCCGWA, DDCSOPAC.AC);
        DCCSSPOT.AC_NO = DDCSOPAC.AC;
        DCCSSPEC.CARD_PROD_CD = DDCSOPAC.CARD_PRD_CODE;
        DCCSSPEC.CARD_BIN = DDCSOPAC.CARD_BIN;
        DCCSSPEC.CARD_SEG1 = DDCSOPAC.CARD_FIRST_SEQ;
        DCCSSPEC.CARD_SEQ = DDCSOPAC.CARD_SEQ;
        CEP.TRC(SCCGWA, CICCUST.DATA.ID_TYPE);
        CEP.TRC(SCCGWA, CICCUST.DATA.ID_NO);
        DCCSSPEC.HOLDER_IDTYP = CICCUST.O_DATA.O_ID_TYPE;
        DCCSSPEC.HOLDER_IDNO = CICCUST.O_DATA.O_ID_NO;
        DCCSSPEC.HOLDER_CINO = DDCSOPAC.CI_NO;
        DCCSSPEC.HOLDER_NAME = CICCUST.O_DATA.O_CI_NM;
        DCCSSPEC.CARD_AUTO_OPNAC = 'N';
        DCCSSPEC.SNAME_TRAN_FLG = 'Y';
        DCCSSPEC.DNAME_TRAN_FLG = 'Y';
        DCCSSPEC.OUT_DRAW_FLG = 'Y';
        DCCSSPEC.CARD_LNK_TYP = '1';
        DCCSSPEC.PROD_LMT_FLG = 'Y';
        DCCSSPEC.HOLD_AC_FLG = 'N';
        DCCSSPEC.ISSUE_MTH = '0';
        DCCSSPEC.CARD_CLS_PROD = DDCSOPAC.CARD_CLS;
        DCCSSPEC.BV_CD_NO = DDCSOPAC.BV_CD_NO;
        DCCSSPEC.AC_TYPE = DDCSOPAC.AC_TYPE;
        DCCSSPEC.CUS_MGR = DDCSOPAC.CUS_MGR;
        DCCSSPEC.REG_CENT = DDCSOPAC.REG_CENT;
        DCCSSPEC.SUB_BIZ = DDCSOPAC.SUB_BIZ;
        DCCSSPEC.DB_FREE = DDCSOPAC.DB_FREE;
        DCCSSPEC.AC_TYP = DDCSOPAC.AC_TYP;
        DCCSSPEC.EMBOSS_NAME = DDCSOPAC.CARD_PRN_CNM;
        S000_CALL_DCZSSPEC();
        DDCSOPAC.CARD_NO = DCCSSPEC.CARD_NO;
        DDCSOPAC.PSWD = DCCSSPEC.CARD_PSW;
        CEP.TRC(SCCGWA, DDCSOPAC.CARD_NO);
        CEP.TRC(SCCGWA, DCCSSPEC.CARD_SEG1);
        CEP.TRC(SCCGWA, DDCSOPAC.CI_NO);
        CEP.TRC(SCCGWA, DDCSOPAC.AC);
        CEP.TRC(SCCGWA, DCCSSPEC.AC_NO);
    }
    public void B110_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOOPAC);
        DDCOOPAC.CI_NO = DDCSOPAC.CI_NO;
        DDCOOPAC.PROD_CODE = DDCSOPAC.PROD_CODE;
        DDCOOPAC.AC = DDCUOPAC.AC;
        DDCOOPAC.AC_CNM = DDCSOPAC.CI_CNM;
        DDCOOPAC.AC_ENM = DDCSOPAC.CI_ENM;
        DDCOOPAC.AC_TYP = DDCSOPAC.AC_TYP;
        DDCOOPAC.PSBK_FLG = DDCSOPAC.PSBK_FLG;
        DDCOOPAC.CARD_FLG = DDCSOPAC.CARD_FLG;
        CEP.TRC(SCCGWA, DDCSOPAC.PSBK_FLG);
        CEP.TRC(SCCGWA, DDCOOPAC.PSBK_FLG);
        CEP.TRC(SCCGWA, DDCSOPAC.CARD_FLG);
        CEP.TRC(SCCGWA, DDCOOPAC.CARD_FLG);
        CEP.TRC(SCCGWA, DDCSOPAC.PSBK_NO);
        DDCOOPAC.PSBK_NO = DDCSOPAC.PSBK_NO;
        DDCOOPAC.CARD_MADE_FLG = DDCSOPAC.CMAD_TYP;
        CEP.TRC(SCCGWA, DDCUOPAC.CARD_NO);
        if (DDCUOPAC.CARD_NO.trim().length() > 0) {
            DDCOOPAC.CARD_NO = DDCUOPAC.CARD_NO;
        } else {
            DDCOOPAC.CARD_NO = DDCSOPAC.CARD_NO;
        }
        CEP.TRC(SCCGWA, DDCOOPAC.CARD_NO);
        DDCOOPAC.CARD_SORT = DDCSOPAC.CARD_TYP;
        DDCOOPAC.COL_AC_FLG = DDCSOPAC.COLACFLG;
        DDCOOPAC.CARD_PRD_CODE = DDCSOPAC.CARD_PRD_CODE;
        DDCOOPAC.CARD_BIN = DDCSOPAC.CARD_BIN;
        DDCOOPAC.CARD_FIRST_SEQ = DDCSOPAC.CARD_FIRST_SEQ;
        DDCOOPAC.CARD_MTH = DDCSOPAC.CARD_MTH;
        DDCOOPAC.CARD_SEQ = DDCSOPAC.CARD_SEQ;
        DDCOOPAC.CARD_MODE = DDCSOPAC.CARD_MODE;
        DDCOOPAC.CARD_PRN_CNM = DDCSOPAC.CARD_PRN_CNM;
        DDCOOPAC.CARD_DUE = DDCSOPAC.CARD_DUE;
        DDCOOPAC.DRAW_MTH = DDCSOPAC.DRAW_MTH;
        DDCOOPAC.PSWD = " ";
        DDCOOPAC.REMARK = DDCSOPAC.REMARK;
        DDCOOPAC.CUS_MGR = DDCSOPAC.CUS_MGR;
        DDCOOPAC.REG_CENT = DDCSOPAC.REG_CENT;
        DDCOOPAC.SUB_BIZ = DDCSOPAC.SUB_BIZ;
        DDCOOPAC.POST_AC = DDCSOPAC.POST_AC;
        DDCOOPAC.YW_TYP = DDCSOPAC.YW_TYP;
        CEP.TRC(SCCGWA, DDCOOPAC.AC);
        CEP.TRC(SCCGWA, DDCSOPAC.AC);
        CEP.TRC(SCCGWA, DDCSOPAC.PSBK_NO);
        CEP.TRC(SCCGWA, DDCOOPAC.PSBK_NO);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT1;
        SCCFMT.DATA_PTR = DDCOOPAC;
        SCCFMT.DATA_LEN = 906;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_CHK_BR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = WS_BR;
        S000_CALL_BPZPQORG();
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
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
    public void S000_CALL_DCZSSPEC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-S-DCZSSPEC", DCCSSPEC);
    }
    public void S000_CALL_DCZSSPOT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-S-PREMAKE-CARD-OP", DCCSSPOT);
    }
    public void S000_CALL_CISOCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
    }
    public void S000_CALL_DDZIQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-DDPRD", DDCIQPRD);
        if (DDCIQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQPRD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZUOPAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-OPEN-AC", DDCUOPAC);
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