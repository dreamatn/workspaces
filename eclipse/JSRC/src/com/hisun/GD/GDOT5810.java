package com.hisun.GD;

import com.hisun.DD.*;
import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class GDOT5810 {
    int JIBS_tmp_int;
    String JIBS_NumStr;
    DBParm DDTCCY_RD;
    short WS_FLD_NO = 0;
    char WS_CI_TYP_T = ' ';
    String WS_FRG_IND_T = " ";
    String WS_ERR_MSG = " ";
    String WS_RES_AC = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSOCAC DDCSOCAC = new DDCSOCAC();
    GDCRSTAC GDCRSTAC = new GDCRSTAC();
    CICCUST CICCUST = new CICCUST();
    GDRSTAC GDRSTAC = new GDRSTAC();
    CICQACRI CICQACRI = new CICQACRI();
    CICACCU CICACCU = new CICACCU();
    DDCSOPAC DDCSOPAC = new DDCSOPAC();
    DDCSMTDF DDCSMTDF = new DDCSMTDF();
    DDRCCY DDRCCY = new DDRCCY();
    DDRMST DDRMST = new DDRMST();
    DDCRMST DDCRMST = new DDCRMST();
    CICSOEC CICSOEC = new CICSOEC();
    CICQACRL CICQACRL = new CICQACRL();
    BPCPQORG BPCPQORG = new BPCPQORG();
    DCCUCINF DCCUCINF = new DCCUCINF();
    SCCGWA SCCGWA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    GDB5810_AWA_5810 GDB5810_AWA_5810;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "GDOT5810 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "GDB5810_AWA_5810>");
        GDB5810_AWA_5810 = (GDB5810_AWA_5810) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.CUID_TYP);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.CUID_NO);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.CUCI_NO);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.CU_OPT);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.CUCI_ENM);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.CUCI_CNM);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.CUPRD_CD);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.CU_CCY);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.CCY_TYPE);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.CU_RATE);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.CUPCT_S);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.CUF_TYP);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.OPAC_AC);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.AC_TYP);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.AC_CCY);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.SPC_KIND);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.EXP_DATE);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.BASE_AC);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.FRG_CODE);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.FR_OP_NO);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.FRG_IND);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.SPEC_IND);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.AC_PURP);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.PAY_MTH);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.FEE_METH);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.CR_CR_FL);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.ALLO_FLG);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.BUS_KNB);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.DRAW_MTH);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.SP_PSW);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.ACDR_OPT);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.CUDR_FLG);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.CUCR_FLG);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.CURAT_MT);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.CURAT_TY);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.CU_RATE);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.CUPCT_S);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.CUINT_RA);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.CUF_TYP);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.FRG_IND);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.FRG_CODE);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.FR_OP_NO);
        if (GDB5810_AWA_5810.RES_AC == null) GDB5810_AWA_5810.RES_AC = "";
        JIBS_tmp_int = GDB5810_AWA_5810.RES_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) GDB5810_AWA_5810.RES_AC += " ";
        if (GDB5810_AWA_5810.RES_AC.substring(0, 2).trim().length() == 0) {
            CEP.TRC(SCCGWA, "SPACE------------");
        }
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.RES_AC);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.CUID_TYP);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.CUID_NO);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.CUCI_NO);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.CU_OPT);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.CUCI_ENM);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.CUCI_CNM);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.CUPRD_CD);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.CU_CCY);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.CCY_TYPE);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.CU_RATE);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.CUPCT_S);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.CUF_TYP);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.FRG_IND);
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDB5810_AWA_5810);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.CUCI_NO);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.CUPRD_CD);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.CU_CCY);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.RES_AC);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.CUT_AC);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.CUF_TYP);
        if (GDB5810_AWA_5810.CUCI_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CINO_IDNO_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (GDB5810_AWA_5810.CUPRD_CD.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PROD_CD_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (GDB5810_AWA_5810.CU_CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (GDB5810_AWA_5810.CUF_TYP == ' ') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CUF_TYP_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
        if (GDB5810_AWA_5810.FRG_IND.trim().length() == 0) {
            GDB5810_AWA_5810.FRG_IND = "OTH";
        }
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = GDB5810_AWA_5810.CUCI_NO;
        CICCUST.FUNC = 'C';
        S000_CALL_CIZCUST();
        WS_CI_TYP_T = CICCUST.O_DATA.O_CI_TYP;
        if (CICCUST.O_DATA.O_SID_FLG == '2') {
            WS_FRG_IND_T = "NRA";
        } else {
            WS_FRG_IND_T = GDB5810_AWA_5810.FRG_IND;
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        CEP.TRC(SCCGWA, BPCPQORG.TRA_TYP);
        CEP.TRC(SCCGWA, WS_CI_TYP_T);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_SID_FLG);
        if (!BPCPQORG.TRA_TYP.equalsIgnoreCase("00")) {
            if (WS_CI_TYP_T == '1') {
                if (CICCUST.O_DATA.O_SID_FLG == '1') {
                    WS_FRG_IND_T = "FTI";
                }
                if (CICCUST.O_DATA.O_SID_FLG == '2') {
                    WS_FRG_IND_T = "FTF";
                }
            }
            if (WS_CI_TYP_T == '2') {
                if (CICCUST.O_DATA.O_SID_FLG == '1') {
                    WS_FRG_IND_T = "FTE";
                }
                if (CICCUST.O_DATA.O_SID_FLG == '2') {
                    WS_FRG_IND_T = "FTN";
                }
            }
            if (WS_CI_TYP_T == '3') {
                WS_FRG_IND_T = "FTU";
            }
        }
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CI_TYP_T);
        if (WS_CI_TYP_T == '1') {
            R000_PERSONAL_PROC();
        } else {
            R000_COMPANY_PROC();
        }
    }
    public void R000_COMPANY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSOCAC);
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = GDB5810_AWA_5810.CUCI_NO;
        CICCUST.FUNC = 'C';
        S000_CALL_CIZCUST();
        CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_TYP);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_OWNER_BK);
        if (CICCUST.O_DATA.O_CI_TYP != '1' 
            && !GDB5810_AWA_5810.CU_CCY.equalsIgnoreCase(BPCRBANK.LOC_CCY1)) {
            if (GDB5810_AWA_5810.FRG_IND.equalsIgnoreCase("OSA")) {
                DDCSOCAC.FRG_CODE = "3300";
            } else {
                DDCSOCAC.FRG_CODE = "4200";
            }
        }
        CEP.TRC(SCCGWA, DDCSOCAC.FRG_CODE);
        DDCSOCAC.FRG_IND = GDB5810_AWA_5810.FRG_IND;
        DDCSOCAC.CI_NO = GDB5810_AWA_5810.CUCI_NO;
        DDCSOCAC.PROD_CODE = GDB5810_AWA_5810.CUPRD_CD;
        DDCSOCAC.AC_CNM = GDB5810_AWA_5810.CUCI_CNM;
        DDCSOCAC.AC_ENM = GDB5810_AWA_5810.CUCI_ENM;
        DDCSOCAC.AC_CCY = GDB5810_AWA_5810.CU_CCY;
        DDCSOCAC.CCY_TYPE = GDB5810_AWA_5810.CCY_TYPE;
        DDCSOCAC.AC_TYP = 'N';
        DDCSOCAC.FR_OP_NO = GDB5810_AWA_5810.FR_OP_NO;
        DDCSOCAC.FRG_IND = WS_FRG_IND_T;
        DDCSOCAC.AC_PURP = "" + GDB5810_AWA_5810.AC_PURP;
        JIBS_tmp_int = DDCSOCAC.AC_PURP.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) DDCSOCAC.AC_PURP = "0" + DDCSOCAC.AC_PURP;
        DDCSOCAC.CR_CR_FL = GDB5810_AWA_5810.CUDR_FLG;
        DDCSOCAC.CR_DR_FL = GDB5810_AWA_5810.CUCR_FLG;
        DDCSOCAC.OIC_NO = GDB5810_AWA_5810.OIC_NO;
        DDCSOCAC.OIC_CODE = GDB5810_AWA_5810.RES_CENT;
        DDCSOCAC.SUB_DP = GDB5810_AWA_5810.SUB_DP;
        DDCSOCAC.PAY_MTH = '5';
        DDCSOCAC.YW_TYP = GDB5810_AWA_5810.BUS_KNB;
        JIBS_NumStr = "" + 0;
        DDCSOCAC.LMT_TYP = JIBS_NumStr.charAt(0);
        S000_CALL_DDZSOCAC();
        R000_WRITE_GDTSTAC();
        if (GDB5810_AWA_5810.CURAT_TY != '0') {
            B030_INT_CHANGE_PROC();
        }
    }
    public void R000_PERSONAL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSOPAC);
        DDCSOPAC.FRG_TYPE = GDB5810_AWA_5810.FRG_IND;
        DDCSOPAC.CI_NO = GDB5810_AWA_5810.CUCI_NO;
        DDCSOPAC.PROD_CODE = GDB5810_AWA_5810.CUPRD_CD;
        DDCSOPAC.CI_CNM = GDB5810_AWA_5810.CUCI_CNM;
        DDCSOPAC.CI_ENM = GDB5810_AWA_5810.CUCI_ENM;
        DDCSOPAC.CCY = GDB5810_AWA_5810.CU_CCY;
        DDCSOPAC.CCY_TYPE = GDB5810_AWA_5810.CCY_TYPE;
        DDCSOCAC.FRG_IND = WS_FRG_IND_T;
        DDCSOPAC.AC_TYP = 'N';
        DDCSOPAC.FR_OP_NO = GDB5810_AWA_5810.FR_OP_NO;
        DDCSOPAC.CUS_MGR = GDB5810_AWA_5810.OIC_NO;
        DDCSOPAC.REG_CENT = GDB5810_AWA_5810.RES_CENT;
        DDCSOPAC.SUB_BIZ = GDB5810_AWA_5810.SUB_DP;
        DDCSOPAC.YW_TYP = GDB5810_AWA_5810.BUS_KNB;
        DDCSOPAC.DRAW_MTH = '5';
        S000_CALL_DDZSOPAC();
        R000_WRITE_GDTSTAC();
    }
    public void R000_WRITE_GDTSTAC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.RES_AC);
        CEP.TRC(SCCGWA, GDB5810_AWA_5810.CUT_AC);
        IBS.init(SCCGWA, GDCRSTAC);
        IBS.init(SCCGWA, GDRSTAC);
        IBS.init(SCCGWA, CICQACRI);
        IBS.init(SCCGWA, CICACCU);
        GDCRSTAC.FUNC = 'A';
        if (DDCSOCAC.AC.trim().length() > 0) {
            GDRSTAC.KEY.AC = DDCSOCAC.AC;
        }
        if (DDCSOPAC.AC.trim().length() > 0) {
            GDRSTAC.KEY.AC = DDCSOPAC.AC;
        }
        if (GDB5810_AWA_5810.RES_AC.trim().length() > 0) {
            CICACCU.DATA.AGR_NO = GDB5810_AWA_5810.RES_AC;
            S000_CALL_CIZACCU();
            CEP.TRC(SCCGWA, GDB5810_AWA_5810.CUCI_NO);
            CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
            if (!GDB5810_AWA_5810.CUCI_NO.equalsIgnoreCase(CICACCU.DATA.CI_NO)) {
                IBS.init(SCCGWA, CICSOEC);
                CICSOEC.DATA.CI_NO = CICACCU.DATA.CI_NO;
                CICSOEC.DATA.READ_ONLY_FLG = 'Y';
                S000_CALL_CIZSOEC();
                CEP.TRC(SCCGWA, CICSOEC.DATA.SPECIAL_CI_NO);
                CEP.TRC(SCCGWA, GDB5810_AWA_5810.CUCI_NO);
                if (!CICSOEC.DATA.SPECIAL_CI_NO.equalsIgnoreCase(GDB5810_AWA_5810.CUCI_NO)) {
                    WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CI_NO_NOT_SAME;
                    S000_ERR_MSG_PROC();
                }
            }
            GDRSTAC.ST_AC = CICACCU.DATA.AGR_NO;
            CICQACRI.FUNC = 'A';
            CICQACRI.DATA.AGR_NO = GDB5810_AWA_5810.RES_AC;
            S000_CALL_CIZQACRI();
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CNTRCT_TYP);
            if (CICQACRI.O_DATA.O_CNTRCT_TYP.equalsIgnoreCase("033")) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_IPT_AC_INVAILD;
                S000_ERR_MSG_PROC();
            }
            IBS.init(SCCGWA, CICQACRL);
            CICQACRL.FUNC = 'I';
            CICQACRL.DATA.AC_NO = GDB5810_AWA_5810.RES_AC;
            S000_CALL_CIZQACRL();
            CEP.TRC(SCCGWA, CICQACRL.RC.RC_CODE);
            CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_AC_REL);
            CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
            if ((CICQACRL.RC.RC_CODE == 0 
                    && (CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("01") 
                    || CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("03") 
                    || CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("02") 
                    || CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("04") 
                    || CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("09") 
                    || CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("13")))) {
                WS_RES_AC = CICQACRL.O_DATA.O_REL_AC_NO;
            } else {
                WS_RES_AC = GDB5810_AWA_5810.RES_AC;
            }
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.FUNC = 'A';
            CICQACRI.DATA.AGR_NO = WS_RES_AC;
            S000_CALL_CIZQACRI();
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
                IBS.init(SCCGWA, DDRCCY);
                DDRCCY.CUS_AC = WS_RES_AC;
                DDRCCY.CCY = GDB5810_AWA_5810.CU_CCY;
                DDRCCY.CCY_TYPE = GDB5810_AWA_5810.CCY_TYPE;
                T000_READ_DDTCCY();
                IBS.init(SCCGWA, DDCRMST);
                IBS.init(SCCGWA, DDRMST);
                DDCRMST.FUNC = 'I';
                DDRMST.KEY.CUS_AC = GDB5810_AWA_5810.RES_AC;
                DDCRMST.REC_PTR = DDRMST;
                DDCRMST.REC_LEN = 425;
                S000_CALL_DDZRMST();
                if (DDRMST.AC_TYPE == 'N') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_TYP_ERR;
                    CEP.ERR(SCCGWA, WS_ERR_MSG);
                }
                if (DDRMST.AC_STS == 'C') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TO_AC_CLOSE;
                    CEP.ERR(SCCGWA, WS_ERR_MSG);
                }
                if (DDRMST.AC_STS == 'M') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_CLOSED_AND_GET_INT;
                    CEP.ERR(SCCGWA, WS_ERR_MSG);
                }
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                if (DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
                    || DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_OFFICE_FORBID;
                    CEP.ERR(SCCGWA, WS_ERR_MSG);
                }
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                if (DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1") 
                    || DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_FREEZE_CNOT_TR;
                    CEP.ERR(SCCGWA, WS_ERR_MSG);
                }
                if (DDRMST.AC_STS == 'D') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_IS_LONG_HOVER;
                    CEP.ERR(SCCGWA, WS_ERR_MSG);
                }
                if (DDRMST.AC_STS == 'V') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NOT_CHECKED;
                    CEP.ERR(SCCGWA, WS_ERR_MSG);
                }
                if (DDRMST.AC_STS == 'O') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NOT_ACTIVE;
                    CEP.ERR(SCCGWA, WS_ERR_MSG);
                }
            } else {
                IBS.init(SCCGWA, DDRCCY);
                DDRCCY.CUS_AC = WS_RES_AC;
                DDRCCY.CCY = GDB5810_AWA_5810.CU_CCY;
                DDRCCY.CCY_TYPE = GDB5810_AWA_5810.CCY_TYPE;
                T000_READ_DDTCCY();
                IBS.init(SCCGWA, DCCUCINF);
                DCCUCINF.CARD_NO = WS_RES_AC;
                S000_CALL_DCZUCINF();
                CEP.TRC(SCCGWA, DCCUCINF.CARD_STS);
                CEP.TRC(SCCGWA, DCCUCINF.CARD_STSW);
                if (DCCUCINF.CARD_STS == 'C') {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_CANCEL_STS;
                    CEP.ERR(SCCGWA, WS_ERR_MSG);
                }
                if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
                JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
                if (DCCUCINF.CARD_STSW.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALR_CARD_FORB_STS;
                    CEP.ERR(SCCGWA, WS_ERR_MSG);
                }
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                if (DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
                    || DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_OFFICE_FORBID;
                    CEP.ERR(SCCGWA, WS_ERR_MSG);
                }
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                if (DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1") 
                    || DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_FREEZE_CNOT_TR;
                    CEP.ERR(SCCGWA, WS_ERR_MSG);
                }
            }
            if (GDB5810_AWA_5810.CUT_AC.trim().length() > 0) {
                IBS.init(SCCGWA, CICACCU);
                CICACCU.DATA.AGR_NO = WS_RES_AC;
                S000_CALL_CIZACCU();
                CEP.TRC(SCCGWA, GDB5810_AWA_5810.CUCI_NO);
                CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
                if (!GDB5810_AWA_5810.CUCI_NO.equalsIgnoreCase(CICACCU.DATA.CI_NO)) {
                    IBS.init(SCCGWA, CICSOEC);
                    CICSOEC.DATA.CI_NO = CICACCU.DATA.CI_NO;
                    CICSOEC.DATA.READ_ONLY_FLG = 'Y';
                    S000_CALL_CIZSOEC();
                    CEP.TRC(SCCGWA, CICSOEC.DATA.SPECIAL_CI_NO);
                    CEP.TRC(SCCGWA, GDB5810_AWA_5810.CUCI_NO);
                    if (!CICSOEC.DATA.SPECIAL_CI_NO.equalsIgnoreCase(GDB5810_AWA_5810.CUCI_NO)) {
                        WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CI_NO_NOT_SAME;
                        S000_ERR_MSG_PROC();
                    }
                }
                GDRSTAC.INT_AC = CICACCU.DATA.AGR_NO;
                IBS.init(SCCGWA, CICQACRI);
                CICQACRI.FUNC = 'A';
                CICQACRI.DATA.AGR_NO = GDB5810_AWA_5810.CUT_AC;
                S000_CALL_CIZQACRI();
                CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
                if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("TD")) {
                    WS_ERR_MSG = GDCMSG_ERROR_MSG.NOT_DD_AC_TYPE;
                    S000_ERR_MSG_PROC();
                }
                if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
                    IBS.init(SCCGWA, DDCRMST);
                    IBS.init(SCCGWA, DDRMST);
                    DDCRMST.FUNC = 'I';
                    DDRMST.KEY.CUS_AC = GDB5810_AWA_5810.CUT_AC;
                    DDCRMST.REC_PTR = DDRMST;
                    DDCRMST.REC_LEN = 425;
                    S000_CALL_DDZRMST();
                    if (DDRMST.AC_TYPE == 'N') {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_TYP_ERR;
                        CEP.ERR(SCCGWA, WS_ERR_MSG);
                    }
                    if (DDRMST.AC_STS == 'C') {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TO_AC_CLOSE;
                        CEP.ERR(SCCGWA, WS_ERR_MSG);
                    }
                    if (DDRMST.AC_STS == 'D') {
                        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_IS_LONG_HOVER;
                        CEP.ERR(SCCGWA, WS_ERR_MSG);
                    }
                } else {
                    IBS.init(SCCGWA, DCCUCINF);
                    DCCUCINF.CARD_NO = WS_RES_AC;
                    S000_CALL_DCZUCINF();
                    CEP.TRC(SCCGWA, DCCUCINF.CARD_STS);
                    CEP.TRC(SCCGWA, DCCUCINF.CARD_STSW);
                    if (DCCUCINF.CARD_STS == 'C') {
                        WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_CANCEL_STS;
                        CEP.ERR(SCCGWA, WS_ERR_MSG);
                    }
                    if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
                    JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
                    for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
                    if (DCCUCINF.CARD_STSW.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
                        WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALR_CARD_FORB_STS;
                        CEP.ERR(SCCGWA, WS_ERR_MSG);
                    }
                }
            }
            CICQACRI.FUNC = 'A';
            CICQACRI.DATA.AGR_NO = GDB5810_AWA_5810.RES_AC;
            S000_CALL_CIZQACRI();
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CNTRCT_TYP);
            if (CICQACRI.O_DATA.O_CNTRCT_TYP.equalsIgnoreCase("033")) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_IPT_AC_INVAILD;
                S000_ERR_MSG_PROC();
            }
            GDRSTAC.RELAT_STS = 'N';
            GDRSTAC.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            GDRSTAC.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            GDCRSTAC.REC_PTR = GDRSTAC;
            GDCRSTAC.REC_LEN = 401;
            S000_CALL_GDZRSTAC();
        }
    }
    public void B030_INT_CHANGE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSMTDF);
        DDCSMTDF.OPT = '5';
        DDCSMTDF.AC = DDCSOCAC.AC;
        DDCSMTDF.CCY = GDB5810_AWA_5810.CU_CCY;
        DDCSMTDF.FLG = GDB5810_AWA_5810.CCY_TYPE;
        DDCSMTDF.CI_CNM = GDB5810_AWA_5810.CUCI_CNM;
        DDCSMTDF.CI_ENM = GDB5810_AWA_5810.CUCI_ENM;
        DDCSMTDF.TIR_DATA.TIR_INF[1-1].TIR_AMT1 = 99999999999999.99;
        CEP.TRC(SCCGWA, DDCSMTDF.TIR_DATA.TIR_INF[1-1].TIR_AMT1);
        DDCSMTDF.TIR_DATA.TIR_INF[1-1].TIR_SPR1 = GDB5810_AWA_5810.CU_RATE;
        CEP.TRC(SCCGWA, DDCSMTDF.TIR_DATA.TIR_INF[1-1].TIR_SPR1);
        DDCSMTDF.TIR_DATA.TIR_INF[1-1].TIR_SPR_PCT1 = GDB5810_AWA_5810.CUPCT_S;
        CEP.TRC(SCCGWA, DDCSMTDF.TIR_DATA.TIR_INF[1-1].TIR_SPR_PCT1);
        DDCSMTDF.TIR_DATA.TIR_INF[1-1].TIR_FIX_RATE1 = GDB5810_AWA_5810.CUINT_RA;
        CEP.TRC(SCCGWA, DDCSMTDF.TIR_DATA.TIR_INF[1-1].TIR_FIX_RATE1);
        S000_CALL_DDZSMTDF();
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CISOCUST", CICCUST);
    }
    if (CICCUST.RC.RC_CODE != 0) {
        WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
        S000_ERR_MSG_PROC();
    }
    public void S000_CALL_DDZSOCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-OPEN-ENT-AC", DDCSOCAC);
    }
    public void S000_CALL_DDZSOPAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-OPEN-AC", DDCSOPAC);
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_GDZRSTAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SRC-GDZRSTAC", GDCRSTAC);
        CEP.TRC(SCCGWA, GDCRSTAC.RC);
        if (GDCRSTAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, GDCRSTAC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC);
        if (CICQACRI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        if (CICACCU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, CICACCU.RC.RC_CODE);
        CEP.TRC(SCCGWA, CICACCU.DATA.AGR_NO);
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZSMTDF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-Z-DDZSMTDF", DDCSMTDF);
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.eqWhere = "CUS_AC,CCY,CCY_TYPE";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZSOEC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-OPEN-EXP-CI", CICSOEC);
        if (CICSOEC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSOEC.RC);
        }
    }
    public void S000_CALL_DDZRMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-R-DDTMST", DDCRMST);
        if (DDCRMST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCRMST.RC);
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, DDCRMST.RETURN_INFO);
        CEP.TRC(SCCGWA, DDCRMST.RC);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
