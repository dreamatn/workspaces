package com.hisun.DD;

import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.DB.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSQHLD {
    DBParm DDTHLD_RD;
    DBParm DDTFBID_RD;
    brParm DDTHLD_BR = new brParm();
    brParm DDTFBID_BR = new brParm();
    brParm CITACAC_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT_A = "DD755";
    int K_SCR_ROW_NO = 8;
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    String WS_TR_AC = " ";
    double WS_DDBAL = 0;
    String WS_CARD_NO = " ";
    DDZSQHLD_WS_TEMP WS_TEMP = new DDZSQHLD_WS_TEMP();
    String WS_AC_CNM = " ";
    String WS_AC_ENM = " ";
    short WS_CNT = 0;
    short WS_TOTAL_NUM = 0;
    short WS_TOTAL_PAGE = 0;
    short WS_REMAINDER = 0;
    short WS_COUNT_F = 0;
    short WS_COUNT_H = 0;
    char WS_DDTHLD_FLG = ' ';
    char WS_DDTFBID_FLG = ' ';
    char WS_CITACAC_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    CIRACAC CIRACAC = new CIRACAC();
    SCCBINF SCCBINF = new SCCBINF();
    CICQACAC CICQACAC = new CICQACAC();
    CICQACRI CICQACRI = new CICQACRI();
    CICQACRL CICQACRL = new CICQACRL();
    DDRHLD DDRHLD = new DDRHLD();
    DDRHLDR DDRHLDR = new DDRHLDR();
    DDRFBID DDRFBID = new DDRFBID();
    DDCF7550 DDCF7550 = new DDCF7550();
    int WS_COUNT = 0;
    int WS_COUNT_DF = 0;
    int WS_RCD_SEQ = 0;
    int WS_FO_DATE = 0;
    int WS_TO_DATE = 0;
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    DDCSQHLD DDCSQHLD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DDCSQHLD DDCSQHLD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSQHLD = DDCSQHLD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSQHLD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, DDCF7550);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_AC_INF_PROC();
        if (pgmRtn) return;
        if (DDCSQHLD.LAST_FLG == ' ') {
            B030_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            B040_RECORD_LAST_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSQHLD.AC);
        CEP.TRC(SCCGWA, DDCSQHLD.SEQ);
        CEP.TRC(SCCGWA, DDCSQHLD.HLD_NO);
        CEP.TRC(SCCGWA, DDCSQHLD.ORG_TYPE);
        CEP.TRC(SCCGWA, DDCSQHLD.HLD_TYP);
        CEP.TRC(SCCGWA, DDCSQHLD.HLD_FLG);
        CEP.TRC(SCCGWA, DDCSQHLD.LAST_FLG);
        if (DDCSQHLD.AC.trim().length() == 0 
            && DDCSQHLD.HLD_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_MUST_INPUT);
        }
        if (DDCSQHLD.ORG_TYPE == ' ') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ORG_TYPE_MUST_INPUT);
        }
        if (DDCSQHLD.HLD_TYP == ' ') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_HLD_TYP_MUST_INPUT);
        }
        if (DDCSQHLD.ORG_TYPE != '1' 
            && DDCSQHLD.ORG_TYPE != '2' 
            && DDCSQHLD.ORG_TYPE != '3' 
            && DDCSQHLD.ORG_TYPE != 'A' 
            && DDCSQHLD.ORG_TYPE != 'B' 
            && DDCSQHLD.ORG_TYPE != 'W') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ORG_TYPE_ERR);
        }
        if (DDCSQHLD.ORG_TYPE == 'B') {
            if (DDCSQHLD.HLD_NO.trim().length() > 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_HLDNO_MUST_SPACE);
            }
            if (DDCSQHLD.HLD_FLG != ' ') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_HLD_FLG_ERR);
            }
            if (DDCSQHLD.HLD_TYP != '1') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_HLD_TYP_ERR);
            }
            if (DDCSQHLD.LAST_FLG != ' ') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LAST_FLG_ERR);
            }
        }
        if (DDCSQHLD.HLD_TYP != '0' 
            && DDCSQHLD.HLD_TYP != '1' 
            && DDCSQHLD.HLD_TYP != 'W') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_HLD_TYP_ERR);
        }
        if (DDCSQHLD.HLD_FLG != ' ' 
            && DDCSQHLD.HLD_FLG != '1' 
            && DDCSQHLD.HLD_FLG != '2') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_HLD_FLG_ERR);
        }
        if (DDCSQHLD.LAST_FLG != ' ' 
            && DDCSQHLD.LAST_FLG != '0' 
            && DDCSQHLD.LAST_FLG != '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LAST_FLG_ERR);
        }
        if (DDCSQHLD.LAST_FLG == '0' 
            || DDCSQHLD.LAST_FLG == '1' 
            && DDCSQHLD.HLD_NO.trim().length() > 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_HLDNO_MUST_SPACE);
        }
        if (DDCSQHLD.HLD_TYP == '1' 
            && DDCSQHLD.HLD_FLG == '2') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_HLD_FLG_ERR);
        }
        if (DDCSQHLD.HLD_TYP == '1' 
            && DDCSQHLD.LAST_FLG == '0') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LAST_FLG_ERR);
        }
        if ((DDCSQHLD.HLD_FLG == '1' 
            && DDCSQHLD.LAST_FLG == '0') 
            || (DDCSQHLD.HLD_FLG == '2' 
            && DDCSQHLD.LAST_FLG == '1')) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LAST_FLG_ERR);
        }
        if (DDCSQHLD.FO_DATE == 0) {
            DDCSQHLD.FO_DATE = 0;
        }
        if (DDCSQHLD.TO_DATE == 0) {
            DDCSQHLD.TO_DATE = 99991231;
        }
        if (DDCSQHLD.FO_DATE > DDCSQHLD.TO_DATE) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_TODT_LESS_FODT);
        }
        if (DDCSQHLD.HLD_TYP == '0') {
            DDCSQHLD.HLD_FLG = '1';
        }
        if (DDCSQHLD.HLD_TYP == '1') {
            DDCSQHLD.HLD_FLG = '2';
            DDCSQHLD.HLD_TYP = '0';
        }
        if (DDCSQHLD.HLD_TYP == 'W') {
            DDCSQHLD.HLD_FLG = ' ';
            DDCSQHLD.HLD_TYP = '0';
        }
        CEP.TRC(SCCGWA, DDCSQHLD.HLD_TYP);
        CEP.TRC(SCCGWA, DDCSQHLD.HLD_FLG);
        CEP.TRC(SCCGWA, DDCSQHLD.LAST_FLG);
        CEP.TRC(SCCGWA, K_SCR_ROW_NO);
        CEP.TRC(SCCGWA, DDCSQHLD.PAGE_ROW);
        if (DDCSQHLD.PAGE_ROW == 0) {
            DDCSQHLD.PAGE_ROW = (short) K_SCR_ROW_NO;
        } else {
            if (DDCSQHLD.PAGE_ROW >= K_SCR_ROW_NO) {
                DDCSQHLD.PAGE_ROW = (short) K_SCR_ROW_NO;
            }
        }
        CEP.TRC(SCCGWA, DDCSQHLD.PAGE_ROW);
        WS_FO_DATE = DDCSQHLD.FO_DATE;
        WS_TO_DATE = DDCSQHLD.TO_DATE;
    }
    public void B020_AC_INF_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSQHLD.AC);
        CEP.TRC(SCCGWA, DDCSQHLD.HLD_NO);
        if (DDCSQHLD.AC.trim().length() > 0) {
            CEP.TRC(SCCGWA, "==HLD-NO SPACE==");
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.FUNC = 'A';
            CICQACRI.DATA.AGR_NO = DDCSQHLD.AC;
            WS_CARD_NO = DDCSQHLD.AC;
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD") 
                && (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
                && DDCSQHLD.SEQ != 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_APP_DD_SEQ_M_SPACE);
            }
            CEP.TRC(SCCGWA, WS_CARD_NO);
            IBS.init(SCCGWA, CICQACRL);
            CICQACRL.FUNC = 'I';
            CICQACRL.DATA.AC_NO = WS_CARD_NO;
            S000_CALL_CIZQACRL();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACRL.RC.RC_CODE);
            CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_AC_REL);
            CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
            if (CICQACRL.RC.RC_CODE == 8054) {
                WS_CARD_NO = CICQACRL.DATA.AC_NO;
            } else if (CICQACRL.RC.RC_CODE == 0 
                    && CICQACRL.O_DATA.O_AC_REL.equalsIgnoreCase("13")) {
                WS_CARD_NO = CICQACRL.O_DATA.O_REL_AC_NO;
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRL.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, "1111111111");
            CEP.TRC(SCCGWA, DDCSQHLD.SEQ);
            CEP.TRC(SCCGWA, DDCSQHLD.BV_NO);
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = WS_CARD_NO;
            CICQACAC.DATA.AGR_SEQ = DDCSQHLD.SEQ;
            CICQACAC.DATA.BV_NO = DDCSQHLD.BV_NO;
            CICQACAC.DATA.CCY_ACAC = DDCSQHLD.CCY;
            CICQACAC.DATA.CR_FLG = DDCSQHLD.CCY_TYPE;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            WS_TR_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            CEP.TRC(SCCGWA, WS_TR_AC);
            if (WS_TR_AC.trim().length() == 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ACAC_NO_NOT_EXIT);
            }
        }
    }
    public void B030_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "==LAST-FLG SPACE==");
        CEP.TRC(SCCGWA, DDCSQHLD.HLD_TYP);
        CEP.TRC(SCCGWA, WS_TR_AC);
        CEP.TRC(SCCGWA, DDCSQHLD.LAST_FLG);
        if (DDCSQHLD.HLD_TYP == '0') {
            CEP.TRC(SCCGWA, "==DDTHLD==");
            if (DDCSQHLD.HLD_NO.trim().length() > 0) {
                if (DDCSQHLD.ORG_TYPE == 'W') {
                    CEP.TRC(SCCGWA, "== DDTHLD HLDNO ORG-TYPE-A ==");
                    WS_TEMP.WS_BROWSE_H_FLG = 'C';
                } else {
                    if (DDCSQHLD.ORG_TYPE == 'A') {
                        CEP.TRC(SCCGWA, "== DDTHLD HLDNO ORG-TYPE-A ==");
                        WS_TEMP.WS_BROWSE_H_FLG = 'A';
                    } else {
                        CEP.TRC(SCCGWA, "== DDTHLD HLDNO ORG-TYPE-NOT-A ==");
                        WS_TEMP.WS_BROWSE_H_FLG = 'B';
                    }
                }
            } else if ((DDCSQHLD.HLD_NO.trim().length() == 0 
                    && DDCSQHLD.AC.trim().length() > 0)) {
                if (DDCSQHLD.ORG_TYPE == 'W') {
                    CEP.TRC(SCCGWA, "== DDTHLD ACAC ORG-TYPE-A ==");
                    WS_TEMP.WS_BROWSE_H_FLG = 'J';
                } else {
                    if (DDCSQHLD.ORG_TYPE == 'A') {
                        CEP.TRC(SCCGWA, "== DDTHLD ACAC ORG-TYPE-A ==");
                        WS_TEMP.WS_BROWSE_H_FLG = 'H';
                    } else {
                        CEP.TRC(SCCGWA, "== DDTHLD ACAC ORG-TYPE-NOT-A ==");
                        WS_TEMP.WS_BROWSE_H_FLG = 'I';
                    }
                }
            } else {
                CEP.TRC(SCCGWA, "----OTHER-----");
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
                WS_ERR_INFO = "WS-BROWSE-H-FLG" + WS_TEMP.WS_BROWSE_H_FLG;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            T000_COUNT_DDTHLD();
            if (pgmRtn) return;
        }
        if (DDCSQHLD.HLD_TYP == '1') {
            CEP.TRC(SCCGWA, "==DDTFBID==");
            if (DDCSQHLD.HLD_NO.trim().length() > 0) {
                if (DDCSQHLD.ORG_TYPE == 'W') {
                    CEP.TRC(SCCGWA, "== DDTFBID HLDNO ORG-TYPE-A ==");
                    WS_TEMP.WS_BROWSE_F_FLG = 'C';
                } else {
                    if (DDCSQHLD.ORG_TYPE == 'A') {
                        CEP.TRC(SCCGWA, "== DDTFBID HLDNO ORG-TYPE-A ==");
                        WS_TEMP.WS_BROWSE_F_FLG = 'A';
                    } else {
                        CEP.TRC(SCCGWA, "== DDTFBID HLDNO ORG-TYPE-NOT-A ==");
                        WS_TEMP.WS_BROWSE_F_FLG = 'B';
                    }
                }
            } else if ((DDCSQHLD.HLD_NO.trim().length() == 0 
                    && DDCSQHLD.AC.trim().length() > 0)) {
                if (DDCSQHLD.ORG_TYPE == 'B' 
                    || DDCSQHLD.ORG_TYPE == 'W') {
                    CEP.TRC(SCCGWA, "== JK037 DDTFBID ORG-TYPE-B ==");
                    WS_TEMP.WS_BROWSE_F_FLG = 'J';
                } else {
                    if (DDCSQHLD.ORG_TYPE == 'A') {
                        CEP.TRC(SCCGWA, "== DDTFBID ACAC ORG-TYPE-A ==");
                        WS_TEMP.WS_BROWSE_F_FLG = 'H';
                    } else {
                        CEP.TRC(SCCGWA, "== DDTFBID ACAC ORG-TYPE-NOT-A ==");
                        WS_TEMP.WS_BROWSE_F_FLG = 'I';
                    }
                }
            } else {
                CEP.TRC(SCCGWA, "----OTHER-----");
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
                WS_ERR_INFO = "WS-BROWSE-F-FLG" + WS_TEMP.WS_BROWSE_F_FLG;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            T000_COUNT_DDTFBID();
            if (pgmRtn) return;
        }
        if (DDCSQHLD.HLD_TYP == 'W') {
            CEP.TRC(SCCGWA, "==DDTFBID==");
            if (DDCSQHLD.HLD_NO.trim().length() > 0) {
                if (DDCSQHLD.ORG_TYPE == 'W') {
                    CEP.TRC(SCCGWA, "== DDTFBID HLDNO ORG-TYPE-A ==");
                    WS_TEMP.WS_BROWSE_F_FLG = 'C';
                } else {
                    if (DDCSQHLD.ORG_TYPE == 'A') {
                        CEP.TRC(SCCGWA, "== DDTFBID HLDNO ORG-TYPE-A ==");
                        WS_TEMP.WS_BROWSE_F_FLG = 'A';
                    } else {
                        CEP.TRC(SCCGWA, "== DDTFBID HLDNO ORG-TYPE-NOT-A ==");
                        WS_TEMP.WS_BROWSE_F_FLG = 'B';
                    }
                }
            } else if ((DDCSQHLD.HLD_NO.trim().length() == 0 
                    && DDCSQHLD.AC.trim().length() > 0)) {
                if (DDCSQHLD.ORG_TYPE == 'B') {
                    CEP.TRC(SCCGWA, "== JK037 DDTFBID ORG-TYPE-B ==");
                    WS_TEMP.WS_BROWSE_F_FLG = 'J';
                } else {
                    if (DDCSQHLD.ORG_TYPE == 'W') {
                        CEP.TRC(SCCGWA, "== WZB001 DDTFBID ORG-TYPE-W ==");
                        WS_TEMP.WS_BROWSE_F_FLG = 'K';
                    } else {
                        if (DDCSQHLD.ORG_TYPE == 'A') {
                            CEP.TRC(SCCGWA, "== DDTFBID ACAC ORG-TYPE-A ==");
                            WS_TEMP.WS_BROWSE_F_FLG = 'H';
                        } else {
                            CEP.TRC(SCCGWA, "== DDTFBID ACAC ORG-TYPE-NOT-A ==");
                            WS_TEMP.WS_BROWSE_F_FLG = 'I';
                        }
                    }
                }
            } else {
                CEP.TRC(SCCGWA, "----OTHER-----");
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
                WS_ERR_INFO = "WS-BROWSE-F-FLG" + WS_TEMP.WS_BROWSE_F_FLG;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            T000_COUNT_DDTFBID();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "==DDTHLD==");
            if (DDCSQHLD.HLD_NO.trim().length() > 0) {
                if (DDCSQHLD.ORG_TYPE == 'W') {
                    CEP.TRC(SCCGWA, "== DDTHLD HLDNO ORG-TYPE-A ==");
                    WS_TEMP.WS_BROWSE_H_FLG = 'C';
                } else {
                    if (DDCSQHLD.ORG_TYPE == 'A') {
                        CEP.TRC(SCCGWA, "== DDTHLD HLDNO ORG-TYPE-A ==");
                        WS_TEMP.WS_BROWSE_H_FLG = 'A';
                    } else {
                        CEP.TRC(SCCGWA, "== DDTHLD HLDNO ORG-TYPE-NOT-A ==");
                        WS_TEMP.WS_BROWSE_H_FLG = 'B';
                    }
                }
            } else if ((DDCSQHLD.HLD_NO.trim().length() == 0 
                    && DDCSQHLD.AC.trim().length() > 0)) {
                if (DDCSQHLD.ORG_TYPE == 'W') {
                    CEP.TRC(SCCGWA, "== DDTHLD ACAC ORG-TYPE-A ==");
                    WS_TEMP.WS_BROWSE_H_FLG = 'J';
                } else {
                    if (DDCSQHLD.ORG_TYPE == 'A') {
                        CEP.TRC(SCCGWA, "== DDTHLD ACAC ORG-TYPE-A ==");
                        WS_TEMP.WS_BROWSE_H_FLG = 'H';
                    } else {
                        CEP.TRC(SCCGWA, "== DDTHLD ACAC ORG-TYPE-NOT-A ==");
                        WS_TEMP.WS_BROWSE_H_FLG = 'I';
                    }
                }
            } else {
                CEP.TRC(SCCGWA, "----OTHER-----");
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
                WS_ERR_INFO = "WS-BROWSE-H-FLG" + WS_TEMP.WS_BROWSE_H_FLG;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            T000_COUNT_DDTHLD();
            if (pgmRtn) return;
            WS_COUNT = WS_COUNT_F + WS_COUNT_H;
        }
        if (WS_COUNT == 0) {
            CEP.TRC(SCCGWA, "===NO RECORD===");
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_NO_RSLT);
        }
        WS_TOTAL_NUM = (short) WS_COUNT;
        CEP.TRC(SCCGWA, DDCSQHLD.PAGE_ROW);
        WS_REMAINDER = (short) (WS_TOTAL_NUM % DDCSQHLD.PAGE_ROW);
        WS_TOTAL_PAGE = (short) ((WS_TOTAL_NUM - WS_REMAINDER) / DDCSQHLD.PAGE_ROW);
        CEP.TRC(SCCGWA, WS_REMAINDER);
        if (WS_REMAINDER != 0) {
            WS_TOTAL_PAGE += 1;
        }
        CEP.TRC(SCCGWA, DDCSQHLD.PAGE_NUM);
        CEP.TRC(SCCGWA, DDCSQHLD.PAGE_ROW);
        if (DDCSQHLD.PAGE_NUM == 0) {
            WS_RCD_SEQ = 1;
        } else {
            WS_RCD_SEQ = ( DDCSQHLD.PAGE_NUM - 1 ) * DDCSQHLD.PAGE_ROW + 1;
        }
        CEP.TRC(SCCGWA, WS_RCD_SEQ);
        if (DDCSQHLD.HLD_TYP == 'W') {
            CEP.TRC(SCCGWA, WS_COUNT_F);
            CEP.TRC(SCCGWA, WS_COUNT_H);
            CEP.TRC(SCCGWA, WS_RCD_SEQ);
            if (WS_RCD_SEQ > WS_COUNT_F) {
                WS_RCD_SEQ = WS_RCD_SEQ - WS_COUNT_F;
                T000_STARTBR_DDTHLD();
                if (pgmRtn) return;
                R000_BROWSE_DDTHLD();
                if (pgmRtn) return;
            } else {
                if (WS_RCD_SEQ + DDCSQHLD.PAGE_ROW - 1 <= WS_COUNT_F 
                    || WS_COUNT_H == 0) {
                    T000_STARTBR_DDTFBID();
                    if (pgmRtn) return;
                    R000_BROWSE_DDTFBID();
                    if (pgmRtn) return;
                } else {
                    T000_STARTBR_DDTFBID();
                    if (pgmRtn) return;
                    T000_STARTBR_DDTHLD();
                    if (pgmRtn) return;
                    R000_BROWSE_DDTALL();
                    if (pgmRtn) return;
                }
            }
        } else {
            if (DDCSQHLD.HLD_TYP == '0') {
                T000_STARTBR_DDTHLD();
                if (pgmRtn) return;
                R000_BROWSE_DDTHLD();
                if (pgmRtn) return;
            } else {
                T000_STARTBR_DDTFBID();
                if (pgmRtn) return;
                R000_BROWSE_DDTFBID();
                if (pgmRtn) return;
            }
        }
    }
    public void B040_RECORD_LAST_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "==LAST-FLG NOT SPACE==");
        if (DDCSQHLD.LAST_FLG == '0') {
            DDCSQHLD.HLD_FLG = '2';
        }
        if (DDCSQHLD.LAST_FLG == '1' 
            && DDCSQHLD.ORG_TYPE != 'A') {
            DDCSQHLD.HLD_FLG = '1';
        }
        CEP.TRC(SCCGWA, DDCSQHLD.HLD_FLG);
        if (DDCSQHLD.HLD_TYP == '0') {
            CEP.TRC(SCCGWA, "==DDTHLD LAST==");
            if (DDCSQHLD.ORG_TYPE == 'A') {
                CEP.TRC(SCCGWA, "== DDTHLD ACAC ORG-TYPE-A LAST ==");
                WS_TEMP.WS_BROWSE_H_FLG = 'O';
            } else {
                CEP.TRC(SCCGWA, "== DDTHLD ACAC ORG-TYPE-NOT-A LAST ==");
                WS_TEMP.WS_BROWSE_H_FLG = 'P';
            }
            T000_READ_DDTHLD_LAST();
            if (pgmRtn) return;
            if (WS_DDTHLD_FLG == 'N') {
                CEP.TRC(SCCGWA, "===NO RECORD===");
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_NO_RSLT);
            }
        }
        if (DDCSQHLD.HLD_TYP == '1') {
            CEP.TRC(SCCGWA, "==DDTFBID LAST==");
            if (DDCSQHLD.ORG_TYPE == 'A') {
                CEP.TRC(SCCGWA, "== DDTFBID ACAC ORG-TYPE-A LAST ==");
                WS_TEMP.WS_BROWSE_F_FLG = 'Q';
            } else {
                CEP.TRC(SCCGWA, "== DDTFBID ACAC ORG-TYPE-NOT-A LAST ==");
                WS_TEMP.WS_BROWSE_F_FLG = 'R';
            }
            T000_READ_DDTFBID_LAST();
            if (pgmRtn) return;
            if (WS_DDTFBID_FLG == 'N') {
                CEP.TRC(SCCGWA, "===NO RECORD===");
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_NO_RSLT);
            }
        }
        WS_CNT = 1;
        B020_70_OUTPUT_DETAIL();
        if (pgmRtn) return;
        DDCF7550.TOT_PAGE = 1;
        DDCF7550.TOT_NUM = 1;
        DDCF7550.CURR_PAGE = 1;
        DDCF7550.CURR_ROW = 1;
        DDCF7550.LAST_IND = 'Y';
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_A;
        SCCFMT.DATA_PTR = DDCF7550;
        SCCFMT.DATA_LEN = 9481;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_BROWSE_DDTHLD() throws IOException,SQLException,Exception {
        T000_READNEXT_DDTHLD_FIRST();
        if (pgmRtn) return;
        if (WS_DDTHLD_FLG == 'N') {
            if (DDCSQHLD.PAGE_NUM == 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_NO_RSLT);
            } else {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LAST_PAGE);
            }
        }
        CEP.TRC(SCCGWA, WS_CNT);
        CEP.TRC(SCCGWA, DDCSQHLD.PAGE_ROW);
        for (WS_CNT = 1; WS_CNT <= DDCSQHLD.PAGE_ROW 
            && WS_DDTHLD_FLG != 'N'; WS_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CNT);
            CEP.TRC(SCCGWA, "ABCDEFG");
            CEP.TRC(SCCGWA, DDRHLD.KEY.HLD_NO);
            B020_70_OUTPUT_DETAIL_H();
            if (pgmRtn) return;
            T000_READNEXT_DDTHLD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCF7550.OUTPUT_DATA[WS_CNT-1].HLD_NO);
            WS_RCD_SEQ = WS_RCD_SEQ + 1;
        }
        DDCF7550.TOT_PAGE = WS_TOTAL_PAGE;
        DDCF7550.TOT_NUM = WS_TOTAL_NUM;
        if (DDCSQHLD.PAGE_NUM == 0) {
            DDCF7550.CURR_PAGE = 1;
        } else {
            DDCF7550.CURR_PAGE = DDCSQHLD.PAGE_NUM;
        }
        if (WS_DDTHLD_FLG == 'N') {
            DDCF7550.LAST_IND = 'Y';
            DDCF7550.CURR_ROW = (short) (WS_CNT - 1);
        } else {
            DDCF7550.CURR_ROW = DDCSQHLD.PAGE_ROW;
        }
        CEP.TRC(SCCGWA, WS_TOTAL_PAGE);
        CEP.TRC(SCCGWA, WS_TOTAL_NUM);
        CEP.TRC(SCCGWA, DDCF7550.CURR_PAGE);
        CEP.TRC(SCCGWA, DDCF7550.CURR_ROW);
        T000_ENDBR_DDTHLD();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_A;
        SCCFMT.DATA_PTR = DDCF7550;
        SCCFMT.DATA_LEN = 9481;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_BROWSE_DDTFBID() throws IOException,SQLException,Exception {
        T000_READNEXT_DDTFBID_FIRST();
        if (pgmRtn) return;
        if (WS_DDTFBID_FLG == 'N' 
            && DDCSQHLD.HLD_TYP != 'W') {
            if (DDCSQHLD.PAGE_NUM == 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_NO_RSLT);
            } else {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LAST_PAGE);
            }
        }
        CEP.TRC(SCCGWA, WS_CNT);
        CEP.TRC(SCCGWA, DDCSQHLD.PAGE_ROW);
        for (WS_CNT = 1; WS_CNT <= DDCSQHLD.PAGE_ROW 
            && WS_DDTFBID_FLG != 'N'; WS_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CNT);
            CEP.TRC(SCCGWA, "ABCDEFG");
            CEP.TRC(SCCGWA, DDRFBID.KEY.REF_NO);
            B020_70_OUTPUT_DETAIL_F();
            if (pgmRtn) return;
            T000_READNEXT_DDTFBID();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCF7550.OUTPUT_DATA[WS_CNT-1].HLD_NO);
            WS_RCD_SEQ = WS_RCD_SEQ + 1;
        }
        DDCF7550.TOT_PAGE = WS_TOTAL_PAGE;
        DDCF7550.TOT_NUM = WS_TOTAL_NUM;
        if (DDCSQHLD.PAGE_NUM == 0) {
            DDCF7550.CURR_PAGE = 1;
        } else {
            DDCF7550.CURR_PAGE = DDCSQHLD.PAGE_NUM;
        }
        if (WS_DDTFBID_FLG == 'N') {
            DDCF7550.LAST_IND = 'Y';
            DDCF7550.CURR_ROW = (short) (WS_CNT - 1);
        } else {
            DDCF7550.CURR_ROW = DDCSQHLD.PAGE_ROW;
        }
        CEP.TRC(SCCGWA, WS_TOTAL_PAGE);
        CEP.TRC(SCCGWA, WS_TOTAL_NUM);
        CEP.TRC(SCCGWA, DDCF7550.CURR_PAGE);
        CEP.TRC(SCCGWA, DDCF7550.CURR_ROW);
        T000_ENDBR_DDTFBID();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_A;
        SCCFMT.DATA_PTR = DDCF7550;
        SCCFMT.DATA_LEN = 9481;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_BROWSE_DDTALL() throws IOException,SQLException,Exception {
        T000_READNEXT_DDTFBID_FIRST();
        if (pgmRtn) return;
        if (WS_DDTFBID_FLG == 'N' 
            && DDCSQHLD.HLD_TYP != 'W') {
            if (DDCSQHLD.PAGE_NUM == 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_NO_RSLT);
            } else {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LAST_PAGE);
            }
        }
        CEP.TRC(SCCGWA, WS_CNT);
        CEP.TRC(SCCGWA, DDCSQHLD.PAGE_ROW);
        for (WS_CNT = 1; WS_CNT <= DDCSQHLD.PAGE_ROW 
            && WS_DDTFBID_FLG != 'N'; WS_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CNT);
            CEP.TRC(SCCGWA, "ABCDEFG");
            CEP.TRC(SCCGWA, DDRFBID.KEY.REF_NO);
            B020_70_OUTPUT_DETAIL_F();
            if (pgmRtn) return;
            T000_READNEXT_DDTFBID();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCF7550.OUTPUT_DATA[WS_CNT-1].HLD_NO);
            WS_RCD_SEQ = WS_RCD_SEQ + 1;
        }
        CEP.TRC(SCCGWA, DDCSQHLD.PAGE_ROW);
        CEP.TRC(SCCGWA, WS_COUNT_F);
        CEP.TRC(SCCGWA, WS_RCD_SEQ);
        WS_RCD_SEQ = 1;
        CEP.TRC(SCCGWA, WS_RCD_SEQ);
        T000_READNEXT_DDTHLD_FIRST();
        if (pgmRtn) return;
        if (WS_DDTHLD_FLG == 'N') {
            if (DDCSQHLD.PAGE_NUM == 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_NO_RSLT);
            } else {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_LAST_PAGE);
            }
        }
        CEP.TRC(SCCGWA, WS_CNT);
        CEP.TRC(SCCGWA, DDCSQHLD.PAGE_ROW);
        for (WS_CNT = WS_CNT; WS_CNT <= DDCSQHLD.PAGE_ROW 
            && WS_DDTHLD_FLG != 'N'; WS_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CNT);
            CEP.TRC(SCCGWA, "ABCDEFG");
            CEP.TRC(SCCGWA, DDRHLD.KEY.HLD_NO);
            B020_70_OUTPUT_DETAIL_H();
            if (pgmRtn) return;
            T000_READNEXT_DDTHLD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCF7550.OUTPUT_DATA[WS_CNT-1].HLD_NO);
            WS_RCD_SEQ = WS_RCD_SEQ + 1;
        }
        DDCF7550.TOT_PAGE = WS_TOTAL_PAGE;
        DDCF7550.TOT_NUM = WS_TOTAL_NUM;
        if (DDCSQHLD.PAGE_NUM == 0) {
            DDCF7550.CURR_PAGE = 1;
        } else {
            DDCF7550.CURR_PAGE = DDCSQHLD.PAGE_NUM;
        }
        if (WS_DDTHLD_FLG == 'N') {
            DDCF7550.LAST_IND = 'Y';
            DDCF7550.CURR_ROW = (short) (WS_CNT - 1);
        } else {
            DDCF7550.CURR_ROW = DDCSQHLD.PAGE_ROW;
        }
        CEP.TRC(SCCGWA, WS_TOTAL_PAGE);
        CEP.TRC(SCCGWA, WS_TOTAL_NUM);
        CEP.TRC(SCCGWA, DDCF7550.CURR_PAGE);
        CEP.TRC(SCCGWA, DDCF7550.CURR_ROW);
        T000_ENDBR_DDTFBID();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_A;
        SCCFMT.DATA_PTR = DDCF7550;
        SCCFMT.DATA_LEN = 9481;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B020_70_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        DDCF7550.OUTPUT_DATA[WS_CNT-1].AC = DDCSQHLD.AC;
        if (DDCSQHLD.HLD_TYP == '0') {
            DDCF7550.OUTPUT_DATA[WS_CNT-1].HLD_NO = DDRHLD.KEY.HLD_NO;
            DDCF7550.OUTPUT_DATA[WS_CNT-1].HLD_TYP = DDRHLD.HLD_TYP;
            DDCF7550.OUTPUT_DATA[WS_CNT-1].SPR_TYP = DDRHLD.SPR_BR_TYP;
            DDCF7550.OUTPUT_DATA[WS_CNT-1].HLD_STS = DDRHLD.HLD_STS;
            DDCF7550.OUTPUT_DATA[WS_CNT-1].HLD_AMT = DDRHLD.HLD_AMT;
            DDCF7550.OUTPUT_DATA[WS_CNT-1].SPR_NO = DDRHLD.HLD_WRIT_NO;
            DDCF7550.OUTPUT_DATA[WS_CNT-1].SPR_NM = DDRHLD.HLD_BR_NM;
            DDCF7550.OUTPUT_DATA[WS_CNT-1].EFF_DT = DDRHLD.EFF_DATE;
            DDCF7550.OUTPUT_DATA[WS_CNT-1].EXP_DT = DDRHLD.EXP_DATE;
            DDCF7550.OUTPUT_DATA[WS_CNT-1].RSN = DDRHLD.HLD_RSN;
            DDCF7550.OUTPUT_DATA[WS_CNT-1].LAW_NM1 = DDRHLD.LAW_OFF_NM1;
            DDCF7550.OUTPUT_DATA[WS_CNT-1].LAW_NO1 = DDRHLD.LAW_ID_NO1;
            DDCF7550.OUTPUT_DATA[WS_CNT-1].LAW_NM2 = DDRHLD.LAW_OFF_NM2;
            DDCF7550.OUTPUT_DATA[WS_CNT-1].LAW_NO2 = DDRHLD.LAW_ID_NO2;
            DDCF7550.OUTPUT_DATA[WS_CNT-1].ORG_TYPE = DDRHLD.ORG_TYPE;
            DDCF7550.OUTPUT_DATA[WS_CNT-1].HLD_FLG = DDRHLD.HLD_FLG;
            CEP.TRC(SCCGWA, DDRHLD.AC);
        } else {
            DDCF7550.OUTPUT_DATA[WS_CNT-1].HLD_NO = DDRFBID.KEY.REF_NO;
            if (DDRFBID.ORG_TYP == '1') {
                DDCF7550.OUTPUT_DATA[WS_CNT-1].SPR_TYP = '2';
            }
            if (DDRFBID.ORG_TYP == '2') {
                DDCF7550.OUTPUT_DATA[WS_CNT-1].SPR_TYP = '1';
            }
            if (DDRFBID.STS == '1') {
                DDCF7550.OUTPUT_DATA[WS_CNT-1].HLD_STS = 'N';
            }
            if (DDRFBID.STS == '2') {
                DDCF7550.OUTPUT_DATA[WS_CNT-1].HLD_STS = 'C';
            }
            if (DDRFBID.STS == '3') {
                DDCF7550.OUTPUT_DATA[WS_CNT-1].HLD_STS = 'W';
            }
            DDCF7550.OUTPUT_DATA[WS_CNT-1].SPR_NO = DDRFBID.BOOK_NO;
            DDCF7550.OUTPUT_DATA[WS_CNT-1].SPR_NM = DDRFBID.ORG_NAME;
            DDCF7550.OUTPUT_DATA[WS_CNT-1].EFF_DT = DDRFBID.EFF_DATE;
            DDCF7550.OUTPUT_DATA[WS_CNT-1].EXP_DT = DDRFBID.EXP_DATE;
            DDCF7550.OUTPUT_DATA[WS_CNT-1].RSN = DDRFBID.REASON;
            if (DDRFBID.STS == '2') {
                DDCF7550.OUTPUT_DATA[WS_CNT-1].LAW_NM1 = DDRFBID.RLAW_NM1;
                DDCF7550.OUTPUT_DATA[WS_CNT-1].LAW_NO1 = DDRFBID.RLAW_NO1;
                DDCF7550.OUTPUT_DATA[WS_CNT-1].LAW_NM2 = DDRFBID.RLAW_NM2;
                DDCF7550.OUTPUT_DATA[WS_CNT-1].LAW_NO2 = DDRFBID.RLAW_NO2;
            } else {
                DDCF7550.OUTPUT_DATA[WS_CNT-1].LAW_NM1 = DDRFBID.SLAW_NM1;
                DDCF7550.OUTPUT_DATA[WS_CNT-1].LAW_NO1 = DDRFBID.SLAW_NO1;
                DDCF7550.OUTPUT_DATA[WS_CNT-1].LAW_NM2 = DDRFBID.SLAW_NM2;
                DDCF7550.OUTPUT_DATA[WS_CNT-1].LAW_NO2 = DDRFBID.SLAW_NO2;
            }
            DDCF7550.OUTPUT_DATA[WS_CNT-1].ORG_TYPE = DDRFBID.ORG_TYPE;
            CEP.TRC(SCCGWA, DDRFBID.AC);
        }
        IBS.init(SCCGWA, CICQACAC);
        if (DDCSQHLD.HLD_TYP == '0') {
            CICQACAC.DATA.ACAC_NO = DDRHLD.AC;
        } else {
            CICQACAC.DATA.ACAC_NO = DDRFBID.AC;
        }
        CICQACAC.FUNC = 'A';
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ);
        DDCF7550.OUTPUT_DATA[WS_CNT-1].AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
        DDCF7550.OUTPUT_DATA[WS_CNT-1].CCY = CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC;
        DDCF7550.OUTPUT_DATA[WS_CNT-1].CCY_TYP = CICQACAC.O_DATA.O_ACAC_DATA.O_CR_FLG;
    }
    public void B020_70_OUTPUT_DETAIL_F() throws IOException,SQLException,Exception {
        DDCF7550.OUTPUT_DATA[WS_CNT-1].AC = DDCSQHLD.AC;
        DDCF7550.OUTPUT_DATA[WS_CNT-1].HLD_NO = DDRFBID.KEY.REF_NO;
        if (DDRFBID.ORG_TYP == '1') {
            DDCF7550.OUTPUT_DATA[WS_CNT-1].SPR_TYP = '2';
        }
        if (DDRFBID.ORG_TYP == '2') {
            DDCF7550.OUTPUT_DATA[WS_CNT-1].SPR_TYP = '1';
        }
        if (DDRFBID.STS == '1') {
            DDCF7550.OUTPUT_DATA[WS_CNT-1].HLD_STS = 'N';
        }
        if (DDRFBID.STS == '2') {
            DDCF7550.OUTPUT_DATA[WS_CNT-1].HLD_STS = 'C';
        }
        if (DDRFBID.STS == '3') {
            DDCF7550.OUTPUT_DATA[WS_CNT-1].HLD_STS = 'W';
        }
        DDCF7550.OUTPUT_DATA[WS_CNT-1].SPR_NO = DDRFBID.BOOK_NO;
        DDCF7550.OUTPUT_DATA[WS_CNT-1].SPR_NM = DDRFBID.ORG_NAME;
        DDCF7550.OUTPUT_DATA[WS_CNT-1].EFF_DT = DDRFBID.EFF_DATE;
        DDCF7550.OUTPUT_DATA[WS_CNT-1].EXP_DT = DDRFBID.EXP_DATE;
        DDCF7550.OUTPUT_DATA[WS_CNT-1].RSN = DDRFBID.REASON;
        if (DDRFBID.STS == '2') {
            DDCF7550.OUTPUT_DATA[WS_CNT-1].LAW_NM1 = DDRFBID.RLAW_NM1;
            DDCF7550.OUTPUT_DATA[WS_CNT-1].LAW_NO1 = DDRFBID.RLAW_NO1;
            DDCF7550.OUTPUT_DATA[WS_CNT-1].LAW_NM2 = DDRFBID.RLAW_NM2;
            DDCF7550.OUTPUT_DATA[WS_CNT-1].LAW_NO2 = DDRFBID.RLAW_NO2;
        } else {
            DDCF7550.OUTPUT_DATA[WS_CNT-1].LAW_NM1 = DDRFBID.SLAW_NM1;
            DDCF7550.OUTPUT_DATA[WS_CNT-1].LAW_NO1 = DDRFBID.SLAW_NO1;
            DDCF7550.OUTPUT_DATA[WS_CNT-1].LAW_NM2 = DDRFBID.SLAW_NM2;
            DDCF7550.OUTPUT_DATA[WS_CNT-1].LAW_NO2 = DDRFBID.SLAW_NO2;
        }
        DDCF7550.OUTPUT_DATA[WS_CNT-1].ORG_TYPE = DDRFBID.ORG_TYPE;
        CEP.TRC(SCCGWA, DDRFBID.AC);
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.ACAC_NO = DDRFBID.AC;
        CICQACAC.FUNC = 'A';
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ);
        DDCF7550.OUTPUT_DATA[WS_CNT-1].AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
        DDCF7550.OUTPUT_DATA[WS_CNT-1].CCY = CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC;
        DDCF7550.OUTPUT_DATA[WS_CNT-1].CCY_TYP = CICQACAC.O_DATA.O_ACAC_DATA.O_CR_FLG;
    }
    public void B020_70_OUTPUT_DETAIL_H() throws IOException,SQLException,Exception {
        DDCF7550.OUTPUT_DATA[WS_CNT-1].AC = DDCSQHLD.AC;
        DDCF7550.OUTPUT_DATA[WS_CNT-1].HLD_NO = DDRHLD.KEY.HLD_NO;
        DDCF7550.OUTPUT_DATA[WS_CNT-1].HLD_TYP = DDRHLD.HLD_TYP;
        DDCF7550.OUTPUT_DATA[WS_CNT-1].SPR_TYP = DDRHLD.SPR_BR_TYP;
        DDCF7550.OUTPUT_DATA[WS_CNT-1].HLD_STS = DDRHLD.HLD_STS;
        DDCF7550.OUTPUT_DATA[WS_CNT-1].HLD_AMT = DDRHLD.HLD_AMT;
        DDCF7550.OUTPUT_DATA[WS_CNT-1].SPR_NO = DDRHLD.HLD_WRIT_NO;
        DDCF7550.OUTPUT_DATA[WS_CNT-1].SPR_NM = DDRHLD.HLD_BR_NM;
        DDCF7550.OUTPUT_DATA[WS_CNT-1].EFF_DT = DDRHLD.EFF_DATE;
        DDCF7550.OUTPUT_DATA[WS_CNT-1].EXP_DT = DDRHLD.EXP_DATE;
        DDCF7550.OUTPUT_DATA[WS_CNT-1].RSN = DDRHLD.HLD_RSN;
        DDCF7550.OUTPUT_DATA[WS_CNT-1].LAW_NM1 = DDRHLD.LAW_OFF_NM1;
        DDCF7550.OUTPUT_DATA[WS_CNT-1].LAW_NO1 = DDRHLD.LAW_ID_NO1;
        DDCF7550.OUTPUT_DATA[WS_CNT-1].LAW_NM2 = DDRHLD.LAW_OFF_NM2;
        DDCF7550.OUTPUT_DATA[WS_CNT-1].LAW_NO2 = DDRHLD.LAW_ID_NO2;
        DDCF7550.OUTPUT_DATA[WS_CNT-1].ORG_TYPE = DDRHLD.ORG_TYPE;
        DDCF7550.OUTPUT_DATA[WS_CNT-1].HLD_FLG = DDRHLD.HLD_FLG;
        CEP.TRC(SCCGWA, DDRHLD.AC);
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.ACAC_NO = DDRHLD.AC;
        CICQACAC.FUNC = 'A';
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ);
        DDCF7550.OUTPUT_DATA[WS_CNT-1].AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
        DDCF7550.OUTPUT_DATA[WS_CNT-1].CCY = CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC;
        DDCF7550.OUTPUT_DATA[WS_CNT-1].CCY_TYP = CICQACAC.O_DATA.O_ACAC_DATA.O_CR_FLG;
    }
    public void T000_COUNT_DDTHLD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLD);
        DDRHLD.AC = WS_TR_AC;
        DDRHLD.CARD_NO = DDCSQHLD.AC;
        DDRHLD.KEY.HLD_NO = DDCSQHLD.HLD_NO;
        DDRHLD.ORG_TYPE = DDCSQHLD.ORG_TYPE;
        DDRHLD.HLD_FLG = DDCSQHLD.HLD_FLG;
        CEP.TRC(SCCGWA, DDRHLD.AC);
        CEP.TRC(SCCGWA, DDRHLD.CARD_NO);
        CEP.TRC(SCCGWA, DDRHLD.KEY.HLD_NO);
        CEP.TRC(SCCGWA, DDRHLD.ORG_TYPE);
        CEP.TRC(SCCGWA, DDRHLD.HLD_FLG);
        CEP.TRC(SCCGWA, WS_FO_DATE);
        CEP.TRC(SCCGWA, WS_TO_DATE);
        if (WS_TEMP.WS_BROWSE_H_FLG == 'A') {
            DDTHLD_RD = new DBParm();
            DDTHLD_RD.TableName = "DDTHLD";
            DDTHLD_RD.set = "WS-COUNT=COUNT(*)";
            DDTHLD_RD.where = "HLD_NO = :DDRHLD.KEY.HLD_NO "
                + "AND SPR_BR_TYP = '1' "
                + "AND ( ORG_TYPE = '1' "
                + "OR ORG_TYPE = '3' "
                + "OR ORG_TYPE = ' ' ) "
                + "AND ( HLD_FLG = :DDRHLD.HLD_FLG "
                + "OR ( :DDRHLD.HLD_FLG = ' ' ) ) "
                + "AND ( EFF_DATE BETWEEN :WS_FO_DATE "
                + "AND :WS_TO_DATE )";
            IBS.GROUP(SCCGWA, DDRHLD, this, DDTHLD_RD);
        } else if (WS_TEMP.WS_BROWSE_H_FLG == 'B') {
            DDTHLD_RD = new DBParm();
            DDTHLD_RD.TableName = "DDTHLD";
            DDTHLD_RD.set = "WS-COUNT=COUNT(*)";
            DDTHLD_RD.where = "HLD_NO = :DDRHLD.KEY.HLD_NO "
                + "AND SPR_BR_TYP = '1' "
                + "AND ( ORG_TYPE = :DDRHLD.ORG_TYPE ) "
                + "AND ( HLD_FLG = :DDRHLD.HLD_FLG "
                + "OR ( :DDRHLD.HLD_FLG = ' ' ) ) "
                + "AND ( EFF_DATE BETWEEN :WS_FO_DATE "
                + "AND :WS_TO_DATE )";
            IBS.GROUP(SCCGWA, DDRHLD, this, DDTHLD_RD);
        } else if (WS_TEMP.WS_BROWSE_H_FLG == 'C') {
            DDTHLD_RD = new DBParm();
            DDTHLD_RD.TableName = "DDTHLD";
            DDTHLD_RD.set = "WS-COUNT=COUNT(*)";
            DDTHLD_RD.where = "HLD_NO = :DDRHLD.KEY.HLD_NO "
                + "AND SPR_BR_TYP = '1' "
                + "AND ( HLD_FLG = :DDRHLD.HLD_FLG "
                + "OR ( :DDRHLD.HLD_FLG = ' ' ) ) "
                + "AND ( EFF_DATE BETWEEN :WS_FO_DATE "
                + "AND :WS_TO_DATE )";
            IBS.GROUP(SCCGWA, DDRHLD, this, DDTHLD_RD);
        } else if (WS_TEMP.WS_BROWSE_H_FLG == 'H') {
            CEP.TRC(SCCGWA, "WODETIAN");
            DDTHLD_RD = new DBParm();
            DDTHLD_RD.TableName = "DDTHLD";
            DDTHLD_RD.set = "WS-COUNT=COUNT(*)";
            DDTHLD_RD.where = "AC = :DDRHLD.AC "
                + "AND SPR_BR_TYP = '1' "
                + "AND ( HLD_STS = 'N' "
                + "OR HLD_STS = 'W' ) "
                + "AND ( ORG_TYPE = '1' "
                + "OR ORG_TYPE = '3' "
                + "OR ORG_TYPE = ' ' ) "
                + "AND ( HLD_FLG = :DDRHLD.HLD_FLG "
                + "OR ( :DDRHLD.HLD_FLG = ' ' ) ) "
                + "AND ( EFF_DATE BETWEEN :WS_FO_DATE "
                + "AND :WS_TO_DATE )";
            IBS.GROUP(SCCGWA, DDRHLD, this, DDTHLD_RD);
        } else if (WS_TEMP.WS_BROWSE_H_FLG == 'I') {
            DDTHLD_RD = new DBParm();
            DDTHLD_RD.TableName = "DDTHLD";
            DDTHLD_RD.set = "WS-COUNT=COUNT(*)";
            DDTHLD_RD.where = "AC = :DDRHLD.AC "
                + "AND SPR_BR_TYP = '1' "
                + "AND ( HLD_STS = 'N' "
                + "OR HLD_STS = 'W' ) "
                + "AND ( ORG_TYPE = :DDRHLD.ORG_TYPE ) "
                + "AND ( HLD_FLG = :DDRHLD.HLD_FLG "
                + "OR ( :DDRHLD.HLD_FLG = ' ' ) ) "
                + "AND ( EFF_DATE BETWEEN :WS_FO_DATE "
                + "AND :WS_TO_DATE )";
            IBS.GROUP(SCCGWA, DDRHLD, this, DDTHLD_RD);
        } else if (WS_TEMP.WS_BROWSE_H_FLG == 'J') {
            DDTHLD_RD = new DBParm();
            DDTHLD_RD.TableName = "DDTHLD";
            DDTHLD_RD.set = "WS-COUNT=COUNT(*)";
            DDTHLD_RD.where = "( AC = :DDRHLD.AC "
                + "OR CARD_NO = :DDRHLD.CARD_NO ) "
                + "AND SPR_BR_TYP = '1' "
                + "AND ( HLD_STS = 'N' "
                + "OR HLD_STS = 'W' ) "
                + "AND ( HLD_FLG = :DDRHLD.HLD_FLG "
                + "OR ( :DDRHLD.HLD_FLG = ' ' ) ) "
                + "AND ( EFF_DATE BETWEEN :WS_FO_DATE "
                + "AND :WS_TO_DATE )";
            IBS.GROUP(SCCGWA, DDRHLD, this, DDTHLD_RD);
        } else {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
            WS_ERR_INFO = "WS-BROWSE-H-FLG=" + WS_TEMP.WS_BROWSE_H_FLG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_COUNT);
        WS_COUNT_H = (short) WS_COUNT;
        CEP.TRC(SCCGWA, WS_COUNT_H);
    }
    public void T000_COUNT_DDTFBID() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRFBID);
        DDRFBID.AC = WS_TR_AC;
        DDRFBID.KEY.REF_NO = DDCSQHLD.HLD_NO;
        DDRHLD.ORG_TYPE = DDCSQHLD.ORG_TYPE;
        CEP.TRC(SCCGWA, DDRFBID.AC);
        CEP.TRC(SCCGWA, DDRFBID.KEY.REF_NO);
        CEP.TRC(SCCGWA, DDRHLD.ORG_TYPE);
        CEP.TRC(SCCGWA, WS_FO_DATE);
        CEP.TRC(SCCGWA, WS_TO_DATE);
        if (WS_TEMP.WS_BROWSE_F_FLG == 'A') {
            DDTFBID_RD = new DBParm();
            DDTFBID_RD.TableName = "DDTFBID";
            DDTFBID_RD.set = "WS-COUNT=COUNT(*)";
            DDTFBID_RD.where = "REF_NO = :DDRFBID.KEY.REF_NO "
                + "AND TYPE = '1' "
                + "AND ORG_TYP = '2' "
                + "AND ( ORG_TYPE = '1' "
                + "OR ORG_TYPE = '3' "
                + "OR ORG_TYPE = ' ' ) "
                + "AND ( EFF_DATE BETWEEN :WS_FO_DATE "
                + "AND :WS_TO_DATE )";
            IBS.GROUP(SCCGWA, DDRFBID, this, DDTFBID_RD);
        } else if (WS_TEMP.WS_BROWSE_F_FLG == 'B') {
            DDTFBID_RD = new DBParm();
            DDTFBID_RD.TableName = "DDTFBID";
            DDTFBID_RD.set = "WS-COUNT=COUNT(*)";
            DDTFBID_RD.where = "REF_NO = :DDRFBID.KEY.REF_NO "
                + "AND TYPE = '1' "
                + "AND ORG_TYP = '2' "
                + "AND ( ORG_TYPE = :DDRHLD.ORG_TYPE ) "
                + "AND ( EFF_DATE BETWEEN :WS_FO_DATE "
                + "AND :WS_TO_DATE )";
            IBS.GROUP(SCCGWA, DDRFBID, this, DDTFBID_RD);
        } else if (WS_TEMP.WS_BROWSE_F_FLG == 'C') {
            DDTFBID_RD = new DBParm();
            DDTFBID_RD.TableName = "DDTFBID";
            DDTFBID_RD.set = "WS-COUNT=COUNT(*)";
            DDTFBID_RD.where = "REF_NO = :DDRFBID.KEY.REF_NO "
                + "AND TYPE = '1' "
                + "AND ORG_TYP = '2' "
                + "AND ( EFF_DATE BETWEEN :WS_FO_DATE "
                + "AND :WS_TO_DATE )";
            IBS.GROUP(SCCGWA, DDRFBID, this, DDTFBID_RD);
        } else if (WS_TEMP.WS_BROWSE_F_FLG == 'H') {
            DDTFBID_RD = new DBParm();
            DDTFBID_RD.TableName = "DDTFBID";
            DDTFBID_RD.set = "WS-COUNT=COUNT(*)";
            DDTFBID_RD.where = "AC = :DDRFBID.AC "
                + "AND TYPE = '1' "
                + "AND ORG_TYP = '2' "
                + "AND ( STS = '1' "
                + "OR STS = '3' ) "
                + "AND ( ORG_TYPE = '1' "
                + "OR ORG_TYPE = '3' "
                + "OR ORG_TYPE = ' ' ) "
                + "AND ( EFF_DATE BETWEEN :WS_FO_DATE "
                + "AND :WS_TO_DATE )";
            IBS.GROUP(SCCGWA, DDRFBID, this, DDTFBID_RD);
        } else if (WS_TEMP.WS_BROWSE_F_FLG == 'I') {
            DDTFBID_RD = new DBParm();
            DDTFBID_RD.TableName = "DDTFBID";
            DDTFBID_RD.set = "WS-COUNT=COUNT(*)";
            DDTFBID_RD.where = "AC = :DDRFBID.AC "
                + "AND TYPE = '1' "
                + "AND ORG_TYP = '2' "
                + "AND ( STS = '1' "
                + "OR STS = '3' ) "
                + "AND ( ORG_TYPE = :DDRHLD.ORG_TYPE ) "
                + "AND ( EFF_DATE BETWEEN :WS_FO_DATE "
                + "AND :WS_TO_DATE )";
            IBS.GROUP(SCCGWA, DDRFBID, this, DDTFBID_RD);
        } else if (WS_TEMP.WS_BROWSE_F_FLG == 'J') {
            DDTFBID_RD = new DBParm();
            DDTFBID_RD.TableName = "DDTFBID";
            DDTFBID_RD.set = "WS-COUNT=COUNT(*)";
            DDTFBID_RD.where = "AC = :DDRFBID.AC "
                + "AND TYPE = '1' "
                + "AND ORG_TYP = '2' "
                + "AND ( EFF_DATE BETWEEN :WS_FO_DATE "
                + "AND :WS_TO_DATE )";
            IBS.GROUP(SCCGWA, DDRFBID, this, DDTFBID_RD);
        } else if (WS_TEMP.WS_BROWSE_F_FLG == 'K') {
            WS_COUNT = 0;
            IBS.init(SCCGWA, CIRACAC);
            CIRACAC.AGR_NO = DDCSQHLD.AC;
            CEP.TRC(SCCGWA, DDCSQHLD.AC);
            CEP.TRC(SCCGWA, CIRACAC.AGR_NO);
            T000_STARTBR_CITACAC_BY_AC();
            if (pgmRtn) return;
            T000_READNEXT_CITACAC();
            if (pgmRtn) return;
            while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
                DDRFBID.AC = CIRACAC.KEY.ACAC_NO;
                DDTFBID_RD = new DBParm();
                DDTFBID_RD.TableName = "DDTFBID";
                DDTFBID_RD.set = "WS-COUNT-DF=COUNT(*)";
                DDTFBID_RD.where = "AC = :DDRFBID.AC "
                    + "AND TYPE = '1' "
                    + "AND ORG_TYP = '2' "
                    + "AND ( EFF_DATE BETWEEN :WS_FO_DATE "
                    + "AND :WS_TO_DATE )";
                IBS.GROUP(SCCGWA, DDRFBID, this, DDTFBID_RD);
                WS_COUNT = WS_COUNT + WS_COUNT_DF;
                CEP.TRC(SCCGWA, WS_COUNT_DF);
                CEP.TRC(SCCGWA, WS_COUNT);
                CEP.TRC(SCCGWA, WS_TR_AC);
                if (WS_COUNT_DF != 0 
                    && DDCSQHLD.HLD_TYP == 'W') {
                    WS_TR_AC = CIRACAC.KEY.ACAC_NO;
                    CEP.TRC(SCCGWA, WS_TR_AC);
                }
                T000_READNEXT_CITACAC();
                if (pgmRtn) return;
            }
            T000_ENDBR_CITACAC();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
            WS_ERR_INFO = "WS-BROWSE-F-FLG=" + WS_TEMP.WS_BROWSE_F_FLG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_COUNT);
        WS_COUNT_F = (short) WS_COUNT;
        CEP.TRC(SCCGWA, WS_COUNT_F);
    }
    public void T000_READ_DDTHLD_LAST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLD);
        DDRHLD.AC = WS_TR_AC;
        DDRHLD.ORG_TYPE = DDCSQHLD.ORG_TYPE;
        DDRHLD.HLD_FLG = DDCSQHLD.HLD_FLG;
        CEP.TRC(SCCGWA, DDRHLD.AC);
        CEP.TRC(SCCGWA, DDRHLD.ORG_TYPE);
        CEP.TRC(SCCGWA, DDRHLD.HLD_FLG);
        CEP.TRC(SCCGWA, WS_FO_DATE);
        CEP.TRC(SCCGWA, WS_TO_DATE);
        if (WS_TEMP.WS_BROWSE_H_FLG == 'O') {
            DDTHLD_RD = new DBParm();
            DDTHLD_RD.TableName = "DDTHLD";
            DDTHLD_RD.where = "AC = :DDRHLD.AC "
                + "AND SPR_BR_TYP = '1' "
                + "AND ( ORG_TYPE = '1' "
                + "OR ORG_TYPE = '3' "
                + "OR ORG_TYPE = ' ' ) "
                + "AND ( HLD_FLG = :DDRHLD.HLD_FLG "
                + "OR ( :DDRHLD.HLD_FLG = ' ' ) ) "
                + "AND ( EFF_DATE BETWEEN :WS_FO_DATE "
                + "AND :WS_TO_DATE ) "
                + "AND ( HLD_STS = 'N' "
                + "OR HLD_STS = 'W' )";
            DDTHLD_RD.fst = true;
            DDTHLD_RD.order = "UPDTBL_DATE DESC,UPDTBL_TIME DESC";
            IBS.READ(SCCGWA, DDRHLD, this, DDTHLD_RD);
        } else if (WS_TEMP.WS_BROWSE_H_FLG == 'P') {
            DDTHLD_RD = new DBParm();
            DDTHLD_RD.TableName = "DDTHLD";
            DDTHLD_RD.where = "AC = :DDRHLD.AC "
                + "AND SPR_BR_TYP = '1' "
                + "AND ( ORG_TYPE = :DDRHLD.ORG_TYPE ) "
                + "AND ( HLD_FLG = :DDRHLD.HLD_FLG ) "
                + "AND ( EFF_DATE BETWEEN :WS_FO_DATE "
                + "AND :WS_TO_DATE ) "
                + "AND ( HLD_STS = 'N' "
                + "OR HLD_STS = 'W' )";
            DDTHLD_RD.fst = true;
            DDTHLD_RD.order = "UPDTBL_DATE DESC,UPDTBL_TIME DESC";
            IBS.READ(SCCGWA, DDRHLD, this, DDTHLD_RD);
        } else {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
            WS_ERR_INFO = "WS-BROWSE-H-FLG=" + WS_TEMP.WS_BROWSE_H_FLG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "==DDTHLD FOUND==");
            WS_DDTHLD_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "==DDTHLD NOT FOUND==");
            WS_DDTHLD_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTHLD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTFBID_LAST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRFBID);
        DDRFBID.AC = WS_TR_AC;
        DDRFBID.ORG_TYPE = DDCSQHLD.ORG_TYPE;
        CEP.TRC(SCCGWA, DDRFBID.AC);
        CEP.TRC(SCCGWA, DDRFBID.ORG_TYPE);
        CEP.TRC(SCCGWA, WS_FO_DATE);
        CEP.TRC(SCCGWA, WS_TO_DATE);
        if (WS_TEMP.WS_BROWSE_F_FLG == 'Q') {
            DDTFBID_RD = new DBParm();
            DDTFBID_RD.TableName = "DDTFBID";
            DDTFBID_RD.where = "AC = :DDRFBID.AC "
                + "AND TYPE = '1' "
                + "AND ORG_TYP = '2' "
                + "AND ( ORG_TYPE = '1' "
                + "OR ORG_TYPE = '3' "
                + "OR ORG_TYPE = ' ' ) "
                + "AND ( EFF_DATE BETWEEN :WS_FO_DATE "
                + "AND :WS_TO_DATE )";
            DDTFBID_RD.fst = true;
            DDTFBID_RD.order = "UPDTBL_DATE DESC,UPDTBL_TIME DESC";
            IBS.READ(SCCGWA, DDRFBID, this, DDTFBID_RD);
        } else if (WS_TEMP.WS_BROWSE_F_FLG == 'R') {
            DDTFBID_RD = new DBParm();
            DDTFBID_RD.TableName = "DDTFBID";
            DDTFBID_RD.where = "AC = :DDRFBID.AC "
                + "AND TYPE = '1' "
                + "AND ORG_TYP = '2' "
                + "AND ( ORG_TYPE = :DDRHLD.ORG_TYPE ) "
                + "AND ( EFF_DATE BETWEEN :WS_FO_DATE "
                + "AND :WS_TO_DATE )";
            DDTFBID_RD.fst = true;
            DDTFBID_RD.order = "UPDTBL_DATE DESC,UPDTBL_TIME DESC";
            IBS.READ(SCCGWA, DDRFBID, this, DDTFBID_RD);
        } else {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
            WS_ERR_INFO = "WS-BROWSE-F-FLG=" + WS_TEMP.WS_BROWSE_F_FLG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "==DDTFBID FOUND==");
            WS_DDTFBID_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "==DDTFBID NOT FOUND==");
            WS_DDTFBID_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTFBID";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DDTHLD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLD);
        DDRHLD.AC = WS_TR_AC;
        DDRHLD.CARD_NO = DDCSQHLD.AC;
        DDRHLD.KEY.HLD_NO = DDCSQHLD.HLD_NO;
        DDRHLD.ORG_TYPE = DDCSQHLD.ORG_TYPE;
        DDRHLD.HLD_FLG = DDCSQHLD.HLD_FLG;
        CEP.TRC(SCCGWA, DDRHLD.AC);
        CEP.TRC(SCCGWA, DDRHLD.CARD_NO);
        CEP.TRC(SCCGWA, DDRHLD.KEY.HLD_NO);
        CEP.TRC(SCCGWA, DDRHLD.ORG_TYPE);
        CEP.TRC(SCCGWA, DDRHLD.HLD_FLG);
        CEP.TRC(SCCGWA, WS_FO_DATE);
        CEP.TRC(SCCGWA, WS_TO_DATE);
        if (WS_TEMP.WS_BROWSE_H_FLG == 'A') {
            DDTHLD_BR.rp = new DBParm();
            DDTHLD_BR.rp.TableName = "DDTHLD";
            DDTHLD_BR.rp.where = "HLD_NO = :DDRHLD.KEY.HLD_NO "
                + "AND SPR_BR_TYP = '1' "
                + "AND ( ORG_TYPE = '1' "
                + "OR ORG_TYPE = '3' "
                + "OR ORG_TYPE = ' ' ) "
                + "AND ( HLD_FLG = :DDRHLD.HLD_FLG "
                + "OR ( :DDRHLD.HLD_FLG = ' ' ) ) "
                + "AND ( EFF_DATE BETWEEN :WS_FO_DATE "
                + "AND :WS_TO_DATE )";
            IBS.STARTBR(SCCGWA, DDRHLD, this, DDTHLD_BR);
        } else if (WS_TEMP.WS_BROWSE_H_FLG == 'B') {
            DDTHLD_BR.rp = new DBParm();
            DDTHLD_BR.rp.TableName = "DDTHLD";
            DDTHLD_BR.rp.where = "HLD_NO = :DDRHLD.KEY.HLD_NO "
                + "AND SPR_BR_TYP = '1' "
                + "AND ( ORG_TYPE = :DDRHLD.ORG_TYPE ) "
                + "AND ( HLD_FLG = :DDRHLD.HLD_FLG "
                + "OR ( :DDRHLD.HLD_FLG = ' ' ) ) "
                + "AND ( EFF_DATE BETWEEN :WS_FO_DATE "
                + "AND :WS_TO_DATE )";
            IBS.STARTBR(SCCGWA, DDRHLD, this, DDTHLD_BR);
        } else if (WS_TEMP.WS_BROWSE_H_FLG == 'C') {
            DDTHLD_BR.rp = new DBParm();
            DDTHLD_BR.rp.TableName = "DDTHLD";
            DDTHLD_BR.rp.where = "HLD_NO = :DDRHLD.KEY.HLD_NO "
                + "AND SPR_BR_TYP = '1' "
                + "AND ( HLD_FLG = :DDRHLD.HLD_FLG "
                + "OR ( :DDRHLD.HLD_FLG = ' ' ) ) "
                + "AND ( EFF_DATE BETWEEN :WS_FO_DATE "
                + "AND :WS_TO_DATE )";
            IBS.STARTBR(SCCGWA, DDRHLD, this, DDTHLD_BR);
        } else if (WS_TEMP.WS_BROWSE_H_FLG == 'H') {
            CEP.TRC(SCCGWA, "1111");
            DDTHLD_BR.rp = new DBParm();
            DDTHLD_BR.rp.TableName = "DDTHLD";
            DDTHLD_BR.rp.where = "AC = :DDRHLD.AC "
                + "AND SPR_BR_TYP = '1' "
                + "AND ( HLD_STS = 'N' "
                + "OR HLD_STS = 'W' ) "
                + "AND ( ORG_TYPE = '1' "
                + "OR ORG_TYPE = '3' "
                + "OR ORG_TYPE = ' ' ) "
                + "AND ( HLD_FLG = :DDRHLD.HLD_FLG "
                + "OR ( :DDRHLD.HLD_FLG = ' ' ) ) "
                + "AND ( EFF_DATE BETWEEN :WS_FO_DATE "
                + "AND :WS_TO_DATE )";
            IBS.STARTBR(SCCGWA, DDRHLD, this, DDTHLD_BR);
        } else if (WS_TEMP.WS_BROWSE_H_FLG == 'I') {
            CEP.TRC(SCCGWA, "2222");
            DDTHLD_BR.rp = new DBParm();
            DDTHLD_BR.rp.TableName = "DDTHLD";
            DDTHLD_BR.rp.where = "AC = :DDRHLD.AC "
                + "AND SPR_BR_TYP = '1' "
                + "AND ( HLD_STS = 'N' "
                + "OR HLD_STS = 'W' ) "
                + "AND ( ORG_TYPE = :DDRHLD.ORG_TYPE ) "
                + "AND ( HLD_FLG = :DDRHLD.HLD_FLG "
                + "OR ( :DDRHLD.HLD_FLG = ' ' ) ) "
                + "AND ( EFF_DATE BETWEEN :WS_FO_DATE "
                + "AND :WS_TO_DATE )";
            IBS.STARTBR(SCCGWA, DDRHLD, this, DDTHLD_BR);
        } else if (WS_TEMP.WS_BROWSE_H_FLG == 'J') {
            CEP.TRC(SCCGWA, "3333");
            DDTHLD_BR.rp = new DBParm();
            DDTHLD_BR.rp.TableName = "DDTHLD";
            DDTHLD_BR.rp.where = "( AC = :DDRHLD.AC "
                + "OR CARD_NO = :DDRHLD.CARD_NO ) "
                + "AND SPR_BR_TYP = '1' "
                + "AND ( HLD_STS = 'N' "
                + "OR HLD_STS = 'W' ) "
                + "AND ( HLD_FLG = :DDRHLD.HLD_FLG "
                + "OR ( :DDRHLD.HLD_FLG = ' ' ) ) "
                + "AND ( EFF_DATE BETWEEN :WS_FO_DATE "
                + "AND :WS_TO_DATE )";
            IBS.STARTBR(SCCGWA, DDRHLD, this, DDTHLD_BR);
        } else {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
            WS_ERR_INFO = "WS-BROWSE-H-FLG=" + WS_TEMP.WS_BROWSE_H_FLG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_STARTBR_DDTFBID() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRFBID);
        DDRFBID.AC = WS_TR_AC;
        DDRFBID.KEY.REF_NO = DDCSQHLD.HLD_NO;
        DDRHLD.ORG_TYPE = DDCSQHLD.ORG_TYPE;
        CEP.TRC(SCCGWA, DDRFBID.AC);
        CEP.TRC(SCCGWA, DDRFBID.KEY.REF_NO);
        CEP.TRC(SCCGWA, DDRFBID.ORG_TYPE);
        CEP.TRC(SCCGWA, WS_FO_DATE);
        CEP.TRC(SCCGWA, WS_TO_DATE);
        CEP.TRC(SCCGWA, WS_TEMP.WS_BROWSE_H_FLG);
        CEP.TRC(SCCGWA, WS_TEMP.WS_BROWSE_F_FLG);
        if (WS_TEMP.WS_BROWSE_F_FLG == 'A') {
            DDTFBID_BR.rp = new DBParm();
            DDTFBID_BR.rp.TableName = "DDTFBID";
            DDTFBID_BR.rp.where = "REF_NO = :DDRFBID.KEY.REF_NO "
                + "AND TYPE = '1' "
                + "AND ORG_TYP = '2' "
                + "AND ( ORG_TYPE = '1' "
                + "OR ORG_TYPE = '3' "
                + "OR ORG_TYPE = ' ' ) "
                + "AND ( EFF_DATE BETWEEN :WS_FO_DATE "
                + "AND :WS_TO_DATE )";
            IBS.STARTBR(SCCGWA, DDRFBID, this, DDTFBID_BR);
        } else if (WS_TEMP.WS_BROWSE_F_FLG == 'B') {
            DDTFBID_BR.rp = new DBParm();
            DDTFBID_BR.rp.TableName = "DDTFBID";
            DDTFBID_BR.rp.where = "REF_NO = :DDRFBID.KEY.REF_NO "
                + "AND TYPE = '1' "
                + "AND ORG_TYP = '2' "
                + "AND ( ORG_TYPE = :DDRHLD.ORG_TYPE ) "
                + "AND ( EFF_DATE BETWEEN :WS_FO_DATE "
                + "AND :WS_TO_DATE )";
            IBS.STARTBR(SCCGWA, DDRFBID, this, DDTFBID_BR);
        } else if (WS_TEMP.WS_BROWSE_F_FLG == 'C') {
            DDTFBID_BR.rp = new DBParm();
            DDTFBID_BR.rp.TableName = "DDTFBID";
            DDTFBID_BR.rp.where = "REF_NO = :DDRFBID.KEY.REF_NO "
                + "AND TYPE = '1' "
                + "AND ORG_TYP = '2' "
                + "AND ( EFF_DATE BETWEEN :WS_FO_DATE "
                + "AND :WS_TO_DATE )";
            IBS.STARTBR(SCCGWA, DDRFBID, this, DDTFBID_BR);
        } else if (WS_TEMP.WS_BROWSE_F_FLG == 'H') {
            DDTFBID_BR.rp = new DBParm();
            DDTFBID_BR.rp.TableName = "DDTFBID";
            DDTFBID_BR.rp.where = "AC = :DDRFBID.AC "
                + "AND TYPE = '1' "
                + "AND ORG_TYP = '2' "
                + "AND ( STS = '1' "
                + "OR STS = '3' ) "
                + "AND ( ORG_TYPE = '1' "
                + "OR ORG_TYPE = '3' "
                + "OR ORG_TYPE = ' ' ) "
                + "AND ( EFF_DATE BETWEEN :WS_FO_DATE "
                + "AND :WS_TO_DATE )";
            IBS.STARTBR(SCCGWA, DDRFBID, this, DDTFBID_BR);
        } else if (WS_TEMP.WS_BROWSE_F_FLG == 'I') {
            DDTFBID_BR.rp = new DBParm();
            DDTFBID_BR.rp.TableName = "DDTFBID";
            DDTFBID_BR.rp.where = "AC = :DDRFBID.AC "
                + "AND TYPE = '1' "
                + "AND ORG_TYP = '2' "
                + "AND ( STS = '1' "
                + "OR STS = '3' ) "
                + "AND ( ORG_TYPE = :DDRHLD.ORG_TYPE ) "
                + "AND ( EFF_DATE BETWEEN :WS_FO_DATE "
                + "AND :WS_TO_DATE )";
            IBS.STARTBR(SCCGWA, DDRFBID, this, DDTFBID_BR);
        } else if (WS_TEMP.WS_BROWSE_F_FLG == 'J') {
            DDTFBID_BR.rp = new DBParm();
            DDTFBID_BR.rp.TableName = "DDTFBID";
            DDTFBID_BR.rp.where = "AC = :DDRFBID.AC "
                + "AND TYPE = '1' "
                + "AND ORG_TYP = '2' "
                + "AND ( EFF_DATE BETWEEN :WS_FO_DATE "
                + "AND :WS_TO_DATE )";
            IBS.STARTBR(SCCGWA, DDRFBID, this, DDTFBID_BR);
        } else if (WS_TEMP.WS_BROWSE_F_FLG == 'K') {
            DDTFBID_BR.rp = new DBParm();
            DDTFBID_BR.rp.TableName = "DDTFBID";
            DDTFBID_BR.rp.where = "AC = :DDRFBID.AC "
                + "AND TYPE = '1' "
                + "AND ORG_TYP = '2' "
                + "AND ( EFF_DATE BETWEEN :WS_FO_DATE "
                + "AND :WS_TO_DATE )";
            IBS.STARTBR(SCCGWA, DDRFBID, this, DDTFBID_BR);
        } else {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
            WS_ERR_INFO = "WS-BROWSE-F-FLG=" + WS_TEMP.WS_BROWSE_F_FLG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READNEXT_DDTHLD_FIRST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_RCD_SEQ);
        IBS.READNEXT(SCCGWA, DDRHLD, this, DDTHLD_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTHLD_FLG = 'Y';
        } else {
            WS_DDTHLD_FLG = 'N';
        }
    }
    public void T000_READNEXT_DDTFBID_FIRST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_RCD_SEQ);
        IBS.READNEXT(SCCGWA, DDRFBID, this, DDTFBID_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTFBID_FLG = 'Y';
        } else {
            WS_DDTFBID_FLG = 'N';
        }
    }
    public void T000_READNEXT_DDTHLD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_RCD_SEQ);
        IBS.READNEXT(SCCGWA, DDRHLD, this, DDTHLD_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTHLD_FLG = 'Y';
        } else {
            WS_DDTHLD_FLG = 'N';
        }
    }
    public void T000_READNEXT_DDTFBID() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_RCD_SEQ);
        IBS.READNEXT(SCCGWA, DDRFBID, this, DDTFBID_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTFBID_FLG = 'Y';
        } else {
            WS_DDTFBID_FLG = 'N';
        }
    }
    public void T000_ENDBR_DDTHLD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTHLD_BR);
    }
    public void T000_ENDBR_DDTFBID() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTFBID_BR);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACAC.RC);
        }
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
    }
    public void T000_STARTBR_CITACAC_BY_AC() throws IOException,SQLException,Exception {
        CITACAC_BR.rp = new DBParm();
        CITACAC_BR.rp.TableName = "CITACAC";
        CITACAC_BR.rp.where = "AGR_NO = :CIRACAC.AGR_NO "
            + "AND ACAC_STS = '0'";
        CITACAC_BR.rp.order = "AGR_SEQ";
        IBS.STARTBR(SCCGWA, CIRACAC, this, CITACAC_BR);
    }
    public void T000_READNEXT_CITACAC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRACAC, this, CITACAC_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "==CITACAC FOUND==");
            WS_CITACAC_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "==ACAC NOT FOUND==");
            WS_CITACAC_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTHLD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_CITACAC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITACAC_BR);
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
