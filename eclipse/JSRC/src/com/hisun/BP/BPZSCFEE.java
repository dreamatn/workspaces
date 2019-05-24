package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.CI.CICACCU;
import com.hisun.CI.CICQACRI;
import com.hisun.DC.DCCPACTY;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class BPZSCFEE {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BP077";
    String CPN_BANK_MAINTAIN = "BP-S-BANK-MAINTAIN  ";
    String CPN_F_F_CAL_FEE = "BP-F-F-CAL-FEE      ";
    String CPN_F_F_TX_INFO = "BP-F-F-TX-INFO      ";
    String CPN_F_F_RM_TX = "BP-RM-CREATE-RECORD ";
    String CPN_F_F_CON_CHG_INFO = "BP-F-F-CON-CHG-INFO ";
    String CPN_R_FEE_PARM_MAIN = "BP-F-F-MAINTAIN-PARM";
    String CPN_R_FEE_CAL_FEE = "BP-F-F-CAL-STD-FEE  ";
    String CPN_R_FEE_CAL_IFAV = "BP-F-F-CAL-IFAV     ";
    String CPN_F_COM_FEE_INFO = "BP-F-T-FEE-INFO     ";
    String CPN_F_CAL_FEE = "BP-F-F-CAL-FEE      ";
    String CPN_S_AMO_CHECK = "BP-P-AMO-CHK        ";
    String K_PARM_TYPE_FBAS = "FBAS ";
    String K_PARM_TYPE_FMSK = "FMSK ";
    String CPN_EXG_PROC = "BP-EX";
    char K_LOCAL_TYPE = '0';
    String K_LOCAL_CCY = "156";
    String K_ABROAD_CCY = "840";
    double K_MAX_AMT = 99999999999999.99;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_ITM = 0;
    double WS_FEE_AMT = 0;
    double WS_CAL_FAV_AMT = 0;
    short WS_CNT = 0;
    String WS_REG_CODE = " ";
    short WS_F_ITM_CNT = 0;
    double WS_FAV_PCT = 0;
    String WS_REF_CCY = " ";
    char WS_FEE_EXG_TYP = ' ';
    double WS_UP_AMT = 0;
    double WS_DWN_AMT = 0;
    double WS_MIN_AMT = 0;
    double WS_MAX_AMT = 0;
    String WS_MID_CCY = " ";
    String WS_FEE_CCY = " ";
    String WS_CHG_CCY = " ";
    double WS_CMZ_AMT = 0;
    double WS_TX_AMT = 0;
    String WS_FEE_CODE = " ";
    int WS_PFEE_PROC_DT = 0;
    char WS_SKPCHG_FLG = ' ';
    char WS_SKPALL_FLG = ' ';
    BPZSCFEE_WS_FEE_CDS[] WS_FEE_CDS = new BPZSCFEE_WS_FEE_CDS[20];
    short WS_I = 0;
    char WS_URMT_FLG = ' ';
    char WS_USING_BRANCH_FLAG = ' ';
    char WS_USING_CMZ_FLAG = ' ';
    char WS_FILE_STS = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCTCALF BPCTCALF = new BPCTCALF();
    BPCFX BPCFX = new BPCFX();
    BPCFFCON BPCFFCON = new BPCFFCON();
    BPCONESF BPCONESF = new BPCONESF();
    BPCPQCMZ BPCPQCMZ = new BPCPQCMZ();
    BPRCMZR BPRCMZR = new BPRCMZR();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCFPARM BPCFPARM = new BPCFPARM();
    BPCTIFAV BPCTIFAV = new BPCTIFAV();
    BPCFFCAL BPCFFCAL = new BPCFFCAL();
    BPRFBAS BPRFBAS = new BPRFBAS();
    BPCTFBAS BPCTFBAS = new BPCTFBAS();
    BPVFMSK BPVFMSK = new BPVFMSK();
    BPVFCOM BPVFCOM = new BPVFCOM();
    BPVFSTD BPVFSTD = new BPVFSTD();
    DCCPACTY DCCPACTY = new DCCPACTY();
    CICACCU CICACCU = new CICACCU();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPRFEAG BPRFEAG = new BPRFEAG();
    CICQACRI CICQACRI = new CICQACRI();
    String WS_SGN_CINO = " ";
    String WS_SGN_AC = " ";
    int WS_CLT_BR = 0;
    String WS_FEE_CD = " ";
    char WS_FDT_TYP = ' ';
    char WS_PRC_STS = ' ';
    int WS_PROC_DT = 0;
    String WS_PROD_CD = " ";
    SCCGWA SCCGWA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPCTCFEE BPCTCFEE;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    String LK_REC = " ";
    public BPZSCFEE() {
        for (int i=0;i<20;i++) WS_FEE_CDS[i] = new BPZSCFEE_WS_FEE_CDS();
    }
    public void MP(SCCGWA SCCGWA, BPCTCFEE BPCTCFEE) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTCFEE = BPCTCFEE;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSCFEE return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCONESF);
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            WS_URMT_FLG = 'N';
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B015_GET_FEE_BASIC_INFO();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B030_GET_BASIC_FEE_CN();
            if (pgmRtn) return;
            B055_01_FEE_CMZ_INFO();
            if (pgmRtn) return;
            if (BPCPQCMZ.DATE_FLG != 'Y') {
                B035_GET_FAV_INFO_CN();
                if (pgmRtn) return;
                B040_CAL_FEE_AMT_CN();
                if (pgmRtn) return;
            } else {
                B055_CAL_CMZ_FEE_AMT_CN();
                if (pgmRtn) return;
            }
            B060_EXCHANGE_CURRENCY_CN();
            if (pgmRtn) return;
        } else {
            B030_GET_BASIC_FEE();
            if (pgmRtn) return;
            B035_GET_FAV_INFO();
            if (pgmRtn) return;
            B040_CAL_FEE_AMT();
            if (pgmRtn) return;
        }
        B089_COLLECTION_INFO();
        if (pgmRtn) return;
        B090_OUTPUT_INFO();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCONESF.FEE_CHG_TYPE);
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCTCFEE.INPUT_AREA.AC_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = BPCTCFEE.INPUT_AREA.AC_NO;
            S000_CALL_CIZACCU();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCTCFEE.INPUT_AREA.CI_NO);
            CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
            if (BPCTCFEE.INPUT_AREA.CI_NO.trim().length() > 0) {
                if (!CICACCU.DATA.CI_NO.equalsIgnoreCase(BPCTCFEE.INPUT_AREA.CI_NO)) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AC_CI_NOT_RELATED;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B015_GET_FEE_BASIC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFBAS);
        IBS.init(SCCGWA, BPCTFBAS);
        BPCTFBAS.INFO.POINTER = BPRFBAS;
        BPCTFBAS.INFO.REC_LEN = 312;
        BPRFBAS.KEY.FEE_CODE = BPCTCFEE.INPUT_AREA.FEE_CODE;
        BPCTFBAS.INFO.FUNC = 'Q';
        CEP.TRC(SCCGWA, BPCTCFEE.INPUT_AREA.FEE_CODE);
        CEP.TRC(SCCGWA, BPRFBAS.KEY);
        CEP.TRC(SCCGWA, BPRFBAS.FEE_NO);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        S000_CALL_BPZTFBAS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, BPCTFBAS);
        CEP.TRC(SCCGWA, BPRFBAS);
        if (BPCTFBAS.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "ENTER NOTFND ");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CODE_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRFBAS.START_DATE > SCCGWA.COMM_AREA.AC_DATE 
            || BPRFBAS.END_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FE_C_PARM_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_FEE_CODE = BPRFBAS.KEY.FEE_CODE;
        if (BPRFBAS.REG_TYPE.equalsIgnoreCase("00")) {
            WS_REG_CODE = " ";
        } else {
            if (BPRFBAS.REG_TYPE.equalsIgnoreCase("99")) {
                WS_REG_CODE = "" + SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                JIBS_tmp_int = WS_REG_CODE.length();
                for (int i=0;i<6-JIBS_tmp_int;i++) WS_REG_CODE = "0" + WS_REG_CODE;
            } else {
                R000_GET_REG_CODE_BY_TYPE();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_GET_FEE_COM_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPVFCOM);
        IBS.init(SCCGWA, BPCFPARM);
        BPCFPARM.INFO.TYPE = "FCOM ";
        BPVFCOM.KEY.FEE_CODE = WS_FEE_CODE;
        BPVFCOM.KEY.CHN_NO = SCCGWA.COMM_AREA.CHNL;
        BPVFCOM.KEY.FREE_FMT = " ";
        BPCFPARM.INFO.POINTER = BPVFCOM;
        BPCFPARM.INFO.FUNC = '3';
        CEP.TRC(SCCGWA, BPVFCOM.KEY);
        S000_CALL_BPZFPARM();
        if (pgmRtn) return;
        if (BPCFPARM.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FCOM_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_GET_BASIC_FEE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPVFSTD);
        IBS.init(SCCGWA, BPCFPARM);
        CEP.TRC(SCCGWA, BPCTCFEE.INPUT_AREA.FEE_AMT);
        CEP.TRC(SCCGWA, BPCTCFEE.INPUT_AREA.CFEE_CCY);
        if (BPCTCFEE.INPUT_AREA.FEE_AMT != 0) {
            BPVFSTD.KEY.FEE_CODE = WS_FEE_CODE;
            BPVFSTD.KEY.REG_CODE = " ";
            BPVFSTD.KEY.CHN_NO = " ";
            BPVFSTD.KEY.FREE_FMT = " ";
            BPVFSTD.KEY.REF_CCY = BPCTCFEE.INPUT_AREA.CFEE_CCY;
            BPCFPARM.RETURN_INFO = 'F';
            BPCFPARM.INFO.FUNC = '3';
            BPCFPARM.INFO.TYPE = "FSTD ";
            BPCFPARM.INFO.POINTER = BPVFSTD;
            S000_CALL_BPZFPARM();
            if (pgmRtn) return;
            if (BPCFPARM.RETURN_INFO == 'N') {
                CEP.TRC(SCCGWA, "FFFFFFFFFFFFFFF");
                CEP.TRC(SCCGWA, BPCRBANK.LOC_CCY1);
                BPVFSTD.KEY.FEE_CODE = WS_FEE_CODE;
                BPVFSTD.KEY.REG_CODE = " ";
                BPVFSTD.KEY.CHN_NO = " ";
                BPVFSTD.KEY.FREE_FMT = " ";
                BPVFSTD.KEY.REF_CCY = BPCRBANK.LOC_CCY1;
                BPCFPARM.RETURN_INFO = 'F';
                BPCFPARM.INFO.FUNC = '3';
                BPCFPARM.INFO.TYPE = "FSTD ";
                BPCFPARM.INFO.POINTER = BPVFSTD;
                S000_CALL_BPZFPARM();
                if (pgmRtn) return;
                if (BPCFPARM.RETURN_INFO == 'N') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FSTD_NOTFND;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    CEP.TRC(SCCGWA, "GGGGGGGGGGGGGGG");
                    CEP.TRC(SCCGWA, BPCTCFEE.INPUT_AREA.CFEE_CCY);
                    if (!BPVFSTD.VAL.FEE_CCY.equalsIgnoreCase(BPCTCFEE.INPUT_AREA.CFEE_CCY)) {
                        IBS.init(SCCGWA, BPCFX);
                        BPCFX.FUNC = '3';
                        BPCFX.EXR_TYPE = "MDR";
                        BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                        BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                        CEP.TRC(SCCGWA, BPCRBANK.LOC_CCY1);
                        BPCFX.BUY_CCY = BPVFSTD.VAL.FEE_CCY;
                        CEP.TRC(SCCGWA, BPCFX.BUY_CCY);
                        BPCFX.SELL_AMT = BPCTCFEE.INPUT_AREA.FEE_AMT;
                        BPCFX.SELL_CCY = BPCTCFEE.INPUT_AREA.CFEE_CCY;
                        CEP.TRC(SCCGWA, BPCFX.SELL_CCY);
                        CEP.TRC(SCCGWA, BPCFX.BUY_AMT);
                        CEP.TRC(SCCGWA, BPVFSTD.VAL.START_AMT);
                        S000_CALL_BPZSFX();
                        if (pgmRtn) return;
                        if (BPCFX.BUY_AMT < BPVFSTD.VAL.START_AMT) {
                            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TX_LESS_START_AMT;
                            S000_ERR_MSG_PROC();
                            if (pgmRtn) return;
                        }
                    }
                }
            } else {
                CEP.TRC(SCCGWA, BPCTCFEE.INPUT_AREA.FEE_AMT);
                CEP.TRC(SCCGWA, BPVFSTD.VAL.START_AMT);
                if (BPCTCFEE.INPUT_AREA.FEE_AMT < BPVFSTD.VAL.START_AMT) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TX_LESS_START_AMT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        } else {
        }
        IBS.init(SCCGWA, BPCFFCAL);
        BPCFFCAL.DATA.FEE_CODE = WS_FEE_CODE;
        BPCFFCAL.DATA.CHNL_NO = BPCTCFEE.INPUT_AREA.CHNL;
        BPCFFCAL.DATA.CNT_CCY = BPCTCFEE.INPUT_AREA.CFEE_CCY;
        BPCFFCAL.DATA.TX_AMT = BPCTCFEE.INPUT_AREA.FEE_AMT;
        BPCFFCAL.DATA.TX_CNT = BPCTCFEE.INPUT_AREA.ACC_CNT;
        BPCFFCAL.DATA.CHG_CCY = BPCTCFEE.INPUT_AREA.CHG_CCY;
        BPCFFCAL.DATA.FEE_CCY = BPCTCFEE.INPUT_AREA.CFEE_CCY;
        BPCFFCAL.DATA.EXG_GROUP = "MDR";
        S000_CALL_BPZFFCAL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.FEE_AMT);
    }
    public void B030_GET_BASIC_FEE_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCTCFEE.INPUT_AREA.FEE_AMT);
        CEP.TRC(SCCGWA, BPCTCFEE.INPUT_AREA.CFEE_CCY);
        B030_01_GET_FEE_STD_INFO_CN();
        if (pgmRtn) return;
        if (BPCTCFEE.INPUT_AREA.FEE_AMT != 0) {
            CEP.TRC(SCCGWA, "GGGGGGGGGGGGGGG");
            CEP.TRC(SCCGWA, BPCTCFEE.INPUT_AREA.CFEE_CCY);
            if (!BPVFSTD.VAL.FEE_CCY.equalsIgnoreCase(BPCTCFEE.INPUT_AREA.CFEE_CCY)) {
                IBS.init(SCCGWA, BPCFX);
                BPCFX.FUNC = '3';
                BPCFX.EXR_TYPE = "MDR";
                BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                CEP.TRC(SCCGWA, BPCRBANK.LOC_CCY1);
                BPCFX.BUY_CCY = BPVFSTD.VAL.FEE_CCY;
                CEP.TRC(SCCGWA, BPCFX.BUY_CCY);
                BPCFX.SELL_AMT = BPCTCFEE.INPUT_AREA.FEE_AMT;
                BPCFX.SELL_CCY = BPCTCFEE.INPUT_AREA.CFEE_CCY;
                CEP.TRC(SCCGWA, BPCFX.SELL_CCY);
                CEP.TRC(SCCGWA, BPCFX.BUY_AMT);
                CEP.TRC(SCCGWA, BPVFSTD.VAL.START_AMT);
                S000_CALL_BPZSFX();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCFX.BUY_AMT);
                if (BPCFX.BUY_AMT < BPVFSTD.VAL.START_AMT) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TX_LESS_START_AMT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            } else {
                CEP.TRC(SCCGWA, BPCTCFEE.INPUT_AREA.FEE_AMT);
                CEP.TRC(SCCGWA, BPVFSTD.VAL.START_AMT);
                if (BPCTCFEE.INPUT_AREA.FEE_AMT < BPVFSTD.VAL.START_AMT) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TX_LESS_START_AMT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        } else {
        }
        IBS.init(SCCGWA, BPCFFCAL);
        BPCFFCAL.DATA.FEE_CODE = WS_FEE_CODE;
        BPCFFCAL.DATA.CHNL_NO = BPCTCFEE.INPUT_AREA.CHNL;
        BPCFFCAL.DATA.CNT_CCY = BPCTCFEE.INPUT_AREA.CFEE_CCY;
        BPCFFCAL.DATA.TX_AMT = BPCTCFEE.INPUT_AREA.FEE_AMT;
        BPCFFCAL.DATA.TX_CNT = BPCTCFEE.INPUT_AREA.ACC_CNT;
        BPCFFCAL.DATA.CHG_CCY = BPCTCFEE.INPUT_AREA.CHG_CCY;
        BPCFFCAL.DATA.FEE_CCY = BPCTCFEE.INPUT_AREA.CFEE_CCY;
        BPCFFCAL.DATA.EXG_GROUP = "MDR";
        if (WS_USING_BRANCH_FLAG == 'Y') {
            BPCFFCAL.DATA.REG_CODE = "" + BPCPQORG.BRANCH_BR;
            JIBS_tmp_int = BPCFFCAL.DATA.REG_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCFFCAL.DATA.REG_CODE = "0" + BPCFFCAL.DATA.REG_CODE;
        }
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.REG_CODE);
        S000_CALL_BPZFFCAL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.FEE_AMT);
    }
    public void B030_01_GET_FEE_STD_INFO_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
        CEP.TRC(SCCGWA, BPCTCFEE.INPUT_AREA.BR);
        BPCPQORG.BR = BPCTCFEE.INPUT_AREA.BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.BRANCH_BR);
        IBS.init(SCCGWA, BPVFSTD);
        IBS.init(SCCGWA, BPCFPARM);
        BPVFSTD.KEY.FEE_CODE = WS_FEE_CODE;
        BPVFSTD.KEY.REG_CODE = "" + BPCPQORG.BRANCH_BR;
        JIBS_tmp_int = BPVFSTD.KEY.REG_CODE.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPVFSTD.KEY.REG_CODE = "0" + BPVFSTD.KEY.REG_CODE;
        BPVFSTD.KEY.CHN_NO = " ";
        BPVFSTD.KEY.FREE_FMT = " ";
        BPVFSTD.KEY.REF_CCY = BPCTCFEE.INPUT_AREA.CFEE_CCY;
        BPCFPARM.RETURN_INFO = 'F';
        BPCFPARM.INFO.FUNC = '3';
        BPCFPARM.INFO.TYPE = "FSTD ";
        BPCFPARM.INFO.POINTER = BPVFSTD;
        S000_CALL_BPZFPARM();
        if (pgmRtn) return;
        if (BPCFPARM.RETURN_INFO == 'F') {
            CEP.TRC(SCCGWA, WS_USING_BRANCH_FLAG);
            WS_USING_BRANCH_FLAG = 'Y';
        }
        if (BPCFPARM.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, BPRFBAS.EXG_TYP);
            if (BPRFBAS.EXG_TYP == '0') {
                WS_REF_CCY = BPCRBANK.LOC_CCY1;
            } else {
                WS_REF_CCY = BPCRBANK.EVA_CCY;
            }
            CEP.TRC(SCCGWA, "FFFFFFFFFFFFFFF");
            CEP.TRC(SCCGWA, BPCRBANK.LOC_CCY1);
            CEP.TRC(SCCGWA, BPCRBANK.EVA_CCY);
            CEP.TRC(SCCGWA, WS_REF_CCY);
            IBS.init(SCCGWA, BPVFSTD);
            IBS.init(SCCGWA, BPCFPARM);
            BPVFSTD.KEY.FEE_CODE = WS_FEE_CODE;
            BPVFSTD.KEY.REG_CODE = "" + BPCPQORG.BRANCH_BR;
            JIBS_tmp_int = BPVFSTD.KEY.REG_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPVFSTD.KEY.REG_CODE = "0" + BPVFSTD.KEY.REG_CODE;
            BPVFSTD.KEY.CHN_NO = " ";
            BPVFSTD.KEY.FREE_FMT = " ";
            BPVFSTD.KEY.REF_CCY = WS_REF_CCY;
            BPCFPARM.RETURN_INFO = 'F';
            BPCFPARM.INFO.FUNC = '3';
            BPCFPARM.INFO.TYPE = "FSTD ";
            BPCFPARM.INFO.POINTER = BPVFSTD;
            S000_CALL_BPZFPARM();
            if (pgmRtn) return;
            if (BPCFPARM.RETURN_INFO == 'F') {
                CEP.TRC(SCCGWA, WS_USING_BRANCH_FLAG);
                WS_USING_BRANCH_FLAG = 'Y';
            }
            if (BPCFPARM.RETURN_INFO == 'N') {
                IBS.init(SCCGWA, BPVFSTD);
                IBS.init(SCCGWA, BPCFPARM);
                BPVFSTD.KEY.FEE_CODE = WS_FEE_CODE;
                BPVFSTD.KEY.REG_CODE = " ";
                BPVFSTD.KEY.CHN_NO = " ";
                BPVFSTD.KEY.FREE_FMT = " ";
                BPVFSTD.KEY.REF_CCY = BPCTCFEE.INPUT_AREA.CFEE_CCY;
                BPCFPARM.RETURN_INFO = 'F';
                BPCFPARM.INFO.FUNC = '3';
                BPCFPARM.INFO.TYPE = "FSTD ";
                BPCFPARM.INFO.POINTER = BPVFSTD;
                S000_CALL_BPZFPARM();
                if (pgmRtn) return;
                if (BPCFPARM.RETURN_INFO == 'N') {
                    IBS.init(SCCGWA, BPVFSTD);
                    IBS.init(SCCGWA, BPCFPARM);
                    BPVFSTD.KEY.FEE_CODE = WS_FEE_CODE;
                    BPVFSTD.KEY.REG_CODE = " ";
                    BPVFSTD.KEY.CHN_NO = " ";
                    BPVFSTD.KEY.FREE_FMT = " ";
                    BPVFSTD.KEY.REF_CCY = WS_REF_CCY;
                    BPCFPARM.RETURN_INFO = 'F';
                    BPCFPARM.INFO.FUNC = '3';
                    BPCFPARM.INFO.TYPE = "FSTD ";
                    BPCFPARM.INFO.POINTER = BPVFSTD;
                    S000_CALL_BPZFPARM();
                    if (pgmRtn) return;
                }
            }
            if (BPCFPARM.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FSTD_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B035_GET_FAV_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTIFAV);
        BPCTIFAV.INPUT_AREA.FEE_CODE = BPCTCFEE.INPUT_AREA.FEE_CODE;
        BPCTIFAV.INPUT_AREA.CHNL = SCCGWA.COMM_AREA.CHNL;
        BPCTIFAV.INPUT_AREA.FREE_FMT = " ";
        BPCTIFAV.INPUT_AREA.BASIC_FEE = BPCFFCAL.DATA.FEE_AMT;
        BPCTIFAV.INPUT_AREA.FAV_CCY = BPCTCFEE.INPUT_AREA.CFEE_CCY;
        BPCTIFAV.INPUT_AREA.PROD_CODE = BPCTCFEE.INPUT_AREA.PRD_CODE;
        BPCTIFAV.INPUT_AREA.TX_AC = BPCTCFEE.INPUT_AREA.AC_NO;
        BPCTIFAV.INPUT_AREA.TX_CI = BPCTCFEE.INPUT_AREA.CI_NO;
        BPCTIFAV.INPUT_AREA.TX_AMT = BPCTCFEE.INPUT_AREA.FEE_AMT;
        BPCTIFAV.INPUT_AREA.TX_CNT = BPCTCFEE.INPUT_AREA.ACC_CNT;
        S000_CALL_BPZPIFAV();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCTIFAV.OUTPUT_AREA.FAV_AMT);
    }
    public void B035_GET_FAV_INFO_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTIFAV);
        BPCTIFAV.INPUT_AREA.FEE_CODE = BPCTCFEE.INPUT_AREA.FEE_CODE;
        BPCTIFAV.INPUT_AREA.REGION_CODE = "" + BPCTCFEE.INPUT_AREA.BR;
        JIBS_tmp_int = BPCTIFAV.INPUT_AREA.REGION_CODE.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCTIFAV.INPUT_AREA.REGION_CODE = "0" + BPCTIFAV.INPUT_AREA.REGION_CODE;
        BPCTIFAV.INPUT_AREA.CHNL = SCCGWA.COMM_AREA.CHNL;
        BPCTIFAV.INPUT_AREA.FREE_FMT = " ";
        BPCTIFAV.INPUT_AREA.BASIC_FEE = BPCFFCAL.DATA.FEE_AMT;
        BPCTIFAV.INPUT_AREA.FAV_CCY = BPCTCFEE.INPUT_AREA.CFEE_CCY;
        BPCTIFAV.INPUT_AREA.PROD_CODE = BPCTCFEE.INPUT_AREA.PRD_CODE;
        BPCTIFAV.INPUT_AREA.TX_AC = BPCTCFEE.INPUT_AREA.AC_NO;
        BPCTIFAV.INPUT_AREA.TX_CI = BPCTCFEE.INPUT_AREA.CI_NO;
        BPCTIFAV.INPUT_AREA.TX_AMT = BPCTCFEE.INPUT_AREA.FEE_AMT;
        BPCTIFAV.INPUT_AREA.TX_CNT = BPCTCFEE.INPUT_AREA.ACC_CNT;
        if (WS_URMT_FLG == 'Y') {
            BPCTIFAV.INPUT_AREA.URG_RMT_FLG = 'Y';
        }
        S000_CALL_BPZPIFAV();
        if (pgmRtn) return;
    }
    public void B040_CAL_FEE_AMT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.CHG_CCY);
        BPCONESF.FEEE_CCY = BPCFFCAL.DATA.FEE_CCY;
        CEP.TRC(SCCGWA, "ENTER SAME CCY");
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.FEE_AMT);
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.CHG_AMT);
        CEP.TRC(SCCGWA, BPCTIFAV.OUTPUT_AREA.FAV_PCT);
        CEP.TRC(SCCGWA, BPCTIFAV.OUTPUT_AREA.FAV_AMT);
        if (BPCTIFAV.OUTPUT_AREA.FAV_PCT != 0) {
            CEP.TRC(SCCGWA, "11111111");
            WS_FEE_AMT = BPCFFCAL.DATA.FEE_AMT * BPCTIFAV.OUTPUT_AREA.FAV_PCT / 100;
            BPCONESF.FEE_AMT = BPCFFCAL.DATA.FEE_AMT - WS_FEE_AMT;
        } else {
            CEP.TRC(SCCGWA, "22222222");
            BPCONESF.FEE_AMT = BPCFFCAL.DATA.FEE_AMT - BPCTIFAV.OUTPUT_AREA.FAV_AMT;
            CEP.TRC(SCCGWA, "33333333");
        }
        CEP.TRC(SCCGWA, "44444444");
        if (BPCFFCAL.DATA.FEE_CCY.equalsIgnoreCase(BPCFFCAL.DATA.CHG_CCY)) {
            CEP.TRC(SCCGWA, "55555555");
            BPCONESF.CHG_BAS = BPCFFCAL.DATA.FEE_AMT;
            BPCONESF.CHG_AMT = BPCONESF.FEE_AMT;
        } else {
            CEP.TRC(SCCGWA, "66666666");
            if (BPCFFCAL.DATA.FEE_AMT > 0) {
                IBS.init(SCCGWA, BPCFX);
                BPCFX.FUNC = '3';
                BPCFX.EXR_TYPE = "MDR";
                BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                CEP.TRC(SCCGWA, BPRFBAS.CHG_TYPE);
                if (BPRFBAS.CHG_TYPE == '1') {
                    CEP.TRC(SCCGWA, "CCCCCCCC");
                    BPCFX.SELL_CCY = BPCTCFEE.INPUT_AREA.CHG_CCY;
                    BPCFX.BUY_AMT = BPCFFCAL.DATA.FEE_AMT;
                    BPCFX.BUY_CCY = BPCFFCAL.DATA.FEE_CCY;
                    BPCFX.CI_NO = BPCTCFEE.INPUT_AREA.CI_NO;
                    S000_CALL_BPZSFX();
                    if (pgmRtn) return;
                    BPCONESF.CHG_BAS = BPCFX.SELL_AMT;
                } else {
                    CEP.TRC(SCCGWA, "DDDDDDDD");
                    BPCFX.BUY_CCY = BPCTCFEE.INPUT_AREA.CHG_CCY;
                    BPCFX.SELL_AMT = BPCFFCAL.DATA.FEE_AMT;
                    BPCFX.SELL_CCY = BPCFFCAL.DATA.FEE_CCY;
                    BPCFX.CI_NO = BPCTCFEE.INPUT_AREA.CI_NO;
                    S000_CALL_BPZSFX();
                    if (pgmRtn) return;
                    BPCONESF.CHG_BAS = BPCFX.BUY_AMT;
                }
                IBS.init(SCCGWA, BPCFX);
                BPCFX.FUNC = '3';
                BPCFX.EXR_TYPE = "MDR";
                BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                if (BPRFBAS.CHG_TYPE == '1') {
                    CEP.TRC(SCCGWA, "AAAAAAAA");
                    BPCFX.SELL_CCY = BPCTCFEE.INPUT_AREA.CHG_CCY;
                    BPCFX.BUY_AMT = BPCONESF.FEE_AMT;
                    BPCFX.BUY_CCY = BPCFFCAL.DATA.FEE_CCY;
                    BPCFX.CI_NO = BPCTCFEE.INPUT_AREA.CI_NO;
                    S000_CALL_BPZSFX();
                    if (pgmRtn) return;
                    BPCONESF.CHG_AMT = BPCFX.SELL_AMT;
                    BPCONESF.TRN_RATE = BPCFX.TRN_RATE;
                } else {
                    CEP.TRC(SCCGWA, "BBBBBBBB");
                    BPCFX.BUY_CCY = BPCTCFEE.INPUT_AREA.CHG_CCY;
                    BPCFX.SELL_AMT = BPCONESF.FEE_AMT;
                    BPCFX.SELL_CCY = BPCFFCAL.DATA.FEE_CCY;
                    BPCFX.CI_NO = BPCTCFEE.INPUT_AREA.CI_NO;
                    S000_CALL_BPZSFX();
                    if (pgmRtn) return;
                    BPCONESF.CHG_AMT = BPCFX.BUY_AMT;
                    BPCONESF.TRN_RATE = BPCFX.TRN_RATE;
                    CEP.TRC(SCCGWA, BPCONESF.TRN_RATE);
                }
                BPCONESF.EXG_DATE = BPCFX.EFF_DATE;
                BPCONESF.EXG_TIME = BPCFX.EFF_TIME;
            }
            CEP.TRC(SCCGWA, "77777777");
        }
        CEP.TRC(SCCGWA, BPCONESF.CHG_AMT);
        if (BPCONESF.CHG_AMT < 0) {
            CEP.TRC(SCCGWA, "77777777");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_AMT_LT_LOW_AMT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCONESF.FEE_AMT);
        BPCONESF.FEE_BAS = BPCFFCAL.DATA.FEE_AMT;
    }
    public void B040_CAL_FEE_AMT_CN() throws IOException,SQLException,Exception {
        BPCONESF.FEEE_CCY = BPCFFCAL.DATA.FEE_CCY;
        BPCONESF.FEE_AMT = BPCFFCAL.DATA.FEE_AMT;
        if (BPCTIFAV.OUTPUT_AREA.FAV_PCT != 0) {
            if (BPCTIFAV.OUTPUT_AREA.ADJ_TYP != '2') {
                WS_FEE_AMT = BPCFFCAL.DATA.FEE_AMT * BPCTIFAV.OUTPUT_AREA.FAV_PCT / 100;
                bigD = new BigDecimal(WS_FEE_AMT);
                WS_FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                CEP.TRC(SCCGWA, "WS-FEE-AMT1");
                CEP.TRC(SCCGWA, WS_FEE_AMT);
            } else {
                WS_FEE_AMT = BPCFFCAL.DATA.TX_AMT * BPCTIFAV.OUTPUT_AREA.FAV_PCT / 100;
                bigD = new BigDecimal(WS_FEE_AMT);
                WS_FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                CEP.TRC(SCCGWA, "WS-FEE-AMT2");
                CEP.TRC(SCCGWA, WS_FEE_AMT);
            }
        } else {
            WS_FEE_AMT = BPCTIFAV.OUTPUT_AREA.FAV_AMT;
        }
        if (BPCTIFAV.OUTPUT_AREA.ADJ_TYP == '0') {
            BPCONESF.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + WS_FEE_AMT;
        } else if (BPCTIFAV.OUTPUT_AREA.ADJ_TYP == '1') {
            BPCONESF.FEE_AMT = BPCFFCAL.DATA.FEE_AMT - WS_FEE_AMT;
        } else if (BPCTIFAV.OUTPUT_AREA.ADJ_TYP == '2') {
            BPCONESF.FEE_AMT = WS_FEE_AMT;
        } else {
        }
        CEP.TRC(SCCGWA, BPRFBAS.CHG_TYPE);
    }
    public void B055_01_FEE_CMZ_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQCMZ);
        if (BPCTCFEE.INPUT_AREA.CI_NO.trim().length() == 0) {
            BPCPQCMZ.CI_NO = CICACCU.DATA.CI_NO;
        } else {
            BPCPQCMZ.CI_NO = BPCTCFEE.INPUT_AREA.CI_NO;
        }
        BPCPQCMZ.FEE_CODE = BPCTCFEE.INPUT_AREA.FEE_CODE;
        BPCPQCMZ.INQ_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPQCMZ.CMZ_AC = BPCTCFEE.INPUT_AREA.AC_NO;
        BPCPQCMZ.CMZ_CARD = BPCTCFEE.INPUT_AREA.AC_NO;
        S000_CALL_BPZPQCMZ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQCMZ.DATE_FLG);
        CEP.TRC(SCCGWA, BPCPQCMZ.CCY_RULE);
        CEP.TRC(SCCGWA, BPCPQCMZ.CMZ_CCY);
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.FEE_CCY);
        if (BPCPQCMZ.DATE_FLG == 'Y' 
            && BPCPQCMZ.CCY_RULE == '0' 
            && !BPCPQCMZ.CMZ_CCY.equalsIgnoreCase(BPCFFCAL.DATA.FEE_CCY)) {
            BPCPQCMZ.DATE_FLG = 'N';
        }
        CEP.TRC(SCCGWA, BPCPQCMZ.DATE_FLG);
    }
    public void B055_CAL_CMZ_FEE_AMT_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPQCMZ.CMZ_FLG1);
        if (BPCPQCMZ.CMZ_FLG1 == '0') {
            CEP.TRC(SCCGWA, "1111");
            CEP.TRC(SCCGWA, BPCPQCMZ.CMZ_FLG1);
            if (BPCPQCMZ.CMZ_FLG2 == '0') {
                S000_CMZ_CCY_FOR_AMT();
                if (pgmRtn) return;
                if (BPCFFCAL.DATA.FEE_AMT > WS_CMZ_AMT) {
                    BPCONESF.FEE_AMT = BPCFFCAL.DATA.FEE_AMT - WS_CMZ_AMT;
                } else {
                    CEP.TRC(SCCGWA, "LESS THAN");
                    BPCONESF.FEE_AMT = 0;
                }
            } else {
                CEP.TRC(SCCGWA, BPCPQCMZ.CMZ_PCN);
                CEP.TRC(SCCGWA, BPCFFCAL.DATA.FEE_AMT);
                BPCONESF.FEE_AMT = BPCFFCAL.DATA.FEE_AMT - BPCFFCAL.DATA.FEE_AMT * BPCPQCMZ.CMZ_PCN;
                bigD = new BigDecimal(BPCONESF.FEE_AMT);
                BPCONESF.FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                CEP.TRC(SCCGWA, "WS-FEE-AMT5");
                CEP.TRC(SCCGWA, BPCONESF.FEE_AMT);
                CEP.TRC(SCCGWA, BPCONESF.FEE_AMT);
            }
        } else {
            CEP.TRC(SCCGWA, "222");
            CEP.TRC(SCCGWA, BPCPQCMZ.CMZ_FLG1);
            CEP.TRC(SCCGWA, BPCPQCMZ.CMZ_CCY);
            CEP.TRC(SCCGWA, BPCTCFEE.INPUT_AREA.CHG_CCY);
            if (BPCPQCMZ.CMZ_FLG1 == '1') {
                CEP.TRC(SCCGWA, "GET THE PQCMZ-CMZ-AMT!!!");
                S000_CMZ_CCY_FOR_AMT();
                if (pgmRtn) return;
                BPCONESF.FEE_AMT = WS_CMZ_AMT;
            }
        }
        CEP.TRC(SCCGWA, BPCONESF.FEE_AMT);
        CEP.TRC(SCCGWA, BPCONESF.CHG_AMT);
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_W_HAS_DIY_CHG_FEE;
        S000_ERR_MSG_PROC();
        if (pgmRtn) return;
    }
    public void B089_COLLECTION_INFO() throws IOException,SQLException,Exception {
        WS_SKPCHG_FLG = 'N';
        CEP.TRC(SCCGWA, "QWQWQW");
        CEP.TRC(SCCGWA, WS_SGN_CINO);
        if (WS_SGN_CINO.trim().length() > 0) {
            T000_STARTBR_BPTFEAG_01();
            if (pgmRtn) return;
            T000_READNEXT_BPTFEAG();
            if (pgmRtn) return;
            while (WS_FILE_STS != 'N' 
                && WS_SKPCHG_FLG != 'Y') {
                WS_PFEE_PROC_DT = SCCGWA.COMM_AREA.AC_DATE;
                CEP.TRC(SCCGWA, WS_PFEE_PROC_DT);
                CEP.TRC(SCCGWA, BPRFEAG.KEY.EFF_DATE);
                CEP.TRC(SCCGWA, BPRFEAG.EXP_DATE);
                CEP.TRC(SCCGWA, BPRFEAG.KEY.CLT_CI_NO);
                CEP.TRC(SCCGWA, BPRFEAG.KEY.CLT_AC);
                CEP.TRC(SCCGWA, WS_SGN_AC);
                if (((WS_SGN_CINO.equalsIgnoreCase(BPRFEAG.KEY.CLT_CI_NO) 
                    && BPRFEAG.KEY.CLT_AC.trim().length() == 0) 
                    || (WS_SGN_AC.equalsIgnoreCase(BPRFEAG.KEY.CLT_AC) 
                    && BPRFEAG.KEY.CLT_AC.trim().length() > 0)) 
                    && WS_PFEE_PROC_DT >= BPRFEAG.KEY.EFF_DATE 
                    && WS_PFEE_PROC_DT <= BPRFEAG.EXP_DATE) {
                    WS_SKPCHG_FLG = 'Y';
                    CEP.TRC(SCCGWA, WS_SKPCHG_FLG);
                    WS_FEE_CDS[1-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE1;
                    WS_FEE_CDS[2-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE2;
                    WS_FEE_CDS[3-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE3;
                    WS_FEE_CDS[4-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE4;
                    WS_FEE_CDS[5-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE5;
                    WS_FEE_CDS[6-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE6;
                    WS_FEE_CDS[7-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE7;
                    WS_FEE_CDS[8-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE8;
                    WS_FEE_CDS[9-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE9;
                    WS_FEE_CDS[10-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE10;
                    WS_FEE_CDS[11-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE11;
                    WS_FEE_CDS[12-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE12;
                    WS_FEE_CDS[13-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE13;
                    WS_FEE_CDS[14-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE14;
                    WS_FEE_CDS[15-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE15;
                    WS_FEE_CDS[16-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE16;
                    WS_FEE_CDS[17-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE17;
                    WS_FEE_CDS[18-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE18;
                    WS_FEE_CDS[19-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE19;
                    WS_FEE_CDS[20-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE20;
                    CEP.TRC(SCCGWA, WS_FEE_CDS[1-1].WS_FEE_CD1);
                    CEP.TRC(SCCGWA, WS_FEE_CDS[2-1].WS_FEE_CD1);
                    CEP.TRC(SCCGWA, WS_FEE_CDS[3-1].WS_FEE_CD1);
                    WS_PFEE_PROC_DT = 0;
                }
                if (WS_SKPCHG_FLG != 'Y') {
                    T000_READNEXT_BPTFEAG();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, "WANG54");
                }
            }
            T000_ENDBR_BPTFEAG();
            if (pgmRtn) return;
            if (WS_SKPCHG_FLG != 'Y') {
                WS_PROD_CD = BPCTCFEE.INPUT_AREA.PRD_CODE;
                T000_STARTBR_BPTFEAG_02();
                if (pgmRtn) return;
                T000_READNEXT_BPTFEAG();
                if (pgmRtn) return;
                while (WS_FILE_STS != 'N' 
                    && WS_SKPCHG_FLG != 'Y') {
                    WS_PFEE_PROC_DT = SCCGWA.COMM_AREA.AC_DATE;
                    CEP.TRC(SCCGWA, WS_PFEE_PROC_DT);
                    if (((WS_PROD_CD.equalsIgnoreCase(BPRFEAG.KEY.PRDT_CODE) 
                        && BPRFEAG.KEY.CLT_AC.trim().length() == 0 
                        && BPRFEAG.KEY.CLT_CI_NO.equalsIgnoreCase("99999999999"))) 
                        && WS_PFEE_PROC_DT >= BPRFEAG.KEY.EFF_DATE 
                        && WS_PFEE_PROC_DT <= BPRFEAG.EXP_DATE) {
                        WS_SKPCHG_FLG = 'Y';
                        CEP.TRC(SCCGWA, WS_SKPCHG_FLG);
                        WS_FEE_CDS[1-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE1;
                        WS_FEE_CDS[2-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE2;
                        WS_FEE_CDS[3-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE3;
                        WS_FEE_CDS[4-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE4;
                        WS_FEE_CDS[5-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE5;
                        WS_FEE_CDS[6-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE6;
                        WS_FEE_CDS[7-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE7;
                        WS_FEE_CDS[8-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE8;
                        WS_FEE_CDS[9-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE9;
                        WS_FEE_CDS[10-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE10;
                        WS_FEE_CDS[11-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE11;
                        WS_FEE_CDS[12-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE12;
                        WS_FEE_CDS[13-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE13;
                        WS_FEE_CDS[14-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE14;
                        WS_FEE_CDS[15-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE15;
                        WS_FEE_CDS[16-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE16;
                        WS_FEE_CDS[17-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE17;
                        WS_FEE_CDS[18-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE18;
                        WS_FEE_CDS[19-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE19;
                        WS_FEE_CDS[20-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE20;
                        CEP.TRC(SCCGWA, WS_FEE_CDS[1-1].WS_FEE_CD1);
                        CEP.TRC(SCCGWA, WS_FEE_CDS[2-1].WS_FEE_CD1);
                        CEP.TRC(SCCGWA, WS_FEE_CDS[3-1].WS_FEE_CD1);
                        WS_PFEE_PROC_DT = 0;
                    }
                    if (WS_SKPCHG_FLG != 'Y') {
                        T000_READNEXT_BPTFEAG();
                        if (pgmRtn) return;
                    }
                }
                T000_ENDBR_BPTFEAG();
                if (pgmRtn) return;
            }
            WS_SKPALL_FLG = 'N';
            if (WS_SKPCHG_FLG == 'Y') {
                CEP.TRC(SCCGWA, BPRFEAG.CLT_RANGE);
                if (BPRFEAG.CLT_RANGE != 'A') {
                    for (WS_I = 1; WS_I <= 20 
                        && WS_FEE_CDS[WS_I-1].WS_FEE_CD1.trim().length() != 0 
                        && WS_SKPALL_FLG != 'Y'; WS_I += 1) {
                        CEP.TRC(SCCGWA, BPCTCFEE.INPUT_AREA.FEE_CODE);
                        CEP.TRC(SCCGWA, WS_FEE_CDS[WS_I-1].WS_FEE_CD1);
                        CEP.TRC(SCCGWA, "WANG56");
                        if (BPCTCFEE.INPUT_AREA.FEE_CODE.equalsIgnoreCase(WS_FEE_CDS[WS_I-1].WS_FEE_CD1)) {
                            CEP.TRC(SCCGWA, "WANG57");
                            WS_SKPALL_FLG = 'Y';
                        }
                    }
                }
                if (BPRFEAG.CLT_RANGE == 'A') {
                    CEP.TRC(SCCGWA, "WANG58");
                    WS_SKPALL_FLG = 'Y';
                }
            }
        }
        if (WS_SKPALL_FLG == 'Y') {
            BPCONESF.FEE_CHG_TYPE = 'C';
        } else {
            BPCONESF.FEE_CHG_TYPE = 'A';
        }
    }
