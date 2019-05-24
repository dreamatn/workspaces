package com.hisun.PN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class PNZSQUE {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "PN190";
    String WS_ERR_MSG = " ";
    int WS_ISS_DATE = 0;
    String WS_ENCRY_NO = " ";
    double WS_AMT = 0;
    char WS_BCC_FLAG = ' ';
    char WS_DFT_FLAG = ' ';
    char WS_GBCC_FLAG = ' ';
    char WS_DFPSW_FLAG = ' ';
    PNCMSG_ERROR_MSG PNCMSG_ERROR_MSG = new PNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    PNCOQUE PNCOQUE = new PNCOQUE();
    PNRBCC PNRBCC = new PNRBCC();
    PNRDFT PNRDFT = new PNRDFT();
    PNRGBCC PNRGBCC = new PNRGBCC();
    PNRDFPSW PNRDFPSW = new PNRDFPSW();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    PNCSQUE PNCSQUE;
    public void MP(SCCGWA SCCGWA, PNCSQUE PNCSQUE) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.PNCSQUE = PNCSQUE;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "PNZSQUE return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNCOQUE);
        IBS.init(SCCGWA, PNRBCC);
        IBS.init(SCCGWA, PNRDFT);
        IBS.init(SCCGWA, PNRGBCC);
        IBS.init(SCCGWA, PNRDFPSW);
        if (PNCSQUE.KND.equalsIgnoreCase("C00001")) {
            PNRBCC.KEY.BILL_KND = PNCSQUE.KND;
            PNRBCC.KEY.BILL_NO = PNCSQUE.CC_NO;
            T000_READ_PNTBCC();
            if (pgmRtn) return;
            if (WS_BCC_FLAG == 'N') {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_BCC_NOT_FND);
            }
            WS_ISS_DATE = PNRBCC.ISS_DT;
            WS_AMT = PNRBCC.AMT;
        } else {
            PNRDFT.KEY.BILL_KND = PNCSQUE.KND;
            PNRDFT.KEY.BILL_NO = PNCSQUE.CC_NO;
            T000_READ_PNTDFT();
            if (pgmRtn) return;
            if (WS_DFT_FLAG == 'N') {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_DFT_REC_NOT_EXIST);
            }
            WS_ISS_DATE = PNRDFT.ISS_DT;
            WS_AMT = PNRDFT.ISS_AMT;
        }
        PNRDFPSW.KEY.BILL_KND = PNCSQUE.KND;
        PNRDFPSW.KEY.BILL_NO = PNCSQUE.CC_NO;
        T000_READ_PNTDFPSW();
        if (pgmRtn) return;
        if (WS_DFPSW_FLAG == 'N') {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_PNTDFPSW_NOT_FND);
        }
        WS_ENCRY_NO = PNRDFPSW.ENCRY_NO;
        if (PNCSQUE.ISS_DATE != WS_ISS_DATE) {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_ISS_DATE_ERR);
        }
        if (PNCSQUE.AMT != WS_AMT) {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_ISS_AMT_ERR);
        }
        if (!PNCSQUE.ENCRY_NO.equalsIgnoreCase(WS_ENCRY_NO)) {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_ENCRY_NO_ERR);
        }
        PNRGBCC.KEY.BILL_KND = PNCSQUE.KND;
        PNRGBCC.KEY.BILL_NO = PNCSQUE.CC_NO;
        T000_READ_PNTGBCC();
        if (pgmRtn) return;
        if (WS_GBCC_FLAG == 'Y') {
            B020_01_DATA_OUTPUT();
            if (pgmRtn) return;
        }
    }
    public void B020_DATA_OUTPUT() throws IOException,SQLException,Exception {
        if (PNCSQUE.KND.equalsIgnoreCase("C00001")) {
            PNCOQUE.DATA.KND = PNRBCC.KEY.BILL_KND.charAt(0);
            PNCOQUE.DATA.CC_NO = PNRBCC.KEY.BILL_NO;
            PNCOQUE.DATA.TRN_FLG = PNRBCC.TRN_FLG;
            PNCOQUE.DATA.CCY = PNRBCC.CCY;
            PNCOQUE.DATA.AMT = PNRBCC.AMT;
            PNCOQUE.DATA.STS = PNRBCC.STS;
            PNCOQUE.DATA.PAY_TYPE = PNRBCC.PAY_TYPE;
            PNCOQUE.DATA.C_T_FLG = PNRBCC.C_T_FLG;
            if (PNRBCC.APB_TYPE == '1') {
                PNCOQUE.DATA.APB_TYPE = '0';
            } else {
                PNCOQUE.DATA.APB_TYPE = '1';
            }
            PNCOQUE.DATA.APP_AC = PNRBCC.APP_AC;
            PNCOQUE.DATA.APP_ACNM = PNRBCC.APP_ACNM;
            PNCOQUE.DATA.APB_NO = PNRBCC.APB_NO;
            PNCOQUE.DATA.APB_VALUE_DATE = PNRBCC.APB_VALUE_DATE;
            PNCOQUE.DATA.PAYEE_AC = PNRBCC.PAYEE_AC;
            PNCOQUE.DATA.PAYEE_ACNM = PNRBCC.PAYEE_ACNM;
            PNCOQUE.DATA.LHD_AC = PNRBCC.LHD_AC;
            PNCOQUE.DATA.LHD_ACNM = PNRBCC.LHD_ACNM;
            PNCOQUE.DATA.ODUE_FLG = PNRBCC.ODUE_FLG;
            PNCOQUE.DATA.PAY_BK = PNRBCC.PAY_BK;
            PNCOQUE.DATA.ISS_DT = PNRBCC.ISS_DT;
            PNCOQUE.DATA.ISS_BR = PNRBCC.ISS_BR;
            PNCOQUE.DATA.ISS_TLR = PNRBCC.ISS_TLR;
            PNCOQUE.DATA.CLR_DATE = PNRBCC.CLR_DATE;
            PNCOQUE.DATA.CLR_BR = PNRBCC.CLR_BR;
            PNCOQUE.DATA.CLR_TLR = PNRBCC.CLR_TLR;
            PNCOQUE.DATA.OLD_CCNO = PNRBCC.OLD_CCNO;
            PNCOQUE.DATA.CHG_DATE = PNRBCC.CHG_DATE;
            PNCOQUE.DATA.USG_RMK = PNRBCC.USG_RMK;
            PNCOQUE.DATA.ISS_FEEFLG = PNRBCC.FEE_FLG;
            PNCOQUE.DATA.STL_OPT = PNRBCC.STL_OPT;
        } else {
            PNCOQUE.DATA.KND = PNRDFT.KEY.BILL_KND.charAt(0);
            PNCOQUE.DATA.CC_NO = PNRDFT.KEY.BILL_NO;
            PNCOQUE.DATA.TRN_FLG = PNRDFT.TRN_FLG;
            PNCOQUE.DATA.CCY = PNRDFT.ISS_CCY;
            PNCOQUE.DATA.AMT = PNRDFT.ISS_AMT;
            PNCOQUE.DATA.STS = PNRDFT.STS;
            PNCOQUE.DATA.PAY_TYPE = PNRDFT.PAY_TYPE;
            PNCOQUE.DATA.C_T_FLG = PNRDFT.C_T_FLG;
            if (PNRDFT.APB_TYPE == '1') {
                PNCOQUE.DATA.APB_TYPE = '0';
            } else {
                PNCOQUE.DATA.APB_TYPE = '1';
            }
            PNCOQUE.DATA.APP_AC = PNRDFT.APP_AC;
            PNCOQUE.DATA.APP_ACNM = PNRDFT.APP_NAME;
            PNCOQUE.DATA.APB_NO = PNRDFT.APB_NO;
            PNCOQUE.DATA.APB_VALUE_DATE = PNRDFT.APB_VALUE_DATE;
            PNCOQUE.DATA.PAYEE_AC = PNRDFT.PAYEE_AC;
            PNCOQUE.DATA.PAYEE_ACNM = PNRDFT.PAYEE_NAME;
            PNCOQUE.DATA.LHD_AC = PNRDFT.LHD_AC;
            PNCOQUE.DATA.LHD_ACNM = PNRDFT.LHD_NAME;
            PNCOQUE.DATA.ODUE_FLG = PNRDFT.ODUE_FLG;
            PNCOQUE.DATA.STL_AMT = PNRDFT.STL_AMT;
            PNCOQUE.DATA.BAL_AMT = PNRDFT.BAL_AMT;
            PNCOQUE.DATA.CR_AC = PNRDFT.CR_AC;
            PNCOQUE.DATA.AGT_BK_NO = PNRDFT.AGT_BK_NO;
            PNCOQUE.DATA.AGT_BK_NAME = PNRDFT.AGT_BK_NAME;
            PNCOQUE.DATA.PAY_BK = PNRDFT.PAY_BK;
            PNCOQUE.DATA.ISS_DT = PNRDFT.ISS_DT;
            PNCOQUE.DATA.ISS_BR = PNRDFT.ISS_BR;
            PNCOQUE.DATA.ISS_TLR = PNRDFT.ISS_TLR;
            PNCOQUE.DATA.CLR_DATE = PNRDFT.CLR_DATE;
            PNCOQUE.DATA.CLR_BR = PNRDFT.CLR_BR;
            PNCOQUE.DATA.CLR_TLR = PNRDFT.CLR_TLR;
            PNCOQUE.DATA.OLD_CCNO = PNRDFT.OLD_DFNO;
            PNCOQUE.DATA.CHG_DATE = PNRDFT.CHG_DATE;
            PNCOQUE.DATA.USG_RMK = PNRDFT.USG_RMK;
            PNCOQUE.DATA.ISS_FEEFLG = PNRDFT.FEE_FLG;
            PNCOQUE.DATA.STL_OPT = PNRDFT.STL_OPT;
        }
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = PNCOQUE;
        SCCFMT.DATA_LEN = 3089;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, "AFTER-OUTPUT");
        CEP.TRC(SCCGWA, PNCOQUE);
