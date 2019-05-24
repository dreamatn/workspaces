package com.hisun.PN;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.DD.*;
import com.hisun.AI.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class PNZUDFT {
    int JIBS_tmp_int;
    DBParm PNTPROD_RD;
    String JIBS_tmp_str[] = new String[10];
    DBParm PNTDFT_RD;
    DBParm PNTDFPSW_RD;
    String CPN_F_BPZFFTXI = "BP-F-F-TX-INFO";
    String K_TBL_BCC = "PNTPROD";
    String CPN_F_BPZFCALF = "BP-F-F-CAL-FEE";
    String CPN_U_ADD_CBOX = "BP-U-ADD-CBOX";
    String CPN_F_PNZFQPRD = "PN-F-GET-PRD";
    String CPN_U_BPZUCNGM = "BP-U-MAINT-CNGM";
    String CPN_U_BPZQCNGL = "BP-P-INQ-CNGL";
    String CPN_U_BPZPOEWA = "BP-P-WRT-ONL-EWA";
    String CPN_U_BPZUBUSE = "BP-U-TLR-BV-USE";
    String CPN_U_BPZFBVQU = "BP-F-BV-PRM-QUERY";
    String CPN_U_DDZUDRAC = "DD-UNIT-DRAW-PROC";
    String CPN_U_PNZUDFT = "PN-U-PERFORM-FINANCE";
    String CPN_U_SCZHMPW = "SC-HM-PASSWORD";
    String CPN_U_BPZPFHIS = "BP-PROC-FHIS";
    String CPN_U_DCZURHLD = "DC-UNIT-RHLD";
    String CPN_U_SCZCBKY = "SC-CPN-SCZCBKY";
    String CPN_U_CIACCU = "CI-INQ-ACCU";
    String PGM_SCSSCLDT = "SCSSCLDT";
    char K_ERROR = 'E';
    String K_TBL_DFT = "PNTDFT";
    String K_CNTR_TYPE = "BADR";
    String K_EVENT_CODE = "CR      ";
    String K_PRDP_TYP = "PRDPR";
    String K_PROD_CO7 = "7631370000";
    String K_PROD_CO8 = "7631360000";
    String K_BVCD_QGHP = "C00005";
    String K_BVCD_HDHP = "C00098";
    String K_TBL_FPSW = "PNTDFPSW";
    String K_OUTPUT_FMT1 = "PN009";
    PNZUDFT_WS_MSGID WS_MSGID = new PNZUDFT_WS_MSGID();
    String WS_ERR_MSG = " ";
    PNZUDFT_CP_PROD_CD CP_PROD_CD = new PNZUDFT_CP_PROD_CD();
    PNZUDFT_WS_MAC_DA WS_MAC_DA = new PNZUDFT_WS_MAC_DA();
    int WS_DATE = 0;
    int WS_DATE2 = 0;
    short WS_HEAD_NO = 0;
    short WS_NO = 0;
    String WS_DRFT_NO_CHANGE = " ";
    short WS_CNT_NUM = 0;
    char WS_APB_FLG = ' ';
    char WS_TABLE_FLG = ' ';
    char WS_STS_CHANGE_FLG = ' ';
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    PNCMSG_ERROR_MSG PNCMSG_ERROR_MSG = new PNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCTCALF BPCTCALF = new BPCTCALF();
    BPCFFCON BPCFFCON = new BPCFFCON();
    BPCUABOX BPCUABOX = new BPCUABOX();
    BPCUBUSE BPCUBUSE = new BPCUBUSE();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    DDCIMMST DDCIMMST = new DDCIMMST();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    AICPIAEV AICPIAEV = new AICPIAEV();
    PNCFQPRD PNCFQPRD = new PNCFQPRD();
    SCCHMPW SCCHMPW = new SCCHMPW();
    SCCCBKY SCCCBKY = new SCCCBKY();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    BPCQCNGL BPCQCNGL = new BPCQCNGL();
    BPCUCNGM BPCUCNGM = new BPCUCNGM();
    BPCAOTH BPCAOTH = new BPCAOTH();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    CICACCU CICACCU = new CICACCU();
    CICQACRI CICQACRI = new CICQACRI();
    CICCUST CICCUST = new CICCUST();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    PNVMPRD PNVMPRD = new PNVMPRD();
    AICUUPIA AICUUPIA = new AICUUPIA();
    PNCOTCEL PNCOTCEL = new PNCOTCEL();
    PNRDFT PNRDFT = new PNRDFT();
    PNRDFPSW PNRDFPSW = new PNRDFPSW();
    PNRPROD PNRPROD = new PNRPROD();
    int WK_DATE = 0;
    SCCGWA SCCGWA;
    PNCUDFT PNCUDFT;
    public void MP(SCCGWA SCCGWA, PNCUDFT PNCUDFT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.PNCUDFT = PNCUDFT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "PNZUDFT return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHK_PROC();
        B201_PRD_CHK_PROC();
        B240_BV_OUT_PROC();
        B210_SET_CAL_FEE_PROC();
        B220_POST_DEBIT();
        B301_INQ_GL_MASTER();
        B302_WRITE_GL_MASTER_PROC();
        B230_POST_CREDIT();
        B250_MST_DFT();
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        if (PNCUDFT.APB_TYPE == '0') {
            WS_APB_FLG = '1';
            if (!PNCUDFT.PROD_CD.equalsIgnoreCase(K_PROD_CO7) 
                && !PNCUDFT.PROD_CD.equalsIgnoreCase(K_PROD_CO8)) {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_PROD_MUST_P);
            }
        } else {
            WS_APB_FLG = '2';
            if (!PNCUDFT.PROD_CD.equalsIgnoreCase(K_PROD_CO8) 
                && !PNCUDFT.PROD_CD.equalsIgnoreCase(K_PROD_CO7)) {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_PROD_MUST_C);
            }
        }
        if (PNCUDFT.PAY_TYPE == 'T') {
            if (PNCUDFT.APP_AC.trim().length() == 0) {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_APAC_MUST_IPT);
            }
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = PNCUDFT.APP_AC;
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            CEP.TRC(SCCGWA, "NCB1015001");
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CI_TYP);
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CI_NO);
            if (!CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC") 
                && !CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD") 
                && !CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
                CEP.TRC(SCCGWA, "ERR AC-TYPE");
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_AC_TYP_NOT_ALLOW);
            }
            CEP.TRC(SCCGWA, PNCUDFT.APB_TYPE);
            CEP.TRC(SCCGWA, WS_APB_FLG);
            if (!CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
                if (WS_APB_FLG == '1') {
                    if (CICQACRI.O_DATA.O_CI_TYP != '1') {
                        CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_CUSTY_NOT_COMM);
                    }
                } else {
                    if (CICQACRI.O_DATA.O_CI_TYP == '1') {
                        CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_CUSTY_NOT_COMM);
                    }
                }
            } else {
                if (PNCUDFT.FEE_FLG == '1') {
                    CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_FEE_FLG_N_TRN);
                }
            }
        }
        if (PNCUDFT.C_T_FLG == 'C') {
            if (PNCUDFT.APB_TYPE != '0') {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_APBTYP_ERR);
            }
            if (PNCUDFT.TRN_FLG != '1') {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_TRNFLG_NOT_COMM);
            }
            if (PNCUDFT.KND.equalsIgnoreCase("C00005")) {
            }
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            IBS.init(SCCGWA, PNRDFT);
            PNRDFT.KEY.BILL_NO = PNCUDFT.DRFT_NO;
            T000_READ_PNTDFT();
            if (WS_TABLE_FLG == 'Y') {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_DFT_REC_EXIST);
            }
        }
        CEP.TRC(SCCGWA, PNCUDFT.PAYEE_NM);
    }
    public void B201_PRD_CHK_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = PNCUDFT.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (BPCPQPRD.PARM_CODE.trim().length() == 0) {
            CEP.TRC(SCCGWA, "---------JICHU CHANPIN SPACE---------");
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_PRD_PARM_NUL);
        }
        CEP.TRC(SCCGWA, "--------GET----JICHUCHANPIN---------");
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        IBS.init(SCCGWA, PNRPROD);
        PNRPROD.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        if (PNRPROD.KEY.CODE == null) PNRPROD.KEY.CODE = "";
        JIBS_tmp_int = PNRPROD.KEY.CODE.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) PNRPROD.KEY.CODE += " ";
        PNRPROD.KEY.CODE = "999999" + PNRPROD.KEY.CODE.substring(6);
        if (PNRPROD.KEY.CODE == null) PNRPROD.KEY.CODE = "";
        JIBS_tmp_int = PNRPROD.KEY.CODE.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) PNRPROD.KEY.CODE += " ";
        if (BPCPQPRD.PARM_CODE == null) BPCPQPRD.PARM_CODE = "";
        JIBS_tmp_int = BPCPQPRD.PARM_CODE.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) BPCPQPRD.PARM_CODE += " ";
        PNRPROD.KEY.CODE = PNRPROD.KEY.CODE.substring(0, 7 - 1) + BPCPQPRD.PARM_CODE + PNRPROD.KEY.CODE.substring(7 + 10 - 1);
        CEP.TRC(SCCGWA, PNRPROD.KEY.CODE);
        CEP.TRC(SCCGWA, WK_DATE);
        PNTPROD_RD = new DBParm();
        PNTPROD_RD.TableName = "PNTPROD";
        PNTPROD_RD.where = "IBS_AC_BK = :PNRPROD.KEY.IBS_AC_BK "
            + "AND CODE = :PNRPROD.KEY.CODE";
        PNTPROD_RD.fst = true;
        PNTPROD_RD.order = "EFF_DATE DESC";
        IBS.READ(SCCGWA, PNRPROD, this, PNTPROD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_FLG = 'N';
            WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_PNTPROD_DATE_ERROR;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_BCC;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
        CEP.TRC(SCCGWA, PNRPROD.CCY);
        CEP.TRC(SCCGWA, PNRPROD.PAY_TERM);
        CEP.TRC(SCCGWA, PNRPROD.AUTO_REL);
    }
    public void B210_SET_CAL_FEE_PROC() throws IOException,SQLException,Exception {
        if (PNCUDFT.FEE_FLG != '2') {
            CEP.TRC(SCCGWA, "FEE FLG OK");
            IBS.init(SCCGWA, BPCFFTXI);
            BPCFFTXI.TX_DATA.AUH_FLG = '0';
            BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            if (PNCUDFT.FEE_FLG == '0') {
                BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '1';
                BPCFFTXI.TX_DATA.CI_NO = PNCUDFT.DFT_CINO;
            } else {
                if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
                    BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '4';
                    BPCFFTXI.TX_DATA.CARD_PSBK_NO = PNCUDFT.APP_AC;
                    BPCFFTXI.TX_DATA.CI_NO = CICQACRI.O_DATA.O_CI_NO;
                } else {
                    BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '0';
                    BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = PNCUDFT.APP_AC;
                    BPCFFTXI.TX_DATA.CI_NO = CICQACRI.O_DATA.O_CI_NO;
                }
            }
            BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = PNCUDFT.ISS_CCY;
            BPCFFTXI.TX_DATA.SVR_CD = SCCGWA.COMM_AREA.SERV_CODE;
            BPCFFTXI.TX_DATA.CCY_TYPE = '1';
            CEP.TRC(SCCGWA, "NCB1015002");
            CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CI_NO);
            S000_CALL_BPZFFTXI();
            IBS.init(SCCGWA, BPCTCALF);
            BPCTCALF.INPUT_AREA.FUNC_CODE = 'C';
            BPCTCALF.INPUT_AREA.TX_CCY = PNCUDFT.ISS_CCY;
            BPCTCALF.INPUT_AREA.TX_AMT = PNCUDFT.ISS_AMT;
            BPCTCALF.INPUT_AREA.PROD_CODE = PNCUDFT.PROD_CD;
            BPCTCALF.INPUT_AREA.PROD_CODE1 = PNCUDFT.PROD_CD;
            BPCTCALF.INPUT_AREA.TX_AC = PNCUDFT.APP_AC;
            BPCTCALF.INPUT_AREA.CPN_ID = CPN_U_PNZUDFT;
            if (PNCUDFT.PAY_TYPE == 'T') {
                BPCTCALF.INPUT_AREA.TX_CI = CICQACRI.O_DATA.O_CI_NO;
            } else {
                BPCTCALF.INPUT_AREA.TX_CI = PNCUDFT.DFT_CINO;
            }
            BPCTCALF.INPUT_AREA.FREE_FMT = "01";
            BPCTCALF.INPUT_AREA.TX_CNT = 1;
            S000_CALL_BPZFCALF();
            PNCUDFT.TXN_FEE = BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].CHG_AMT;
            PNCUDFT.BOOK_FEE = BPCTCALF.OUTPUT_AREA.FEE_INFO[2-1].CHG_AMT;
            CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].CHG_AMT);
            CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[2-1].CHG_AMT);
            CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].ADJ_AMT);
            CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[2-1].ADJ_AMT);
            CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].DER_AMT);
            CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[2-1].DER_AMT);
        }
    }
    public void B220_POST_DEBIT() throws IOException,SQLException,Exception {
        if (PNCUDFT.PAY_TYPE == 'C') {
            IBS.init(SCCGWA, BPCUABOX);
            BPCUABOX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCUABOX.TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCUABOX.CCY = PNCUDFT.ISS_CCY;
            BPCUABOX.AMT = PNCUDFT.ISS_AMT;
            BPCUABOX.OPP_AC = PNCUDFT.PAYEE_AC;
            BPCUABOX.OPP_ACNM = PNCUDFT.PAYEE_NM;
            BPCUABOX.CI_NO = " ";
            S000_CALL_BPZUABOX();
        } else if (PNCUDFT.PAY_TYPE == 'T') {
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
                IBS.init(SCCGWA, AICUUPIA);
                AICUUPIA.DATA.AC_NO = PNCUDFT.APP_AC;
                AICUUPIA.DATA.CCY = PNCUDFT.ISS_CCY;
                AICUUPIA.DATA.AMT = PNCUDFT.ISS_AMT;
                AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
                AICUUPIA.DATA.RVS_NO = PNCUDFT.CREV_NO;
                AICUUPIA.DATA.PAY_MAN = PNCUDFT.APP_NM;
                AICUUPIA.DATA.EVENT_CODE = "DR";
                CEP.TRC(SCCGWA, AICUUPIA.DATA.AC_NO);
                CEP.TRC(SCCGWA, AICUUPIA.DATA.AMT);
                CEP.TRC(SCCGWA, AICUUPIA.DATA.RVS_NO);
                S000_CALL_AIZUUPIA();
            } else {
                IBS.init(SCCGWA, DDCUDRAC);
                CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
                if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
                    DDCUDRAC.CARD_NO = PNCUDFT.APP_AC;
                    DDCUDRAC.CHK_PSW_FLG = 'Y';
                    CEP.TRC(SCCGWA, PNCUDFT.TRK_DAT2);
                    CEP.TRC(SCCGWA, PNCUDFT.TRK_DAT3);
                    if (PNCUDFT.PSW.trim().length() > 0) {
                        CEP.TRC(SCCGWA, "CHK001");
                        DDCUDRAC.CHK_PSW = 'P';
                    }
                    if (PNCUDFT.TRK_DAT2.trim().length() > 0 
                        || PNCUDFT.TRK_DAT3.trim().length() > 0) {
                        CEP.TRC(SCCGWA, "CHK002");
                        DDCUDRAC.CHK_PSW = 'T';
                        if (PNCUDFT.PSW.trim().length() > 0) {
                            CEP.TRC(SCCGWA, "CHK003");
                            DDCUDRAC.CHK_PSW = 'B';
                        }
                    }
                } else {
                    DDCUDRAC.AC = PNCUDFT.APP_AC;
                    DDCUDRAC.CHK_PSW_FLG = 'N';
                }
                DDCUDRAC.PSWD = PNCUDFT.PSW;
                CEP.TRC(SCCGWA, DDCUDRAC.PSWD);
                DDCUDRAC.TRK_DATE2 = PNCUDFT.TRK_DAT2;
                DDCUDRAC.TRK_DATE3 = PNCUDFT.TRK_DAT3;
                DDCUDRAC.CCY = PNCUDFT.ISS_CCY;
                DDCUDRAC.CCY_TYPE = '1';
                DDCUDRAC.TX_AMT = PNCUDFT.ISS_AMT;
                DDCUDRAC.TX_TYPE = PNCUDFT.PAY_TYPE;
                DDCUDRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDCUDRAC.TX_MMO = "A313";
                if (PNCUDFT.APB_TYPE == '0') {
                    DDCUDRAC.TX_TYPE = 'T';
                } else {
                    DDCUDRAC.CHQ_TYPE = '4';
                    DDCUDRAC.CHQ_NO = PNCUDFT.APB_NO;
                    DDCUDRAC.CHQ_ISS_DATE = PNCUDFT.APB_VLDT;
                }
                S000_CALL_DDZUDRAC();
            }
        } else {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_PAY_MTH_NOT_EXIST);
        }
    }
    public void B230_POST_CREDIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = K_CNTR_TYPE;
        BPCPOEWA.DATA.EVENT_CODE = K_EVENT_CODE;
        if (PNCUDFT.IO_FLG == 'O') {
            BPCPOEWA.DATA.BR_OLD = 173001;
            BPCPOEWA.DATA.BR_NEW = 173001;
        } else {
            BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCPOEWA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        }
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = PNCUDFT.ISS_CCY;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = PNCUDFT.ISS_AMT;
        BPCPOEWA.DATA.PROD_CODE = PNCUDFT.PROD_CD;
        BPCPOEWA.DATA.AC_NO = PNCUDFT.DRFT_NO;
        BPCPOEWA.DATA.CI_NO = CICQACRI.O_DATA.O_CI_NO;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.CI_NO);
        S000_CALL_BPZPOEWA();
    }
    public void B240_BV_OUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFBVQU);
        if (PNCUDFT.KND.equalsIgnoreCase("C00005")) {
            BPCFBVQU.TX_DATA.KEY.CODE = K_BVCD_QGHP;
        } else {
            BPCFBVQU.TX_DATA.KEY.CODE = K_BVCD_HDHP;
        }
        S000_CALL_BPZFBVQU();
        WS_HEAD_NO = BPCFBVQU.TX_DATA.HEAD_LENGTH;
        WS_NO = BPCFBVQU.TX_DATA.NO_LENGTH;
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.HEAD_LENGTH);
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.NO_LENGTH);
        IBS.init(SCCGWA, BPCUBUSE);
        BPCUBUSE.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUBUSE.TYPE = '0';
        if (PNCUDFT.KND.equalsIgnoreCase("C00005")) {
            BPCUBUSE.BV_CODE = K_BVCD_QGHP;
        } else {
            BPCUBUSE.BV_CODE = K_BVCD_HDHP;
        }
        CEP.TRC(SCCGWA, WS_HEAD_NO);
        if (WS_HEAD_NO != 0) {
            if (PNCUDFT.DRFT_NO == null) PNCUDFT.DRFT_NO = "";
            JIBS_tmp_int = PNCUDFT.DRFT_NO.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) PNCUDFT.DRFT_NO += " ";
            BPCUBUSE.HEAD_NO = PNCUDFT.DRFT_NO.substring(0, WS_HEAD_NO);
        }
        WS_HEAD_NO = (short) (WS_HEAD_NO + 1);
        if (PNCUDFT.DRFT_NO == null) PNCUDFT.DRFT_NO = "";
        JIBS_tmp_int = PNCUDFT.DRFT_NO.length();
        for (int i=0;i<30-JIBS_tmp_int;i++) PNCUDFT.DRFT_NO += " ";
        BPCUBUSE.BEG_NO = PNCUDFT.DRFT_NO.substring(WS_HEAD_NO - 1, WS_HEAD_NO + WS_NO - 1);
        if (PNCUDFT.DRFT_NO == null) PNCUDFT.DRFT_NO = "";
        JIBS_tmp_int = PNCUDFT.DRFT_NO.length();
        for (int i=0;i<30-JIBS_tmp_int;i++) PNCUDFT.DRFT_NO += " ";
        BPCUBUSE.END_NO = PNCUDFT.DRFT_NO.substring(WS_HEAD_NO - 1, WS_HEAD_NO + WS_NO - 1);
        BPCUBUSE.NUM = 1;
        S000_CALL_BPZUBUSE();
    }
    public void B250_MST_DFT() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B251_GET_DUEDATE_PROC();
            B252_MST_DFT();
            B254_WRT_PNTDFPSW_PROC();
        } else {
            B253_MST_CANCEL_ISS();
            CEP.TRC(SCCGWA, "--CANCEL OUT--");
            B255_OUTPUT_PROC();
        }
    }
    public void B251_GET_DUEDATE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
        if (PNRPROD.PAY_TERM == ' ') SCCCLDT.MTHS = 0;
        else SCCCLDT.MTHS = Short.parseShort(""+PNRPROD.PAY_TERM);
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            WS_MSGID.WS_MSG_AP = "SC";
            WS_MSGID.WS_MSG_CODE = SCCCLDT.RC;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_MSGID);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
        WS_DATE = SCCCLDT.DATE2;
        CEP.TRC(SCCGWA, WS_DATE);
        IBS.init(SCCGWA, BPCOCLWD);
        BPCOCLWD.CAL_CODE = "CN";
        BPCOCLWD.DATE1 = WS_DATE;
        BPCOCLWD.WDAYS = 1;
        BPCOCLWD.DAYE_FLG = 'Y';
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY", BPCOCLWD);
        if (BPCOCLWD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOCLWD.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
        WS_DATE2 = BPCOCLWD.DATE2;
        CEP.TRC(SCCGWA, WS_DATE2);
    }
    public void B252_MST_DFT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNRDFT);
        PNRDFT.KEY.BILL_KND = PNCUDFT.KND;
        PNRDFT.KEY.BILL_NO = PNCUDFT.DRFT_NO;
        PNRDFT.PROD_CD = PNCUDFT.PROD_CD;
        PNRDFT.TRN_FLG = PNCUDFT.TRN_FLG;
        PNRDFT.IO_FLG = PNCUDFT.IO_FLG;
        PNRDFT.ISS_CCY = PNCUDFT.ISS_CCY;
        PNRDFT.ISS_AMT = PNCUDFT.ISS_AMT;
        PNRDFT.STS = '1';
        PNRDFT.PAY_TYPE = PNCUDFT.PAY_TYPE;
        PNRDFT.C_T_FLG = PNCUDFT.C_T_FLG;
        if (PNCUDFT.APB_TYPE == '0') {
            PNRDFT.APB_TYPE = '1';
        } else {
            PNRDFT.APB_TYPE = '2';
        }
        PNRDFT.APB_NO = PNCUDFT.APB_NO;
        PNRDFT.APB_VALUE_DATE = PNCUDFT.APB_VLDT;
        PNRDFT.APP_AC = PNCUDFT.APP_AC;
        PNRDFT.APP_NAME = PNCUDFT.APP_NM;
        PNRDFT.PAYEE_AC = PNCUDFT.PAYEE_AC;
        PNRDFT.PAYEE_NAME = PNCUDFT.PAYEE_NM;
        PNRDFT.FEE_FLG = PNCUDFT.FEE_FLG;
        PNRDFT.USG_RMK = PNCUDFT.USG_RMK;
        if (PNCUDFT.PAY_TYPE == 'T') {
            PNRDFT.LHD_AC = PNCUDFT.PAYEE_AC;
        }
        PNRDFT.ODUE_FLG = '0';
        PNRDFT.AGT_BK_NO = PNCUDFT.AGT_BR;
        CEP.TRC(SCCGWA, PNCUDFT.AGT_BR);
        CEP.TRC(SCCGWA, PNRDFT.AGT_BK_NO);
        PNRDFT.AGT_BK_NAME = PNCUDFT.AGT_NM;
        PNRDFT.PAY_BK = PNCUDFT.PAY_BK;
        PNRDFT.ISS_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        PNRDFT.ISS_DT = SCCGWA.COMM_AREA.AC_DATE;
        PNRDFT.DUE_DT = WS_DATE2;
        PNRDFT.ISS_TLR = SCCGWA.COMM_AREA.TL_ID;
        PNRDFT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        PNRDFT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        PNRDFT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        PNRDFT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_WRITE_PNTDFT();
    }
    public void B254_WRT_PNTDFPSW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNRDFPSW);
        PNRDFPSW.KEY.BILL_KND = PNCUDFT.KND;
        PNRDFPSW.KEY.BILL_NO = PNCUDFT.DRFT_NO;
        PNRDFPSW.ENCRY_NO = PNCUDFT.ENCRY_NO;
        PNRDFPSW.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        PNRDFPSW.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        PNRDFPSW.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        PNRDFPSW.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_PNTDFPSW();
    }
    public void B253_MST_CANCEL_ISS() throws IOException,SQLException,Exception {
        PNRDFT.KEY.BILL_NO = PNCUDFT.DRFT_NO;
        PNRDFT.KEY.BILL_KND = PNCUDFT.KND;
        T000_READUPD_PNTDFT();
        if (PNRDFT.STS != '1') {
            if (PNRDFT.STS == '5') {
                B253_02_FIND_CHANGE();
            } else {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_NOT_NML);
            }
        }
        if (PNRDFT.UPDTBL_DATE != SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_NOT_ALLOW_CANCEL);
        }
        if (!PNRDFT.ISS_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_CANCEL_TLR_MUST_EX);
        }
        PNRDFT.LAST_STS = PNRDFT.STS;
        PNRDFT.STS = '9';
        PNRDFT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        PNRDFT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_PNTDFT();
    }
    public void B255_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNCOTCEL);
        PNCOTCEL.KND = PNCUDFT.KND.charAt(0);
        PNCOTCEL.CC_NO = PNCUDFT.DRFT_NO;
        PNCOTCEL.STS = PNRDFT.STS;
        CEP.TRC(SCCGWA, PNCOTCEL.KND);
        CEP.TRC(SCCGWA, PNCOTCEL.CC_NO);
        CEP.TRC(SCCGWA, PNCOTCEL.STS);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT1;
        SCCFMT.DATA_PTR = PNCOTCEL;
        SCCFMT.DATA_LEN = 18;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B253_02_FIND_CHANGE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNRDFT);
        WS_DRFT_NO_CHANGE = PNCUDFT.DRFT_NO;
        WS_STS_CHANGE_FLG = 'Y';
        while (WS_STS_CHANGE_FLG != 'N') {
            CEP.TRC(SCCGWA, WS_DRFT_NO_CHANGE);
            PNRDFT.OLD_DFNO = WS_DRFT_NO_CHANGE;
            PNRDFT.KEY.BILL_KND = PNCUDFT.KND;
            T000_READ_PNTDFT_FOR_OLDNO();
            CEP.TRC(SCCGWA, PNRDFT.KEY.BILL_NO);
            CEP.TRC(SCCGWA, PNRDFT.KEY.BILL_KND);
            if (WS_TABLE_FLG == 'N') {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_BCC_NOT_FND_CHANGE);
            }
            if (PNRDFT.STS == '5') {
                WS_STS_CHANGE_FLG = 'Y';
                WS_DRFT_NO_CHANGE = PNRDFT.KEY.BILL_NO;
                CEP.TRC(SCCGWA, "#############");
            } else {
                WS_STS_CHANGE_FLG = 'N';
                CEP.TRC(SCCGWA, "&&&&&&&&&&&&&&&");
            }
        }
        if (PNRDFT.STS != '1') {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_NOT_NML);
        } else {
            CEP.TRC(SCCGWA, "REWRITE DFT CANCEL");
            CEP.TRC(SCCGWA, PNRDFT.KEY.BILL_NO);
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_BE_CHANGE_NO);
        }
    }
    public void B301_INQ_GL_MASTER() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            IBS.init(SCCGWA, BPCQCNGL);
            IBS.init(SCCGWA, BPCAOTH);
            BPCQCNGL.DAT.INPUT.CNTR_TYPE = K_CNTR_TYPE;
            BPCQCNGL.DAT.INPUT.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCQCNGL.DAT.INPUT.OTH_PTR_LEN = 10;
            BPCAOTH.PROD_CD = PNCUDFT.PROD_CD;
            BPCQCNGL.DAT.INPUT.OTH_PTR = BPCAOTH;
            S000_CALL_BPZQCNGL();
        }
    }
    public void B302_WRITE_GL_MASTER_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            IBS.init(SCCGWA, BPCUCNGM);
            BPCUCNGM.KEY.AC = PNCUDFT.DRFT_NO;
            BPCUCNGM.KEY.CNTR_TYPE = K_CNTR_TYPE;
            BPCUCNGM.PROD_TYPE = PNCUDFT.PROD_CD;
            BPCUCNGM.DATA[1-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[1-1].MSTNO;
            BPCUCNGM.DATA[2-1].GLMST = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[2-1].MSTNO;
            BPCUCNGM.FUNC = 'A';
            S000_CALL_BPZUCNGM();
        }
    }
    public void B220_BP_HISTORY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.AC = PNCUDFT.DRFT_NO;
        BPCPFHIS.DATA.ACO_AC = PNCUDFT.DRFT_NO;
        BPCPFHIS.DATA.TX_TOOL = PNCUDFT.DRFT_NO;
        BPCPFHIS.DATA.REF_NO = PNCUDFT.DRFT_NO;
        BPCPFHIS.DATA.SUMUP_FLG = '5';
        BPCPFHIS.DATA.TX_DRCR_FLG = 'C';
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        if (PNCUDFT.KND.equalsIgnoreCase("1")) {
            BPCPFHIS.DATA.BV_CODE = K_BVCD_QGHP;
        } else {
            BPCPFHIS.DATA.BV_CODE = K_BVCD_HDHP;
        }
        if (PNCUDFT.DRFT_NO == null) PNCUDFT.DRFT_NO = "";
        JIBS_tmp_int = PNCUDFT.DRFT_NO.length();
        for (int i=0;i<30-JIBS_tmp_int;i++) PNCUDFT.DRFT_NO += " ";
        BPCPFHIS.DATA.HEAD_NO = PNCUDFT.DRFT_NO.substring(0, 8);
        if (PNCUDFT.DRFT_NO == null) PNCUDFT.DRFT_NO = "";
        JIBS_tmp_int = PNCUDFT.DRFT_NO.length();
        for (int i=0;i<30-JIBS_tmp_int;i++) PNCUDFT.DRFT_NO += " ";
        BPCPFHIS.DATA.BV_NO = PNCUDFT.DRFT_NO.substring(9 - 1, 9 + 8 - 1);
        BPCPFHIS.DATA.TX_CCY = PNCUDFT.ISS_CCY;
        BPCPFHIS.DATA.TX_CCY_TYP = '1';
        BPCPFHIS.DATA.TX_TYPE = PNCUDFT.C_T_FLG;
        BPCPFHIS.DATA.TX_AMT = PNCUDFT.ISS_AMT;
        BPCPFHIS.DATA.TX_MMO = "A313";
        BPCPFHIS.DATA.PROD_CD = PNCUDFT.PROD_CD;
        BPCPFHIS.DATA.REMARK = PNCUDFT.USG_RMK;
        BPCPFHIS.DATA.OTH_AC = PNCUDFT.APP_AC;
        BPCPFHIS.DATA.CI_NO = PNCUDFT.DFT_CINO;
        S000_CALL_BPZPFHIS();
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZPFHIS, BPCPFHIS);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_WRITE_PNTDFT() throws IOException,SQLException,Exception {
        PNTDFT_RD = new DBParm();
        PNTDFT_RD.TableName = "PNTDFT";
        IBS.WRITE(SCCGWA, PNRDFT, PNTDFT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_DFT_REC_EXIST);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_DFT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
        }
    }
    public void T000_WRITE_PNTDFPSW() throws IOException,SQLException,Exception {
        PNTDFPSW_RD = new DBParm();
        PNTDFPSW_RD.TableName = "PNTDFPSW";
        IBS.WRITE(SCCGWA, PNRDFPSW, PNTDFPSW_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_FPSW_REC_EXIST);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_FPSW;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
        }
    }
    public void T000_REWRITE_PNTDFT() throws IOException,SQLException,Exception {
        PNTDFT_RD = new DBParm();
        PNTDFT_RD.TableName = "PNTDFT";
        IBS.REWRITE(SCCGWA, PNRDFT, PNTDFT_RD);
    }
    public void T000_READUPD_PNTDFT() throws IOException,SQLException,Exception {
        PNTDFT_RD = new DBParm();
        PNTDFT_RD.TableName = "PNTDFT";
        PNTDFT_RD.upd = true;
        IBS.READ(SCCGWA, PNRDFT, PNTDFT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_DFT_REC_NOT_EXIST);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_DFT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_READ_PNTDFT_FOR_OLDNO() throws IOException,SQLException,Exception {
        PNTDFT_RD = new DBParm();
        PNTDFT_RD.TableName = "PNTDFT";
        PNTDFT_RD.where = "OLD_DFNO = :PNRDFT.OLD_DFNO "
            + "AND BILL_KND = :PNRDFT.KEY.BILL_KND";
        PNTDFT_RD.upd = true;
        IBS.READ(SCCGWA, PNRDFT, this, PNTDFT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_DFT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_READ_PNTDFT() throws IOException,SQLException,Exception {
        PNTDFT_RD = new DBParm();
        PNTDFT_RD.TableName = "PNTDFT";
        PNTDFT_RD.where = "BILL_NO = :PNRDFT.KEY.BILL_NO";
        IBS.READ(SCCGWA, PNRDFT, this, PNTDFT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_DFT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZPOEWA, BPCPOEWA);
    }
    public void S000_CALL_BPZUBUSE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZUBUSE, BPCUBUSE);
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZFBVQU, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_DDZUDRAC, DDCUDRAC);
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        CEP.TRC(SCCGWA, AICUUPIA.RC.RC_CODE);
        if (AICUUPIA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_BPZFFTXI, BPCFFTXI);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZFCALF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_BPZFCALF, BPCTCALF);
        CEP.TRC(SCCGWA, BPCTCALF.RC);
        if (BPCTCALF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTCALF.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CISOACCU_FIRST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_CIACCU, CICACCU);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
        if (CICACCU.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase("CI3011")) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CISOACCU_SECOND() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_CIACCU, CICACCU);
        if (CICACCU.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
    }
    public void S000_CALL_BPZUABOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_ADD_CBOX, BPCUABOX);
        if (BPCUABOX.RTNCODE != 0) {
            CEP.ERR(SCCGWA, BPCUABOX.RTNCODE);
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "CALL CPN-BPZPRMM");
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZUCNGM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZUCNGM, BPCUCNGM);
        CEP.TRC(SCCGWA, BPCUCNGM.RC);
        if (BPCUCNGM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUCNGM.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZQCNGL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZQCNGL, BPCQCNGL);
        CEP.TRC(SCCGWA, BPCQCNGL.RC);
        if (BPCQCNGL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCNGL.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
