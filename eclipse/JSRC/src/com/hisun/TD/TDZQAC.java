package com.hisun.TD;

import com.hisun.CI.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;
import com.hisun.DD.*;
import com.hisun.GD.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class TDZQAC {
    String JIBS_tmp_str[] = new String[10];
    DBParm DDTMST_RD;
    int JIBS_tmp_int;
    brParm TDTSMST_BR = new brParm();
    DBParm TDTCMST_RD;
    DBParm TDTBVT_RD;
    brParm GDTSTAC_BR = new brParm();
    boolean pgmRtn = false;
    String K_OUTPUT_FMTP = "TD225";
    String K_OUTPUT_FMTC = "TD226";
    String K_OUTPUT_FMTG = "TD227";
    int K_OUTPUT_ROW = 5;
    int K_MAX_ROW = 5;
    int K_MAX_COL = 0;
    int K_COL_CNT = 0;
    String PGM_SCSSCLDT = "SCSSCLDT";
    TDZQAC_WS_OUT_DAT WS_OUT_DAT = new TDZQAC_WS_OUT_DAT();
    TDZQAC_WS_CIAC_D WS_CIAC_D = new TDZQAC_WS_CIAC_D();
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_K = 0;
    int WS_T = 0;
    int WS_TT = 0;
    int WS_CNT = 0;
    char WS_STAC_FLG = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCSUBS SCCSUBS = new SCCSUBS();
    CICQCIAC CICQCIAC = new CICQCIAC();
    TDRBVT TDRBVT = new TDRBVT();
    TDRCMST TDRCMST = new TDRCMST();
    TDRSMST TDRSMST = new TDRSMST();
    DDRMST DDRMST = new DDRMST();
    CICQACRI CICQACRI = new CICQACRI();
    CICACCU CICACCU = new CICACCU();
    CICSAGEN CICSAGEN = new CICSAGEN();
    SCCCLDT SCCCLDT = new SCCCLDT();
    GDRSTAC GDRSTAC = new GDRSTAC();
    SCCGWA SCCGWA;
    TDCQAC TDCQAC;
    public void MP(SCCGWA SCCGWA, TDCQAC TDCQAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCQAC = TDCQAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZQAC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRBVT);
        IBS.init(SCCGWA, TDRSMST);
        IBS.init(SCCGWA, TDRCMST);
        IBS.init(SCCGWA, CICQACRI);
        IBS.init(SCCGWA, CICACCU);
        IBS.init(SCCGWA, CICQCIAC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_PROC();
        if (pgmRtn) return;
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
        B200_GET_MSG_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCQAC.CI_NO);
        CEP.TRC(SCCGWA, TDCQAC.AC_NO);
        CEP.TRC(SCCGWA, TDCQAC.CI_TYP);
        CEP.TRC(SCCGWA, TDCQAC.STS);
        CEP.TRC(SCCGWA, TDCQAC.SRCH_FLG);
        CEP.TRC(SCCGWA, TDCQAC.BV_TYP);
        CEP.TRC(SCCGWA, TDCQAC.CCY);
        if (TDCQAC.CI_NO.trim().length() == 0 
            && TDCQAC.AC_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_CI_M_I);
        }
    }
    public void B200_GET_MSG_PROC() throws IOException,SQLException,Exception {
        if (TDCQAC.AC_NO.trim().length() > 0) {
            if (TDCQAC.SRCH_FLG != '8') {
                B010_READ_TDTCMST_AC_NO();
                if (pgmRtn) return;
            } else {
                B045_GET_GD_BYAC_MSG();
                if (pgmRtn) return;
            }
        } else {
            IBS.init(SCCGWA, CICQCIAC);
            while (CICQCIAC.DATA.LAST_FLG != 'Y') {
                CICQCIAC.DATA.CI_NO = TDCQAC.CI_NO;
                CICQCIAC.FUNC = '5';
                S000_CALL_CIZQCIAC();
                if (pgmRtn) return;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQCIAC.DATA.ACR_AREA);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_CIAC_D);
                CEP.TRC(SCCGWA, TDCQAC.CI_TYP);
                CEP.TRC(SCCGWA, TDCQAC.SRCH_FLG);
                if (TDCQAC.CI_TYP == '1') {
                    B020_GET_P_MSG();
                    if (pgmRtn) return;
                } else {
                    if ((TDCQAC.CI_TYP == '2' 
                        || TDCQAC.CI_TYP == '3') 
                        && (TDCQAC.SRCH_FLG != '6')) {
                        B030_GET_C_MSG();
                        if (pgmRtn) return;
                    } else {
                        if ((TDCQAC.CI_TYP == '2' 
                            || TDCQAC.CI_TYP == '3') 
                            && TDCQAC.SRCH_FLG == '6') {
                            B040_GET_G_MSG();
                            if (pgmRtn) return;
                        } else {
                            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CI_TYP_INPUT_ERR);
                        }
                    }
                }
            }
        }
    }
    public void B010_READ_TDTCMST_AC_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSAGEN);
        CICSAGEN.OUT_AC = TDCQAC.AC_NO;
        CICSAGEN.AGENT_TP = "3";
        CICSAGEN.FUNC = 'S';
        S000_CALL_CIZSAGEN();
        if (pgmRtn) return;
        WS_OUT_DAT.WS_AGC_FLG = CICSAGEN.FOUND_FLG;
        if (TDCQAC.SRCH_FLG == '6' 
            || TDCQAC.SRCH_FLG == '8') {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = TDCQAC.AC_NO;
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CNTRCT_TYP);
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
            if (!CICQACRI.O_DATA.O_CNTRCT_TYP.equalsIgnoreCase("033") 
                && !CICQACRI.O_DATA.O_CNTRCT_TYP.equalsIgnoreCase("048")) {
            } else {
                IBS.init(SCCGWA, CICACCU);
                CICACCU.DATA.AGR_NO = TDCQAC.AC_NO;
                S000_CALL_CIZACCU();
                if (pgmRtn) return;
                WS_OUT_DAT.WS_AC_NO = TDCQAC.AC_NO;
                WS_OUT_DAT.WS_AC_CNM = CICACCU.DATA.AC_CNM;
                WS_OUT_DAT.WS_CI_NO = CICACCU.DATA.CI_NO;
                WS_OUT_DAT.WS_CI_TYP = CICACCU.DATA.CI_TYP;
                WS_OUT_DAT.WS_CCY = CICACCU.DATA.CCY;
                if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("TD")) {
                    WS_OUT_DAT.WS_GD_AC_T = '2';
                    IBS.init(SCCGWA, TDRCMST);
                    TDRCMST.KEY.AC_NO = TDCQAC.AC_NO;
                    T000_READ_TDTCMST();
                    if (pgmRtn) return;
                    WS_OUT_DAT.WS_AC_NO = TDRCMST.KEY.AC_NO;
                    WS_OUT_DAT.WS_STS = TDRCMST.STS;
                    WS_OUT_DAT.WS_OPEN_DATE = TDRCMST.OPEN_DATE;
                    WS_OUT_DAT.WS_OWNER_BR = TDRCMST.OWNER_BR;
                    WS_OUT_DAT.WS_OPEN_TLR = TDRCMST.OPEN_TLR;
                    if (TDCQAC.SRCH_FLG == '8') {
                        WS_OUT_DAT.WS_AC_SEQ = GDRSTAC.KEY.AC_SEQ;
                    }
                    CEP.TRC(SCCGWA, WS_OUT_DAT.WS_GD_AC_T);
                    R100_OUT_PROC();
                    if (pgmRtn) return;
                } else {
                    IBS.init(SCCGWA, TDRSMST);
                    IBS.init(SCCGWA, TDRCMST);
                    TDRSMST.AC_NO = TDCQAC.AC_NO;
                    if (TDCQAC.STS == ' ') {
                        TDRSMST.ACO_STS = '0';
                    } else {
                        TDRSMST.ACO_STS = TDCQAC.STS;
                    }
                    T000_STARTBR_TDTSMST_GD();
                    if (pgmRtn) return;
                    R000_READNEXT_TDTSMST_GD();
                    if (pgmRtn) return;
                    IBS.init(SCCGWA, DDRMST);
                    DDRMST.KEY.CUS_AC = TDCQAC.AC_NO;
                    DDTMST_RD = new DBParm();
                    DDTMST_RD.TableName = "DDTMST";
                    DDTMST_RD.eqWhere = "CUS_AC";
                    IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
                    WS_OUT_DAT.WS_STS = DDRMST.AC_STS;
                    WS_OUT_DAT.WS_OPEN_DATE = DDRMST.OPEN_DATE;
                    WS_OUT_DAT.WS_OWNER_BR = DDRMST.OWNER_BR;
                    CEP.TRC(SCCGWA, WS_OUT_DAT.WS_GD_AC_T);
                    R100_OUT_PROC();
                    if (pgmRtn) return;
                }
            }
        } else {
            IBS.init(SCCGWA, TDRCMST);
            TDRCMST.KEY.AC_NO = TDCQAC.AC_NO;
            CEP.TRC(SCCGWA, TDCQAC.SRCH_FLG);
            if (TDCQAC.SRCH_FLG == '1') {
                T000_READ_TDTCMST_NOR();
                if (pgmRtn) return;
            } else {
                if (TDCQAC.SRCH_FLG == '2') {
                    T000_READ_TDTCMST_YBT();
                    if (pgmRtn) return;
                } else {
                    if (TDCQAC.SRCH_FLG != '6') {
                        T000_READ_TDTCMST();
                        if (pgmRtn) return;
                    } else {
                    }
                }
            }
            CEP.TRC(SCCGWA, TDRCMST.CI_TYP);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            } else {
                CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
                if (TDRCMST.CI_TYP == '1' 
                    && SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                    WS_OUT_DAT.WS_AC_NO = TDRCMST.KEY.AC_NO;
                    WS_OUT_DAT.WS_BV_TYP = TDRCMST.BV_TYP;
                    WS_OUT_DAT.WS_STS = TDRCMST.STS;
                    WS_OUT_DAT.WS_CI_TYP = TDRCMST.CI_TYP;
                    WS_OUT_DAT.WS_CCY = TDRCMST.CCY;
                    WS_OUT_DAT.WS_DRAW_MTH = TDRCMST.DRAW_MTH;
                    WS_OUT_DAT.WS_CROS_CR_FLG = TDRCMST.CROS_CR_FLG;
                    WS_OUT_DAT.WS_CROS_DR_FLG = TDRCMST.CROS_DR_FLG;
                    WS_OUT_DAT.WS_OPEN_DATE = TDRCMST.OPEN_DATE;
                    WS_OUT_DAT.WS_OWNER_BR = TDRCMST.OWNER_BR;
                    WS_OUT_DAT.WS_OPEN_TLR = TDRCMST.OPEN_TLR;
                    if (TDRCMST.STSW == null) TDRCMST.STSW = "";
                    JIBS_tmp_int = TDRCMST.STSW.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
                    if (TDRCMST.STSW.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
                        WS_OUT_DAT.WS_PSW_STS = '3';
                    } else {
                        if (TDRCMST.STSW == null) TDRCMST.STSW = "";
                        JIBS_tmp_int = TDRCMST.STSW.length();
                        for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
                        if (TDRCMST.STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                            WS_OUT_DAT.WS_PSW_STS = '1';
                        } else {
                            WS_OUT_DAT.WS_PSW_STS = '0';
                        }
                    }
                    IBS.init(SCCGWA, CICACCU);
                    CICACCU.DATA.AGR_NO = TDRCMST.KEY.AC_NO;
                    S000_CALL_CIZACCU();
                    if (pgmRtn) return;
                    WS_OUT_DAT.WS_CI_NO = CICACCU.DATA.CI_NO;
                    IBS.init(SCCGWA, TDRBVT);
                    TDRBVT.KEY.AC_NO = TDRCMST.KEY.AC_NO;
                    T000_READ_TDTBVT();
                    if (pgmRtn) return;
                    WS_OUT_DAT.WS_BV_NO = TDRBVT.BV_NO;
                    if (TDRBVT.STSW == null) TDRBVT.STSW = "";
                    JIBS_tmp_int = TDRBVT.STSW.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
                    if (TDRBVT.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                        WS_OUT_DAT.WS_LOS_FLG = '2';
                    } else {
                        if (TDRBVT.STSW == null) TDRBVT.STSW = "";
                        JIBS_tmp_int = TDRBVT.STSW.length();
                        for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
                        if (TDRBVT.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                            WS_OUT_DAT.WS_LOS_FLG = '1';
                        } else {
                            WS_OUT_DAT.WS_LOS_FLG = '0';
                        }
                    }
                    R100_OUT_PROC();
                    if (pgmRtn) return;
                } else {
                    if (TDCQAC.CI_TYP == '2' 
                        || TDCQAC.CI_TYP == '3' 
                        && TDCQAC.SRCH_FLG != '6') {
                        WS_OUT_DAT.WS_AC_NO = TDRCMST.KEY.AC_NO;
                        WS_OUT_DAT.WS_STS = TDRCMST.STS;
                        WS_OUT_DAT.WS_CI_TYP = TDRCMST.CI_TYP;
                        WS_OUT_DAT.WS_CCY = TDRCMST.CCY;
                        WS_OUT_DAT.WS_DRAW_MTH = TDRCMST.DRAW_MTH;
                        WS_OUT_DAT.WS_CROS_CR_FLG = TDRCMST.CROS_CR_FLG;
                        WS_OUT_DAT.WS_CROS_DR_FLG = TDRCMST.CROS_DR_FLG;
                        WS_OUT_DAT.WS_OPEN_DATE = TDRCMST.OPEN_DATE;
                        WS_OUT_DAT.WS_OWNER_BR = TDRCMST.OWNER_BR;
                        WS_OUT_DAT.WS_OPEN_TLR = TDRCMST.OPEN_TLR;
                        IBS.init(SCCGWA, CICQACRI);
                        CICQACRI.DATA.AGR_NO = TDCQAC.AC_NO;
                        CICQACRI.FUNC = 'A';
                        S000_CALL_CIZQACRI();
                        if (pgmRtn) return;
                        WS_OUT_DAT.WS_AC_CNM = CICQACRI.O_DATA.O_AC_CNM;
                        IBS.init(SCCGWA, CICACCU);
                        CICACCU.DATA.AGR_NO = TDCQAC.AC_NO;
                        S000_CALL_CIZACCU();
                        if (pgmRtn) return;
                        WS_OUT_DAT.WS_CI_CNM = CICACCU.DATA.CI_CNM;
                        WS_OUT_DAT.WS_ID_TYP = CICACCU.DATA.ID_TYPE;
                        WS_OUT_DAT.WS_ID_NO = CICACCU.DATA.ID_NO;
                        WS_OUT_DAT.WS_CI_NO = CICACCU.DATA.CI_NO;
                        R100_OUT_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
        }
    }
    public void B020_GET_P_MSG() throws IOException,SQLException,Exception {
        WS_I = 1;
        while (WS_I < 100 
            && WS_CIAC_D.WS_CIAC_DATE[WS_I-1].WS_CIAC_NO.trim().length() != 0) {
            if (TDCQAC.SRCH_FLG == '1' 
                || TDCQAC.SRCH_FLG == '2') {
                IBS.init(SCCGWA, TDRSMST);
                if (TDCQAC.STS != ' ') {
                    TDRSMST.ACO_STS = TDCQAC.STS;
                } else {
                    TDRSMST.ACO_STS = '0';
                }
                TDRSMST.AC_NO = WS_CIAC_D.WS_CIAC_DATE[WS_I-1].WS_CIAC_NO;
                R000_GET_NOR();
                if (pgmRtn) return;
            } else {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_SRCH_FLG_ERR);
            }
            WS_I += 1;
        }
        if (WS_CIAC_D.WS_CIAC_DATE[WS_I-1].WS_CIAC_NO.trim().length() > 0) {
            if (TDCQAC.SRCH_FLG == '1' 
                || TDCQAC.SRCH_FLG == '2') {
                IBS.init(SCCGWA, TDRSMST);
                if (TDCQAC.STS != ' ') {
                    TDRSMST.ACO_STS = TDCQAC.STS;
                } else {
                    TDRSMST.ACO_STS = '0';
                }
                TDRSMST.AC_NO = WS_CIAC_D.WS_CIAC_DATE[WS_I-1].WS_CIAC_NO;
                R000_GET_NOR();
                if (pgmRtn) return;
            } else {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_SRCH_FLG_ERR);
            }
        }
    }
    public void B030_GET_C_MSG() throws IOException,SQLException,Exception {
        WS_I = 1;
        while (WS_I < 100 
            && WS_CIAC_D.WS_CIAC_DATE[WS_I-1].WS_CIAC_NO.trim().length() != 0) {
            IBS.init(SCCGWA, TDRSMST);
            if (TDCQAC.STS != ' ') {
                TDRSMST.ACO_STS = TDCQAC.STS;
            } else {
                TDRSMST.ACO_STS = '0';
            }
            TDRSMST.AC_NO = WS_CIAC_D.WS_CIAC_DATE[WS_I-1].WS_CIAC_NO;
            R000_GET_COM();
            if (pgmRtn) return;
            WS_I += 1;
        }
        if (WS_CIAC_D.WS_CIAC_DATE[WS_I-1].WS_CIAC_NO.trim().length() > 0) {
            IBS.init(SCCGWA, TDRSMST);
            if (TDCQAC.STS != ' ') {
                TDRSMST.ACO_STS = TDCQAC.STS;
            } else {
                TDRSMST.ACO_STS = '0';
            }
            TDRSMST.AC_NO = WS_CIAC_D.WS_CIAC_DATE[WS_I-1].WS_CIAC_NO;
            R000_GET_COM();
            if (pgmRtn) return;
        }
    }
    public void B040_GET_G_MSG() throws IOException,SQLException,Exception {
        WS_I = 1;
        while (WS_I < 100 
            && WS_CIAC_D.WS_CIAC_DATE[WS_I-1].WS_CIAC_NO.trim().length() != 0) {
            CEP.TRC(SCCGWA, WS_CIAC_D.WS_CIAC_DATE[WS_I-1].WS_CIAC_NO);
            TDCQAC.AC_NO = WS_CIAC_D.WS_CIAC_DATE[WS_I-1].WS_CIAC_NO;
            B010_READ_TDTCMST_AC_NO();
            if (pgmRtn) return;
            WS_I += 1;
        }
        if (WS_CIAC_D.WS_CIAC_DATE[WS_I-1].WS_CIAC_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, WS_CIAC_D.WS_CIAC_DATE[WS_I-1].WS_CIAC_NO);
            TDCQAC.AC_NO = WS_CIAC_D.WS_CIAC_DATE[WS_I-1].WS_CIAC_NO;
            B010_READ_TDTCMST_AC_NO();
            if (pgmRtn) return;
        }
    }
    public void B045_GET_GD_BYAC_MSG() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCQAC.AC_NO);
        IBS.init(SCCGWA, GDRSTAC);
        GDRSTAC.ST_AC = TDCQAC.AC_NO;
        T000_STARTBR_BY_STAC();
        if (pgmRtn) return;
        T000_READNEXT_PROC();
        if (pgmRtn) return;
        while (WS_STAC_FLG != 'N') {
            TDCQAC.AC_NO = GDRSTAC.KEY.AC;
            B010_READ_TDTCMST_AC_NO();
            if (pgmRtn) return;
            T000_READNEXT_PROC();
            if (pgmRtn) return;
        }
        T000_ENDBR_PROC();
        if (pgmRtn) return;
    }
    public void R000_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R010_READ_TDTCMST_AC_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSAGEN);
        CICSAGEN.OUT_AC = TDRSMST.AC_NO;
        CICSAGEN.AGENT_TP = "3";
        CICSAGEN.FUNC = 'S';
        CEP.TRC(SCCGWA, CICSAGEN.OUT_AC);
        S000_CALL_CIZSAGEN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICSAGEN.FOUND_FLG);
        WS_OUT_DAT.WS_AGC_FLG = CICSAGEN.FOUND_FLG;
        IBS.init(SCCGWA, TDRCMST);
        TDRCMST.KEY.AC_NO = TDRSMST.AC_NO;
        CEP.TRC(SCCGWA, TDRCMST.KEY.AC_NO);
        CEP.TRC(SCCGWA, TDCQAC.SRCH_FLG);
        if (TDCQAC.SRCH_FLG == '1') {
            T000_READ_TDTCMST_NOR();
            if (pgmRtn) return;
        } else {
            if (TDCQAC.SRCH_FLG == '2') {
                T000_READ_TDTCMST_YBT();
                if (pgmRtn) return;
            } else {
                T000_READ_TDTCMST();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, TDRCMST.CI_TYP);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
            if (TDRCMST.CI_TYP == '1' 
                && SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                WS_OUT_DAT.WS_AC_NO = TDRCMST.KEY.AC_NO;
                WS_OUT_DAT.WS_BV_TYP = TDRCMST.BV_TYP;
                WS_OUT_DAT.WS_STS = TDRCMST.STS;
                WS_OUT_DAT.WS_CI_TYP = TDRCMST.CI_TYP;
                WS_OUT_DAT.WS_CCY = TDRCMST.CCY;
                WS_OUT_DAT.WS_DRAW_MTH = TDRCMST.DRAW_MTH;
                WS_OUT_DAT.WS_CROS_CR_FLG = TDRCMST.CROS_CR_FLG;
                WS_OUT_DAT.WS_CROS_DR_FLG = TDRCMST.CROS_DR_FLG;
                WS_OUT_DAT.WS_OPEN_DATE = TDRCMST.OPEN_DATE;
                WS_OUT_DAT.WS_OWNER_BR = TDRCMST.OWNER_BR;
                WS_OUT_DAT.WS_OPEN_TLR = TDRCMST.OPEN_TLR;
                if (TDRCMST.STSW == null) TDRCMST.STSW = "";
                JIBS_tmp_int = TDRCMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
                if (TDRCMST.STSW.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_OUT_DAT.WS_PSW_STS = '3';
                } else {
                    if (TDRCMST.STSW == null) TDRCMST.STSW = "";
                    JIBS_tmp_int = TDRCMST.STSW.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
                    if (TDRCMST.STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                        WS_OUT_DAT.WS_PSW_STS = '1';
                    } else {
                        WS_OUT_DAT.WS_PSW_STS = '0';
                    }
                }
                IBS.init(SCCGWA, CICACCU);
                CICACCU.DATA.AGR_NO = TDRCMST.KEY.AC_NO;
                S000_CALL_CIZACCU();
                if (pgmRtn) return;
                WS_OUT_DAT.WS_CI_NO = CICACCU.DATA.CI_NO;
                IBS.init(SCCGWA, TDRBVT);
                TDRBVT.KEY.AC_NO = TDRCMST.KEY.AC_NO;
                T000_READ_TDTBVT();
                if (pgmRtn) return;
                WS_OUT_DAT.WS_BV_NO = TDRBVT.BV_NO;
                if (TDRBVT.STSW == null) TDRBVT.STSW = "";
                JIBS_tmp_int = TDRBVT.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
                if (TDRBVT.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_OUT_DAT.WS_LOS_FLG = '2';
                } else {
                    if (TDRBVT.STSW == null) TDRBVT.STSW = "";
                    JIBS_tmp_int = TDRBVT.STSW.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
                    if (TDRBVT.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                        WS_OUT_DAT.WS_LOS_FLG = '1';
                    } else {
                        WS_OUT_DAT.WS_LOS_FLG = '0';
                    }
                }
                R100_OUT_PROC();
                if (pgmRtn) return;
            } else {
                if (TDCQAC.CI_TYP == '2' 
                    || TDCQAC.CI_TYP == '3' 
                    && TDCQAC.SRCH_FLG != '6') {
                    WS_OUT_DAT.WS_AC_NO = TDRCMST.KEY.AC_NO;
                    WS_OUT_DAT.WS_STS = TDRCMST.STS;
                    WS_OUT_DAT.WS_CI_TYP = TDRCMST.CI_TYP;
                    WS_OUT_DAT.WS_CCY = TDRCMST.CCY;
                    WS_OUT_DAT.WS_DRAW_MTH = TDRCMST.DRAW_MTH;
                    WS_OUT_DAT.WS_CROS_CR_FLG = TDRCMST.CROS_CR_FLG;
                    WS_OUT_DAT.WS_CROS_DR_FLG = TDRCMST.CROS_DR_FLG;
                    WS_OUT_DAT.WS_OPEN_DATE = TDRCMST.OPEN_DATE;
                    WS_OUT_DAT.WS_OWNER_BR = TDRCMST.OWNER_BR;
                    WS_OUT_DAT.WS_OPEN_TLR = TDRCMST.OPEN_TLR;
                    IBS.init(SCCGWA, CICQACRI);
                    CICQACRI.DATA.AGR_NO = TDRSMST.AC_NO;
                    CICQACRI.FUNC = 'A';
                    S000_CALL_CIZQACRI();
                    if (pgmRtn) return;
                    WS_OUT_DAT.WS_AC_CNM = CICQACRI.O_DATA.O_AC_CNM;
                    IBS.init(SCCGWA, CICACCU);
                    CICACCU.DATA.AGR_NO = TDRSMST.AC_NO;
                    S000_CALL_CIZACCU();
                    if (pgmRtn) return;
                    WS_OUT_DAT.WS_CI_CNM = CICACCU.DATA.CI_CNM;
                    WS_OUT_DAT.WS_ID_TYP = CICACCU.DATA.ID_TYPE;
                    WS_OUT_DAT.WS_ID_NO = CICACCU.DATA.ID_NO;
                    WS_OUT_DAT.WS_CI_NO = CICACCU.DATA.CI_NO;
                    R100_OUT_PROC();
                    if (pgmRtn) return;
                } else {
                    if (TDCQAC.CI_TYP == '2' 
                        || TDCQAC.CI_TYP == '3' 
                        && TDCQAC.SRCH_FLG == '6') {
                        WS_OUT_DAT.WS_AC_NO = TDRCMST.KEY.AC_NO;
                        WS_OUT_DAT.WS_STS = TDRCMST.STS;
                        WS_OUT_DAT.WS_CI_TYP = TDRCMST.CI_TYP;
                        WS_OUT_DAT.WS_OPEN_DATE = TDRCMST.OPEN_DATE;
                        WS_OUT_DAT.WS_OWNER_BR = TDRCMST.OWNER_BR;
                        WS_OUT_DAT.WS_OPEN_TLR = TDRCMST.OPEN_TLR;
                        IBS.init(SCCGWA, CICACCU);
                        CICACCU.DATA.AGR_NO = TDCQAC.AC_NO;
                        S000_CALL_CIZACCU();
                        if (pgmRtn) return;
                        WS_OUT_DAT.WS_AC_CNM = CICACCU.DATA.AC_CNM;
                        WS_OUT_DAT.WS_CI_NO = CICACCU.DATA.CI_NO;
                        R100_OUT_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
        }
    }
    public void R100_OUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        CEP.TRC(SCCGWA, TDRCMST.CHNL_NO);
        WS_OUT_DAT.WS_CHNL_NO = TDRCMST.CHNL_NO;
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUT_DAT);
        SCCMPAG.DATA_LEN = 692;
        CEP.TRC(SCCGWA, SCCMPAG.DATA_LEN);
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_GET_NOR() throws IOException,SQLException,Exception {
        TDTSMST_BR.rp = new DBParm();
        TDTSMST_BR.rp.TableName = "TDTSMST";
        TDTSMST_BR.rp.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND ACO_STS = :TDRSMST.ACO_STS "
            + "AND PRDAC_CD < > '021' "
            + "AND PRDAC_CD < > '035' "
            + "AND PRDAC_CD < > '036'";
        TDTSMST_BR.rp.order = "ACO_AC";
        IBS.STARTBR(SCCGWA, TDRSMST, this, TDTSMST_BR);
        IBS.READNEXT(SCCGWA, TDRSMST, this, TDTSMST_BR);
        CEP.TRC(SCCGWA, TDRSMST.AC_NO);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1' 
                && TDCQAC.SRCH_FLG == '1') {
        } else {
            R010_READ_TDTCMST_AC_NO();
            if (pgmRtn) return;
        }
    }
    public void R000_GET_COM() throws IOException,SQLException,Exception {
        R010_READ_TDTCMST_AC_NO();
        if (pgmRtn) return;
    }
    public void R000_GET_GUA() throws IOException,SQLException,Exception {
        R010_READ_TDTCMST_AC_NO();
        if (pgmRtn) return;
    }
    public void R000_READNEXT_TDTSMST_GD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRSMST, this, TDTSMST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_OUT_DAT.WS_GD_AC_T = '3';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_OUT_DAT.WS_GD_AC_T = '1';
        } else {
        }
    }
    public void T000_STARTBR_TDTSMST_GD() throws IOException,SQLException,Exception {
        TDTSMST_BR.rp = new DBParm();
        TDTSMST_BR.rp.TableName = "TDTSMST";
        TDTSMST_BR.rp.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND ACO_STS = :TDRSMST.ACO_STS";
        TDTSMST_BR.rp.order = "ACO_AC";
        IBS.STARTBR(SCCGWA, TDRSMST, this, TDTSMST_BR);
    }
    public void T000_READ_CMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        TDTCMST_RD.where = "AC_NO = :TDRCMST.KEY.AC_NO";
        IBS.READ(SCCGWA, TDRCMST, this, TDTCMST_RD);
    }
    public void T000_READ_TDTBVT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.READ(SCCGWA, TDRBVT, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
        } else {
        }
    }
    public void T000_READ_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
    }
    public void T000_READ_TDTCMST_NOR() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        TDTCMST_RD.where = "AC_NO = :TDRCMST.KEY.AC_NO "
            + "AND BV_TYP < > '1'";
        IBS.READ(SCCGWA, TDRCMST, this, TDTCMST_RD);
    }
    public void T000_READ_TDTCMST_YBT() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        TDTCMST_RD.where = "AC_NO = :TDRCMST.KEY.AC_NO "
            + "AND BV_TYP = '1'";
        IBS.READ(SCCGWA, TDRCMST, this, TDTCMST_RD);
    }
    public void T000_STARTBR_BY_STAC() throws IOException,SQLException,Exception {
        GDTSTAC_BR.rp = new DBParm();
        GDTSTAC_BR.rp.TableName = "GDTSTAC";
        GDTSTAC_BR.rp.where = "ST_AC = :GDRSTAC.ST_AC "
            + "AND RELAT_STS = 'N'";
        GDTSTAC_BR.rp.order = "AC_SEQ";
        IBS.STARTBR(SCCGWA, GDRSTAC, this, GDTSTAC_BR);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, GDRSTAC, this, GDTSTAC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_STAC_FLG = 'Y';
        } else {
            WS_STAC_FLG = 'N';
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, GDTSTAC_BR);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        if (CICACCU.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CIZQCIAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST-ACR", CICQCIAC);
        if (CICQCIAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQCIAC.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CIZSAGEN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-CIZSAGEN", CICSAGEN);
        if (CICSAGEN.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICSAGEN.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
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
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
