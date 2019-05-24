package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;
import com.hisun.DD.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZUCCHA {
    String JIBS_tmp_str[] = new String[10];
    DBParm DCTCDDAT_RD;
    brParm DCTCDDAT_BR = new brParm();
    DBParm DCTCDORD_RD;
    brParm DCTCRDLT_BR = new brParm();
    DBParm DCTCRDLT_RD;
    DBParm DCTACLNK_RD;
    boolean pgmRtn = false;
    String BSL_RTC_FLAG = "SIT_GN_20150519_V1";
    String CPN_DCZUCHDC = "DC-U-CHG-CARD";
    String CPN_DCZUCACJ = "DC-U-CARD-AC-JOIN";
    String CPN_CIZSSCH = "CI-SEARCH-CI";
    String CPN_CIZMACR = "CI-MAIN-ACR";
    String CPN_CIZCHDC = "CI-CHANGE-DC";
    String CPN_DCZUCINF = "DC-U-CARD-INF";
    String K_TBL_CRDLT = "DCTCRDLT";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    long WS_CARD_SEQNO = 0;
    String WS_OLD_CARD_PROD = " ";
    String WS_NEW_CARD_PROD = " ";
    int WS_NEW_CARD_CRT_DT = 0;
    String WS_CIFNO = " ";
    String WS_QURY_PIN_DAT = " ";
    String WS_TRAN_PIN_DAT = " ";
    String WS_SOCIAL_CARD_NO = " ";
    String WS_F_CARD_NO = " ";
    char WS_CARD_TYPE = ' ';
    char WS_NEW_CARD_EVN_FLG = ' ';
    String WS_NEW_CARD_PSW = " ";
    char WS_CARD_PHY_TYP = ' ';
    String WS_CRDLT_CARD_NO = " ";
    int WS_NUM = 0;
    char WS_LMT_FLG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRCDORD DCRCDORD = new DCRCDORD();
    DCRCRDLT DCRCRDLT = new DCRCRDLT();
    DCRACLNK DCRACLNK = new DCRACLNK();
    CICCUST CICCUST = new CICCUST();
    DCCPQPRD DCCDQPRD = new DCCPQPRD();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    CICSSCH CICSSCH = new CICSSCH();
    CICMACR CICMACR = new CICMACR();
    CICCHDC CICCHDC = new CICCHDC();
    DCCUCINF DCCUCINF = new DCCUCINF();
    DDCIPSBK DDCIPSBK = new DDCIPSBK();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCUCCHA DCCUCCHA;
    public void MP(SCCGWA SCCGWA, DCCUCCHA DCCUCCHA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUCCHA = DCCUCCHA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZUCCHA return!");
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
        B020_CARD_CHECK();
        if (pgmRtn) return;
        B030_CARD_STATUS();
        if (pgmRtn) return;
        if (DCCUCCHA.OLD_CARD_NO.trim().length() > 0) {
            if (!DCCUCCHA.OLD_CARD_NO.equalsIgnoreCase(DCCUCCHA.NEW_CARD_NO)) {
                B060_CARD_LMT_MIGTN();
                if (pgmRtn) return;
            }
            B070_CARD_CI_LOG();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCUCCHA.OLD_CARD_NO);
        CEP.TRC(SCCGWA, DCCUCCHA.NEW_CARD_NO);
        if (DCCUCCHA.OLD_CARD_NO.trim().length() == 0 
            || DCCUCCHA.NEW_CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO, DCCUCCHA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCCHA.NEW_CARD_TYPE == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_TYPE;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_TYPE, DCCUCCHA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCCHA.NEW_CARD_TYPE == '2') {
            if (DCCUCCHA.CARD_MD.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_MD;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_MD, DCCUCCHA.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_CARD_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCUCCHA.OLD_CARD_NO;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        WS_CIFNO = DCRCDDAT.CARD_OWN_CINO;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST, DCCUCCHA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_OLD_CARD_PROD = DCRCDDAT.PROD_CD;
        IBS.init(SCCGWA, CICCUST);
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_OWN_CINO);
        CICCUST.FUNC = 'C';
        CICCUST.DATA.CI_NO = DCRCDDAT.CARD_OWN_CINO;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DCRCDORD);
        DCRCDORD.KEY.CARD_NO = DCCUCCHA.NEW_CARD_NO;
        DCRCDORD.KEY.EXC_CARD_TMS = 0;
        T000_READ_DCTCDORD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_IN_ORDER;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_IN_ORDER, DCCUCCHA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_NEW_CARD_PROD = DCRCDORD.CARD_PROD;
        WS_NEW_CARD_CRT_DT = DCRCDORD.CRT_DT;
        CEP.TRC(SCCGWA, DCCUCCHA.NEW_CARD_NO);
        CEP.TRC(SCCGWA, DCRCDORD.CARD_PROD);
        CEP.TRC(SCCGWA, WS_OLD_CARD_PROD);
        CEP.TRC(SCCGWA, DCRCDORD.TRAN_PIN_DAT);
    }
    public void B030_CARD_STATUS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCUCCHA.OLD_CARD_NO;
        T000_READUPD_DCTCDDAT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DCCUCINF);
        DCCUCINF.CARD_NO = DCCUCCHA.NEW_CARD_NO;
        S000_CALL_DCZUCINF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCUCINF.CARD_PHY_TYP);
        WS_CARD_PHY_TYP = DCCUCINF.CARD_PHY_TYP;
        DCRCDDAT.CARD_MEDI = '3';
        if (DCCUCINF.SVR_RSC_CD.equalsIgnoreCase("220")) {
            DCRCDDAT.CARD_MEDI = '3';
        } else {
            if (DCCUCINF.SVR_RSC_CD.equalsIgnoreCase("120")) {
                DCRCDDAT.CARD_MEDI = '1';
            }
        }
        DCRCDDAT.KEY.CARD_NO = DCCUCCHA.NEW_CARD_NO;
        DCRCDDAT.EXC_CARD_TMS = 0;
        DCRCDDAT.PROD_CD = WS_NEW_CARD_PROD;
        CEP.TRC(SCCGWA, DCRCDDAT.PROD_CD);
        if (DCCUCCHA.NEW_CARD_TYPE == '1') {
            if (WS_NEW_CARD_EVN_FLG == 'Y') {
                DCRCDDAT.CARD_STS = 'N';
                DCRCDDAT.TRAN_PIN_DAT = WS_NEW_CARD_PSW;
            } else {
                DCRCDDAT.CARD_STS = '2';
            }
        }
        if (DCCUCCHA.NEW_CARD_TYPE == '2') {
            DCRCDDAT.CARD_STS = '0';
            if (WS_CARD_PHY_TYP == 'D') {
                DCRCDDAT.CARD_STS = '2';
            }
        }
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_STS);
        DCRCDDAT.CARD_STSW = "ZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZERO";
        DCRCDDAT.LAST_TXN_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRCDDAT.ISSU_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        if (DCCUCCHA.NEW_CARD_TYPE == '1') {
            DCRCDDAT.CLT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        }
        DCRCDDAT.ISSU_DT = SCCGWA.COMM_AREA.AC_DATE;
        if (DCCUCCHA.NEW_CARD_TYPE == '1') {
            DCRCDDAT.EMBS_DT = WS_NEW_CARD_CRT_DT;
        }
        if (DCCUCCHA.NEW_CARD_TYPE == '2') {
            DCRCDDAT.EMBS_DT = 0;
        }
        DCRCDDAT.TRAN_PIN_DAT = " ";
        DCRCDDAT.QURY_PIN_DAT = " ";
        if (DCCUCCHA.NEW_CARD_TYPE == '1') {
            CEP.TRC(SCCGWA, DCCUCINF.DFT_EXP);
            if (DCCUCINF.DFT_EXP == 999) {
                DCRCDDAT.EXP_DT = 99991231;
            } else {
                if (DCCUCINF.DFT_EXP != 0) {
                    IBS.init(SCCGWA, SCCCLDT);
                    SCCCLDT.DATE1 = WS_NEW_CARD_CRT_DT;
                    SCCCLDT.MTHS = DCCUCINF.DFT_EXP;
                    CEP.TRC(SCCGWA, SCCCLDT);
                    SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
                    SCSSCLDT1.MP(SCCGWA, SCCCLDT);
                    CEP.TRC(SCCGWA, SCCCLDT.RC);
                    if (SCCCLDT.RC != 0) {
                        WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_INPUT_ERR;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    CEP.TRC(SCCGWA, SCCCLDT.DATE2);
                    DCRCDDAT.EXP_DT = SCCCLDT.DATE2;
                    CEP.TRC(SCCGWA, DCRCDDAT.EXP_DT);
                }
            }
        }
        if (DCCUCCHA.NEW_CARD_TYPE == '2') {
            if (DCRCDDAT.CARD_STS == '0') {
                DCRCDDAT.EXP_DT = 0;
            }
        }
        DCRCDDAT.CLO_DT = 0;
        DCRCDDAT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_ADD_DCTCDDAT();
        if (pgmRtn) return;
    }
    public void B040_PASSBOOK_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIPSBK);
        DDCIPSBK.FUNC = 'Z';
        DDCIPSBK.CARD_NO = DCCUCCHA.OLD_CARD_NO;
        S000_CALL_DDZIPSBK();
        if (pgmRtn) return;
    }
    public void B080_NEW_CARD_VA_JOIN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRACLNK);
        DCRACLNK.KEY.CARD_NO = DCCUCCHA.OLD_CARD_NO;
        T000_READ_DCTACLNK();
        if (pgmRtn) return;
        DCRACLNK.KEY.CARD_NO = DCCUCCHA.NEW_CARD_NO;
        DCRACLNK.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRACLNK.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRACLNK.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRACLNK.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DCTACLNK();
        if (pgmRtn) return;
    }
    public void B060_CARD_LMT_MIGTN() throws IOException,SQLException,Exception {
        WS_LMT_FLG = 'N';
        IBS.init(SCCGWA, DCRCRDLT);
        WS_CRDLT_CARD_NO = DCCUCCHA.OLD_CARD_NO;
        T000_STARTBR_DCTCRDLT();
        if (pgmRtn) return;
        T000_READNEXT_DCTCRDLT();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            WS_LMT_FLG = 'Y';
            DCRCRDLT.KEY.CARD_NO = DCCUCCHA.NEW_CARD_NO;
            DCRCRDLT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRCRDLT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            DCRCRDLT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRCRDLT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_WRITE_DCTCRDLT();
            if (pgmRtn) return;
            T000_READNEXT_DCTCRDLT();
            if (pgmRtn) return;
        }
        T000_ENDBR_DCTCRDLT();
        if (pgmRtn) return;
    }
    public void B070_CARD_CI_LOG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICMACR);
        CICMACR.FUNC = '1';
        CICMACR.DATA.ENTY_TYP = '2';
        CICMACR.DATA.AGR_NO = DCCUCCHA.NEW_CARD_NO;
        CICMACR.DATA.CI_NO = WS_CIFNO;
        CICMACR.DATA.PROD_CD = WS_NEW_CARD_PROD;
        CICMACR.DATA.FRM_APP = "DC";
        CICMACR.DATA.SHOW_FLG = 'Y';
        CICMACR.DATA.CNTRCT_TYP = "CARD";
        CICMACR.DATA.OPN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICMACR.DATA.BBR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICMACR.DATA.OPEN_DT = SCCGWA.COMM_AREA.AC_DATE;
    }
    public void S000_CALL_CIZSSCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CIZSSCH, CICSSCH);
        CEP.TRC(SCCGWA, CICSSCH.RC.RC_CODE);
        if (CICSSCH.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICSSCH.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICSSCH.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUCCHA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCHDC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CIZCHDC, CICCHDC);
        if (CICCHDC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCHDC.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCHDC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUCCHA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIPSBK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-PSBK-PROC", DDCIPSBK);
    }
    public void S000_CALL_CIZMACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CIZMACR, CICMACR);
        if (CICMACR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICMACR.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICMACR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUCCHA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void T000_READ_DCTCDDAT_S() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CARD_TYPE = 'S';
            WS_F_CARD_NO = DCRCDDAT.KEY.CARD_NO;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDDAT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READUPD_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void T000_READUPD_DCTCDDAT_S() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void T000_REWRITE_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.REWRITE(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void T000_UPDATE_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.REWRITE(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void T000_ADD_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.WRITE(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_USED_CARD;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_USED_CARD, DCCUCCHA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDDAT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_BR.rp = new DBParm();
        DCTCDDAT_BR.rp.TableName = "DCTCDDAT";
        DCTCDDAT_BR.rp.where = "PRIM_CARD_NO = :DCRCDDAT.PRIM_CARD_NO";
        IBS.STARTBR(SCCGWA, DCRCDDAT, this, DCTCDDAT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0'
            || SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDDAT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_DCTCDDAT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRCDDAT, this, DCTCDDAT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0'
            || SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDDAT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_DCTCDDAT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTCDDAT_BR);
    }
    public void T000_GROUP_DCTCDDAT_P() throws IOException,SQLException,Exception {
        null.set = "WS-NUM=COUNT(*)";
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.where = "PRIM_CARD_NO = :DCRCDDAT.PRIM_CARD_NO";
        IBS.GROUP(SCCGWA, DCRCDDAT, this, DCTCDDAT_RD);
    }
    public void T000_READ_DCTCDORD() throws IOException,SQLException,Exception {
        DCTCDORD_RD = new DBParm();
        DCTCDORD_RD.TableName = "DCTCDORD";
        IBS.READ(SCCGWA, DCRCDORD, DCTCDORD_RD);
    }
    public void T000_STARTBR_DCTCRDLT() throws IOException,SQLException,Exception {
        DCTCRDLT_BR.rp = new DBParm();
        DCTCRDLT_BR.rp.TableName = "DCTCRDLT";
        DCTCRDLT_BR.rp.where = "CARD_NO = :WS_CRDLT_CARD_NO";
        DCTCRDLT_BR.rp.order = "REGN_TYP, CHNL_NO, TXN_TYPE, LMT_CCY";
        IBS.STARTBR(SCCGWA, DCRCRDLT, this, DCTCRDLT_BR);
    }
    public void T000_READNEXT_DCTCRDLT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRCRDLT, this, DCTCRDLT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0' 
            && SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_CRDLT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_DCTCRDLT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTCRDLT_BR);
    }
    public void T000_WRITE_DCTCRDLT() throws IOException,SQLException,Exception {
        DCTCRDLT_RD = new DBParm();
        DCTCRDLT_RD.TableName = "DCTCRDLT";
        IBS.WRITE(SCCGWA, DCRCRDLT, DCTCRDLT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_CRDLT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_DELETE_DCTCRDLT() throws IOException,SQLException,Exception {
        DCTCRDLT_RD = new DBParm();
        DCTCRDLT_RD.TableName = "DCTCRDLT";
        DCTCRDLT_RD.where = "CARD_NO = :WS_CRDLT_CARD_NO";
        IBS.DELETE(SCCGWA, DCRCRDLT, this, DCTCRDLT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0' 
            && SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_CRDLT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "DELETE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DCZUCINF, DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUCCHA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTACLNK() throws IOException,SQLException,Exception {
        DCTACLNK_RD = new DBParm();
        DCTACLNK_RD.TableName = "DCTACLNK";
        DCTACLNK_RD.col = "CARD_NO, VIA_AC, CARD_AC_STATUS, CARD_TYPE, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        IBS.READ(SCCGWA, DCRACLNK, DCTACLNK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_VIA_AC_NOTFND;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_VIA_AC_NOTFND, DCCUCCHA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTACLNK";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_DCTACLNK() throws IOException,SQLException,Exception {
        DCTACLNK_RD = new DBParm();
        DCTACLNK_RD.TableName = "DCTACLNK";
        IBS.WRITE(SCCGWA, DCRACLNK, DCTACLNK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_USED_CARD;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_USED_CARD, DCCUCCHA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTACLNK";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CISOCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUCCHA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DCCUCCHA.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DCCUCCHA=");
            CEP.TRC(SCCGWA, DCCUCCHA);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
