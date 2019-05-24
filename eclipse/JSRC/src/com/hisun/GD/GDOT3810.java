package com.hisun.GD;

import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class GDOT3810 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    GDCSSDDR GDCSSDDR = new GDCSSDDR();
    CICCUST CICCUST = new CICCUST();
    SCCGWA SCCGWA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    GDB3810_AWA_3810 GDB3810_AWA_3810;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "GDOT3810 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "GDB3810_AWA_3810>");
        GDB3810_AWA_3810 = (GDB3810_AWA_3810) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDB3810_AWA_3810.CUID_TYP);
        CEP.TRC(SCCGWA, GDB3810_AWA_3810.CUID_NO);
        CEP.TRC(SCCGWA, GDB3810_AWA_3810.CUCI_NO);
        CEP.TRC(SCCGWA, GDB3810_AWA_3810.CU_OPT);
        CEP.TRC(SCCGWA, GDB3810_AWA_3810.CI_ENM);
        CEP.TRC(SCCGWA, GDB3810_AWA_3810.CI_CNM);
        CEP.TRC(SCCGWA, GDB3810_AWA_3810.CUPRD_CD);
        CEP.TRC(SCCGWA, GDB3810_AWA_3810.CU_CCY);
        CEP.TRC(SCCGWA, GDB3810_AWA_3810.CCY_TYPE);
        CEP.TRC(SCCGWA, GDB3810_AWA_3810.CU_RATE);
        CEP.TRC(SCCGWA, GDB3810_AWA_3810.CUPCT_S);
        CEP.TRC(SCCGWA, GDB3810_AWA_3810.CUF_TYP);
        CEP.TRC(SCCGWA, GDB3810_AWA_3810.FRG_IND);
        CEP.TRC(SCCGWA, GDB3810_AWA_3810.SPDR_AC);
        CEP.TRC(SCCGWA, GDB3810_AWA_3810.SP_AMT);
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDB3810_AWA_3810);
        CEP.TRC(SCCGWA, GDB3810_AWA_3810.CUCI_NO);
        CEP.TRC(SCCGWA, GDB3810_AWA_3810.CUPRD_CD);
        CEP.TRC(SCCGWA, GDB3810_AWA_3810.CU_CCY);
        CEP.TRC(SCCGWA, GDB3810_AWA_3810.CUF_TYP);
        if (GDB3810_AWA_3810.CUCI_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CINO_IDNO_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (GDB3810_AWA_3810.CUPRD_CD.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PROD_CD_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (GDB3810_AWA_3810.CU_CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (GDB3810_AWA_3810.RES_AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (GDB3810_AWA_3810.CUT_AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (GDB3810_AWA_3810.SPDR_AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
        }
        if (GDB3810_AWA_3810.SP_AMT == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_DRAWAMT_MST_IPT;
            CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
        }
        if (GDB3810_AWA_3810.CUF_TYP == ' ') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CUF_TYP_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
        if (GDB3810_AWA_3810.FRG_IND.trim().length() == 0) {
            GDB3810_AWA_3810.FRG_IND = "OTH";
        }
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        IBS.init(SCCGWA, GDCSSDDR);
        CICCUST.DATA.CI_NO = GDB3810_AWA_3810.CUCI_NO;
        CICCUST.FUNC = 'C';
        S000_CALL_CIZCUST();
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NO);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_TYP);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_OWNER_BK);
        if (CICCUST.O_DATA.O_CI_TYP != '1' 
            && !GDB3810_AWA_3810.CU_CCY.equalsIgnoreCase(BPCRBANK.LOC_CCY1)) {
            if (GDB3810_AWA_3810.FRG_IND.equalsIgnoreCase("OSA")) {
                GDCSSDDR.FRG_CODE = "3300";
            } else {
                GDCSSDDR.FRG_CODE = "4200";
            }
        }
        CEP.TRC(SCCGWA, GDCSSDDR.FRG_CODE);
        GDCSSDDR.FRG_IND = GDB3810_AWA_3810.FRG_IND;
        GDCSSDDR.CI_NO = GDB3810_AWA_3810.CUCI_NO;
        GDCSSDDR.PROD_CODE = GDB3810_AWA_3810.CUPRD_CD;
        GDCSSDDR.AC_CNM = GDB3810_AWA_3810.CI_CNM;
        GDCSSDDR.AC_ENM = GDB3810_AWA_3810.CI_ENM;
        GDCSSDDR.AC_CCY = GDB3810_AWA_3810.CU_CCY;
        GDCSSDDR.CCY_TYPE = GDB3810_AWA_3810.CCY_TYPE;
        GDCSSDDR.AC_TYP = 'N';
        GDCSSDDR.FRG_CODE = GDB3810_AWA_3810.FRG_CODE;
        GDCSSDDR.FR_OP_NO = GDB3810_AWA_3810.FR_OP_NO;
        GDCSSDDR.FRG_IND = GDB3810_AWA_3810.FRG_IND;
        GDCSSDDR.PAY_MTH = GDB3810_AWA_3810.DRAW_MTH;
        GDCSSDDR.FEE_METH = '1';
        GDCSSDDR.CR_CR_FL = GDB3810_AWA_3810.CUDR_FLG;
        GDCSSDDR.CR_DR_FL = GDB3810_AWA_3810.CUCR_FLG;
        GDCSSDDR.RES_AC = GDB3810_AWA_3810.RES_AC;
        GDCSSDDR.CUT_AC = GDB3810_AWA_3810.CUT_AC;
        GDCSSDDR.OIC_NO = GDB3810_AWA_3810.OIC_NO;
        GDCSSDDR.RES_CENT = GDB3810_AWA_3810.RES_CENT;
        GDCSSDDR.SUB_DP = GDB3810_AWA_3810.SUB_DP;
        GDCSSDDR.SPDR_AC = GDB3810_AWA_3810.SPDR_AC;
        GDCSSDDR.SP_AMT = GDB3810_AWA_3810.SP_AMT;
        GDCSSDDR.STLT = '3';
        GDCSSDDR.YW_TYP = GDB3810_AWA_3810.BUS_KNB;
        CEP.TRC(SCCGWA, GDB3810_AWA_3810.DRAW_MTH);
        CEP.TRC(SCCGWA, GDCSSDDR.PAY_MTH);
        S000_CALL_GDZSSDDR();
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CISOCUST", CICCUST);
    }
    if (CICCUST.RC.RC_CODE != 0) {
        WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
        S000_ERR_MSG_PROC();
    }
    public void S000_CALL_GDZSSDDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SVR-GDZSSDDR", GDCSSDDR);
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
