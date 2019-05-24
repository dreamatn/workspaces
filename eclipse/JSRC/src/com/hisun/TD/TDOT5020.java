package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT5020 {
    int JIBS_tmp_int;
    DBParm TDTRATE_RD;
    brParm TDTRACD_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    brParm TDTRATE_BR = new brParm();
    brParm TDTIREV_BR = new brParm();
    DBParm TDTRACD_RD;
    boolean pgmRtn = false;
    TDOT5020_WS_VARIABLES WS_VARIABLES = new TDOT5020_WS_VARIABLES();
    TDOT5020_WS_COND_FLG WS_COND_FLG = new TDOT5020_WS_COND_FLG();
    short WS_TIME = 0;
    short WS_TIME2 = 0;
    short WS_TIME3 = 0;
    short WS_TIME4 = 0;
    short WS_TIME5 = 0;
    short WS_TIME6 = 0;
    TDOT5020_WS_LIST WS_LIST = new TDOT5020_WS_LIST();
    TDCMSG_ERROR_MSG ERROR_MSG = new TDCMSG_ERROR_MSG();
    TDRRATE TDRRATE = new TDRRATE();
    TDRRACD TDRRACD = new TDRRACD();
    TDRIREV TDRIREV = new TDRIREV();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    TDB5020_AWA_5020 AWA_5020;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDOT5020 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        AWA_5020 = new TDB5020_AWA_5020();
        IBS.init(SCCGWA, AWA_5020);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWA_AREA_PTR, AWA_5020);
        IBS.init(SCCGWA, WS_VARIABLES);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AWA_5020.FUNC);
        if (AWA_5020.FUNC == 'I') {
            B020_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B021_READ_TABLE();
            if (pgmRtn) return;
        } else if (AWA_5020.FUNC == 'A') {
            B010_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B052_ADD_PROCESS();
            if (pgmRtn) return;
        } else if (AWA_5020.FUNC == 'M') {
            B010_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B090_DELETE_PROCESS();
            if (pgmRtn) return;
            B052_ADD_PROCESS();
            if (pgmRtn) return;
        } else if (AWA_5020.FUNC == 'D') {
            B010_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B090_DELETE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.TD_DOCU_FUNC_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (AWA_5020.RUL_CD.trim().length() == 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.TD_KD_FU_SPACE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, AWA_5020.RUL_CD);
            B022_READ_TDTRATE();
            if (pgmRtn) return;
            if (WS_COND_FLG.FOUND_FLG == 'Y') {
                if (AWA_5020.FUNC == 'A') {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.TD_FU_HAVE;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            } else {
                if (AWA_5020.FUNC != 'A') {
                    WS_VARIABLES.ERR_MSG = ERROR_MSG.TD_FU_NOT_HAVE;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        if (AWA_5020.FUNC != 'D') {
            WS_COND_FLG.FOUND_FLG = ' ';
            TDRRACD.KEY.RUL_CD = AWA_5020.RUL_CD;
            TDRRACD.KEY.NUM = 1;
            B230_STARTBR_TDTRACD();
            if (pgmRtn) return;
            B050_READNEXT_TDTRACD();
            if (pgmRtn) return;
            if (WS_COND_FLG.FOUND_FLG == 'Y') {
                CEP.TRC(SCCGWA, WS_COND_FLG.FOUND_FLG);
                WS_COND_FLG.FOUND_FLG = ' ';
                WS_TIME5 = 0;
                WS_TIME5 += 1;
                while (WS_TIME5 <= 30 
                    && AWA_5020.OC_RACD[WS_TIME5-1].NUM != 0) {
                    WS_TIME = 0;
                    while (WS_TIME <= 30 
                        && WS_TIME4 != 2) {
                        WS_TIME4 = 0;
                        WS_TIME6 = 0;
                        WS_TIME += 1;
                        if (AWA_5020.OC_RACD[WS_TIME5-1].BAL != TDRRACD.BAL) {
                            WS_TIME6 += 1;
                        }
                        if (!AWA_5020.OC_RACD[WS_TIME5-1].TERM.equalsIgnoreCase(TDRRACD.TERM)) {
                            WS_TIME6 += 1;
                        }
                        if (!AWA_5020.OC_RACD[WS_TIME5-1].GRPS_NO.equalsIgnoreCase(TDRRACD.GRPS_NO)) {
                            WS_TIME6 += 1;
                        }
                        if (AWA_5020.OC_RACD[WS_TIME5-1].BR != TDRRACD.BR) {
                            WS_TIME6 += 1;
                        }
                        if (AWA_5020.OC_RACD[WS_TIME5-1].AGE != TDRRACD.AGE) {
                            WS_TIME6 += 1;
                        }
                        if (AWA_5020.OC_RACD[WS_TIME5-1].GENDER != TDRRACD.GENDER) {
                            WS_TIME6 += 1;
                        }
                        if (!AWA_5020.OC_RACD[WS_TIME5-1].CHNL_NO.equalsIgnoreCase(TDRRACD.CHNL_NO)) {
                            WS_TIME6 += 1;
                        }
                        WS_TIME2 = 0;
                        if (WS_TIME6 == 7) {
                            while (WS_TIME2 <= 19 
                                && WS_TIME4 != 2) {
                                if (WS_TIME2 == 0) {
                                    WS_TIME2 += 1;
                                } else {
                                    WS_TIME2 += 2;
                                }
                                WS_TIME3 = 0;
                                while (WS_TIME3 <= 19 
                                    && WS_TIME4 != 2) {
                                    if (WS_TIME3 == 0) {
                                        WS_TIME3 += 1;
                                    } else {
                                        WS_TIME3 += 2;
                                    }
                                    if (TDRRACD.SVR_LVL == null) TDRRACD.SVR_LVL = "";
                                    JIBS_tmp_int = TDRRACD.SVR_LVL.length();
                                    for (int i=0;i<20-JIBS_tmp_int;i++) TDRRACD.SVR_LVL += " ";
                                    if (AWA_5020.OC_RACD[WS_TIME5-1].SVR_LVL == null) AWA_5020.OC_RACD[WS_TIME5-1].SVR_LVL = "";
                                    JIBS_tmp_int = AWA_5020.OC_RACD[WS_TIME5-1].SVR_LVL.length();
                                    for (int i=0;i<20-JIBS_tmp_int;i++) AWA_5020.OC_RACD[WS_TIME5-1].SVR_LVL += " ";
                                    if (TDRRACD.SVR_LVL.substring(WS_TIME2 - 1, WS_TIME2 + 2 - 1).equalsIgnoreCase(AWA_5020.OC_RACD[WS_TIME5-1].SVR_LVL.substring(WS_TIME3 - 1, WS_TIME3 + 2 - 1)) 
                                        && WS_COND_FLG.FOUND_FLG != 'Y') {
                                        if (AWA_5020.OC_RACD[WS_TIME5-1].SVR_LVL == null) AWA_5020.OC_RACD[WS_TIME5-1].SVR_LVL = "";
                                        JIBS_tmp_int = AWA_5020.OC_RACD[WS_TIME5-1].SVR_LVL.length();
                                        for (int i=0;i<20-JIBS_tmp_int;i++) AWA_5020.OC_RACD[WS_TIME5-1].SVR_LVL += " ";
                                        if (TDRRACD.SVR_LVL == null) TDRRACD.SVR_LVL = "";
                                        JIBS_tmp_int = TDRRACD.SVR_LVL.length();
                                        for (int i=0;i<20-JIBS_tmp_int;i++) TDRRACD.SVR_LVL += " ";
                                        if (AWA_5020.OC_RACD[WS_TIME5-1].SVR_LVL == null) AWA_5020.OC_RACD[WS_TIME5-1].SVR_LVL = "";
                                        JIBS_tmp_int = AWA_5020.OC_RACD[WS_TIME5-1].SVR_LVL.length();
                                        for (int i=0;i<20-JIBS_tmp_int;i++) AWA_5020.OC_RACD[WS_TIME5-1].SVR_LVL += " ";
                                        if (TDRRACD.SVR_LVL == null) TDRRACD.SVR_LVL = "";
                                        JIBS_tmp_int = TDRRACD.SVR_LVL.length();
                                        for (int i=0;i<20-JIBS_tmp_int;i++) TDRRACD.SVR_LVL += " ";
                                        if (AWA_5020.OC_RACD[WS_TIME5-1].SVR_LVL == null) AWA_5020.OC_RACD[WS_TIME5-1].SVR_LVL = "";
                                        JIBS_tmp_int = AWA_5020.OC_RACD[WS_TIME5-1].SVR_LVL.length();
                                        for (int i=0;i<20-JIBS_tmp_int;i++) AWA_5020.OC_RACD[WS_TIME5-1].SVR_LVL += " ";
                                        if (((AWA_5020.OC_RACD[WS_TIME5-1].SVR_LVL.substring(WS_TIME3 - 1, WS_TIME3 + 2 - 1).trim().length() == 0 
                                            || TDRRACD.SVR_LVL.substring(WS_TIME2 - 1, WS_TIME2 + 2 - 1).trim().length() == 0) 
                                            && AWA_5020.OC_RACD[WS_TIME5-1].SVR_LVL.substring(0, 2).trim().length() == 0 
                                            && TDRRACD.SVR_LVL.substring(0, 2).trim().length() == 0) 
                                            || AWA_5020.OC_RACD[WS_TIME5-1].SVR_LVL.substring(WS_TIME3 - 1, WS_TIME3 + 2 - 1).trim().length() > 0) {
                                            WS_TIME4 += 1;
                                            WS_COND_FLG.FOUND_FLG = 'Y';
                                        }
                                    }
                                    if (TDRRACD.ASS_LVL == null) TDRRACD.ASS_LVL = "";
                                    JIBS_tmp_int = TDRRACD.ASS_LVL.length();
                                    for (int i=0;i<20-JIBS_tmp_int;i++) TDRRACD.ASS_LVL += " ";
                                    if (AWA_5020.OC_RACD[WS_TIME5-1].ASS_LVL == null) AWA_5020.OC_RACD[WS_TIME5-1].ASS_LVL = "";
                                    JIBS_tmp_int = AWA_5020.OC_RACD[WS_TIME5-1].ASS_LVL.length();
                                    for (int i=0;i<20-JIBS_tmp_int;i++) AWA_5020.OC_RACD[WS_TIME5-1].ASS_LVL += " ";
                                    if (TDRRACD.ASS_LVL.substring(WS_TIME2 - 1, WS_TIME2 + 2 - 1).equalsIgnoreCase(AWA_5020.OC_RACD[WS_TIME5-1].ASS_LVL.substring(WS_TIME3 - 1, WS_TIME3 + 2 - 1)) 
                                        && WS_COND_FLG.FOUND_FLG != 'C') {
                                        if (AWA_5020.OC_RACD[WS_TIME5-1].ASS_LVL == null) AWA_5020.OC_RACD[WS_TIME5-1].ASS_LVL = "";
                                        JIBS_tmp_int = AWA_5020.OC_RACD[WS_TIME5-1].ASS_LVL.length();
                                        for (int i=0;i<20-JIBS_tmp_int;i++) AWA_5020.OC_RACD[WS_TIME5-1].ASS_LVL += " ";
                                        if (TDRRACD.ASS_LVL == null) TDRRACD.ASS_LVL = "";
                                        JIBS_tmp_int = TDRRACD.ASS_LVL.length();
                                        for (int i=0;i<20-JIBS_tmp_int;i++) TDRRACD.ASS_LVL += " ";
                                        if (AWA_5020.OC_RACD[WS_TIME5-1].ASS_LVL == null) AWA_5020.OC_RACD[WS_TIME5-1].ASS_LVL = "";
                                        JIBS_tmp_int = AWA_5020.OC_RACD[WS_TIME5-1].ASS_LVL.length();
                                        for (int i=0;i<20-JIBS_tmp_int;i++) AWA_5020.OC_RACD[WS_TIME5-1].ASS_LVL += " ";
                                        if (TDRRACD.ASS_LVL == null) TDRRACD.ASS_LVL = "";
                                        JIBS_tmp_int = TDRRACD.ASS_LVL.length();
                                        for (int i=0;i<20-JIBS_tmp_int;i++) TDRRACD.ASS_LVL += " ";
                                        if (AWA_5020.OC_RACD[WS_TIME5-1].ASS_LVL == null) AWA_5020.OC_RACD[WS_TIME5-1].ASS_LVL = "";
                                        JIBS_tmp_int = AWA_5020.OC_RACD[WS_TIME5-1].ASS_LVL.length();
                                        for (int i=0;i<20-JIBS_tmp_int;i++) AWA_5020.OC_RACD[WS_TIME5-1].ASS_LVL += " ";
                                        if (((AWA_5020.OC_RACD[WS_TIME5-1].ASS_LVL.substring(WS_TIME3 - 1, WS_TIME3 + 2 - 1).trim().length() == 0 
                                            || TDRRACD.ASS_LVL.substring(WS_TIME2 - 1, WS_TIME2 + 2 - 1).trim().length() == 0) 
                                            && AWA_5020.OC_RACD[WS_TIME5-1].ASS_LVL.substring(0, 2).trim().length() == 0 
                                            && TDRRACD.ASS_LVL.substring(0, 2).trim().length() == 0) 
                                            || AWA_5020.OC_RACD[WS_TIME5-1].ASS_LVL.substring(WS_TIME3 - 1, WS_TIME3 + 2 - 1).trim().length() > 0) {
                                            WS_TIME4 += 1;
                                            WS_COND_FLG.FOUND_FLG = 'C';
                                        }
                                    }
                                }
                            }
                        }
                        if (WS_TIME4 == 2) {
                            CEP.ERR(SCCGWA, ERROR_MSG.TD_FU_NOT_HAVE);
                        }
                    }
                    WS_TIME5 += 1;
                }
            }
        }
        WS_COND_FLG.FOUND_FLG = ' ';
        WS_TIME = 0;
        WS_TIME2 = 0;
        WS_TIME3 = 0;
        WS_TIME4 = 0;
        WS_TIME5 = 0;
        WS_TIME6 = 0;
    }
    public void B020_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (AWA_5020.RUL_CD.trim().length() == 0) {
            if (AWA_5020.SDT == 0 
                && AWA_5020.DDT == 0) {
                CEP.ERR(SCCGWA, ERROR_MSG.TD_KD_FU_SPACE);
            }
        }
    }
    public void B021_READ_TABLE() throws IOException,SQLException,Exception {
        if (AWA_5020.RUL_CD.trim().length() > 0) {
            TDRRATE.KEY.RUL_CD = AWA_5020.RUL_CD;
            B022_READ_TDTRATE();
            if (pgmRtn) return;
            B130_TRANS_DATA_OUTPUT1();
            if (pgmRtn) return;
            TDRRACD.KEY.RUL_CD = AWA_5020.RUL_CD;
            if (AWA_5020.SMK.trim().length() > 0) {
                if (AWA_5020.SMK == null) AWA_5020.SMK = "";
                JIBS_tmp_int = AWA_5020.SMK.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) AWA_5020.SMK += " ";
                if (AWA_5020.SMK.substring(8 - 1, 8 + 4 - 1).trim().length() == 0) TDRRACD.KEY.NUM = 0;
                else TDRRACD.KEY.NUM = Short.parseShort(AWA_5020.SMK.substring(8 - 1, 8 + 4 - 1));
            } else {
                TDRRACD.KEY.NUM = 1;
            }
            CEP.TRC(SCCGWA, TDRRACD.KEY.RUL_CD);
            CEP.TRC(SCCGWA, TDRRACD.KEY.NUM);
            B030_STARTBR_TDTRACD();
            if (pgmRtn) return;
            B051_ENDBR_TDTRACD();
            if (pgmRtn) return;
        } else {
            if (AWA_5020.SDT == 0) {
                TDRRATE.SDT = 10101;
            } else {
                TDRRATE.SDT = AWA_5020.SDT;
            }
            if (AWA_5020.DDT == 0) {
                TDRRATE.DDT = 99991231;
            } else {
                TDRRATE.DDT = AWA_5020.DDT;
            }
            if (AWA_5020.SMK.trim().length() > 0) {
                if (AWA_5020.SMK == null) AWA_5020.SMK = "";
                JIBS_tmp_int = AWA_5020.SMK.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) AWA_5020.SMK += " ";
                TDRRATE.KEY.RUL_CD = AWA_5020.SMK.substring(0, 7);
                if (AWA_5020.SMK == null) AWA_5020.SMK = "";
                JIBS_tmp_int = AWA_5020.SMK.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) AWA_5020.SMK += " ";
                if (AWA_5020.SMK.substring(8 - 1, 8 + 4 - 1).trim().length() == 0) TDRRACD.KEY.NUM = 0;
                else TDRRACD.KEY.NUM = Short.parseShort(AWA_5020.SMK.substring(8 - 1, 8 + 4 - 1));
            } else {
                TDRRATE.KEY.RUL_CD = "0000000";
                TDRRACD.KEY.NUM = 0;
            }
            T130_STARTBR_TDTRATE();
            if (pgmRtn) return;
            T140_RENEXT_TDTRATE();
            if (pgmRtn) return;
            T001_ENDBR_TDTRATE();
            if (pgmRtn) return;
            B051_ENDBR_TDTRACD();
            if (pgmRtn) return;
        }
    }
    public void B022_READ_TDTRATE() throws IOException,SQLException,Exception {
        TDRRATE.KEY.RUL_CD = AWA_5020.RUL_CD;
        TDTRATE_RD = new DBParm();
        TDTRATE_RD.TableName = "TDTRATE";
        TDTRATE_RD.upd = true;
        IBS.READ(SCCGWA, TDRRATE, TDTRATE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.FOUND_FLG = 'Y';
        }
    }
    public void B030_STARTBR_TDTRACD() throws IOException,SQLException,Exception {
        WS_COND_FLG.FOUND_FLG = ' ';
        CEP.TRC(SCCGWA, TDRRACD.KEY.RUL_CD);
        CEP.TRC(SCCGWA, TDRRACD.KEY.NUM);
        TDTRACD_BR.rp = new DBParm();
        TDTRACD_BR.rp.TableName = "TDTRACD";
        TDTRACD_BR.rp.where = "RUL_CD = :TDRRACD.KEY.RUL_CD "
            + "AND NUM >= :TDRRACD.KEY.NUM";
        TDTRACD_BR.rp.upd = true;
        TDTRACD_BR.rp.order = "NUM";
        IBS.STARTBR(SCCGWA, TDRRACD, this, TDTRACD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.FOUND_FLG = 'Y';
            B040_READNEXT_TDTRACD();
            if (pgmRtn) return;
        } else {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.TD_START_BR_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_COND_FLG.FOUND_FLG);
    }
    public void B230_STARTBR_TDTRACD() throws IOException,SQLException,Exception {
        WS_COND_FLG.FOUND_FLG = ' ';
        CEP.TRC(SCCGWA, TDRRACD.KEY.RUL_CD);
        CEP.TRC(SCCGWA, TDRRACD.KEY.NUM);
        TDTRACD_BR.rp = new DBParm();
        TDTRACD_BR.rp.TableName = "TDTRACD";
        TDTRACD_BR.rp.where = "RUL_CD = :TDRRACD.KEY.RUL_CD "
            + "AND NUM >= :TDRRACD.KEY.NUM";
        TDTRACD_BR.rp.upd = true;
        TDTRACD_BR.rp.order = "NUM";
        IBS.STARTBR(SCCGWA, TDRRACD, this, TDTRACD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (AWA_5020.FUNC != 'A') {
                WS_COND_FLG.FOUND_FLG = 'Y';
            }
        } else {
            if (AWA_5020.FUNC != 'A') {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.TD_START_BR_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, WS_COND_FLG.FOUND_FLG);
    }
    public void B040_READNEXT_TDTRACD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRRACD, this, TDTRACD_BR);
        WS_COND_FLG.FOUND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.FOUND_FLG = 'Y';
            WS_TIME2 = 1;
            while (WS_TIME2 <= 31 
                && WS_COND_FLG.FOUND_FLG != 'Y' 
                && TDRRACD.KEY.RUL_CD.equalsIgnoreCase(AWA_5020.RUL_CD)) {
                if (AWA_5020.FUNC == 'I') {
                    B130_TRANS_DATA_OUTPUT2();
                    if (pgmRtn) return;
                    B050_READNEXT_TDTRACD();
                    if (pgmRtn) return;
                } else {
                    B092_DELETE_TDTRACD();
                    if (pgmRtn) return;
                    B050_READNEXT_TDTRACD();
                    if (pgmRtn) return;
                }
                WS_TIME2 += 1;
            }
            if (WS_TIME == 31) {
                if (WS_LIST.SMK == null) WS_LIST.SMK = "";
                JIBS_tmp_int = WS_LIST.SMK.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) WS_LIST.SMK += " ";
                if (TDRRACD.KEY.RUL_CD == null) TDRRACD.KEY.RUL_CD = "";
                JIBS_tmp_int = TDRRACD.KEY.RUL_CD.length();
                for (int i=0;i<7-JIBS_tmp_int;i++) TDRRACD.KEY.RUL_CD += " ";
                WS_LIST.SMK = TDRRACD.KEY.RUL_CD + WS_LIST.SMK.substring(7);
                if (WS_LIST.SMK == null) WS_LIST.SMK = "";
                JIBS_tmp_int = WS_LIST.SMK.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) WS_LIST.SMK += " ";
                JIBS_tmp_str[0] = "" + TDRRACD.KEY.NUM;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                WS_LIST.SMK = WS_LIST.SMK.substring(0, 8 - 1) + JIBS_tmp_str[0] + WS_LIST.SMK.substring(8 + 4 - 1);
                WS_LIST.MY_FLG = 'N';
            } else {
                WS_LIST.MY_FLG = 'Y';
            }
            WS_TIME = 0;
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = OUTPUT_FMT1;
        SCCFMT.DATA_PTR = WS_LIST;
        SCCFMT.DATA_LEN = 3542;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T140_RENEXT_TDTRATE() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRRATE, this, TDTRATE_BR);
        WS_COND_FLG.FOUND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TIME2 = 0;
            WS_COND_FLG.FOUND_FLG = 'Y';
            WS_TIME2 = 1;
            while (WS_TIME2 <= 31 
                && WS_COND_FLG.FOUND_FLG != 'Y') {
                if (AWA_5020.FUNC == 'I') {
                    TDRRACD.KEY.RUL_CD = TDRRATE.KEY.RUL_CD;
                    B130_TRANS_DATA_OUTPUT1();
                    if (pgmRtn) return;
                    B030_STARTBR_TDTRACD();
                    if (pgmRtn) return;
                } else {
                    B092_DELETE_TDTRACD();
                    if (pgmRtn) return;
                    B050_READNEXT_TDTRACD();
                    if (pgmRtn) return;
                }
                WS_TIME2 += 1;
            }
            if (AWA_5020.SMK == null) AWA_5020.SMK = "";
            JIBS_tmp_int = AWA_5020.SMK.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) AWA_5020.SMK += " ";
            if (WS_TIME == 31 
                && TDRRACD.KEY.RUL_CD.equalsIgnoreCase(AWA_5020.SMK.substring(0, 7))) {
                if (WS_LIST.SMK == null) WS_LIST.SMK = "";
                JIBS_tmp_int = WS_LIST.SMK.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) WS_LIST.SMK += " ";
                if (TDRRACD.KEY.RUL_CD == null) TDRRACD.KEY.RUL_CD = "";
                JIBS_tmp_int = TDRRACD.KEY.RUL_CD.length();
                for (int i=0;i<7-JIBS_tmp_int;i++) TDRRACD.KEY.RUL_CD += " ";
                WS_LIST.SMK = TDRRACD.KEY.RUL_CD + WS_LIST.SMK.substring(7);
                if (WS_LIST.SMK == null) WS_LIST.SMK = "";
                JIBS_tmp_int = WS_LIST.SMK.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) WS_LIST.SMK += " ";
                JIBS_tmp_str[0] = "" + TDRRACD.KEY.NUM;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                WS_LIST.SMK = WS_LIST.SMK.substring(0, 8 - 1) + JIBS_tmp_str[0] + WS_LIST.SMK.substring(8 + 4 - 1);
                WS_LIST.MY_FLG = 'N';
            } else {
                WS_LIST.MY_FLG = 'Y';
            }
            WS_TIME = 0;
        }
    }
    public void B050_READNEXT_TDTRACD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRRACD, this, TDTRACD_BR);
        WS_COND_FLG.FOUND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.FOUND_FLG = 'Y';
        }
    }
    public void B051_ENDBR_TDTRACD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTRACD_BR);
        WS_COND_FLG.FOUND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.FOUND_FLG = 'Y';
        }
    }
    public void T001_ENDBR_TDTRATE() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTRATE_BR);
        WS_COND_FLG.FOUND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.FOUND_FLG = 'Y';
        }
    }
    public void T130_STARTBR_TDTRATE() throws IOException,SQLException,Exception {
        TDTRATE_BR.rp = new DBParm();
        TDTRATE_BR.rp.TableName = "TDTRATE";
        TDTRATE_BR.rp.where = "RUL_CD >= :TDRRATE.KEY.RUL_CD "
            + "AND SDT >= :TDRRATE.SDT "
            + "AND DDT <= :TDRRATE.DDT";
        TDTRATE_BR.rp.order = "RUL_CD";
        IBS.STARTBR(SCCGWA, TDRRATE, this, TDTRATE_BR);
        WS_COND_FLG.FOUND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.FOUND_FLG = 'Y';
        } else {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.TD_START_BR_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_COND_FLG.FOUND_FLG);
    }
    public void T000_STARTBR_TDTIREV_BY_RUL_CD() throws IOException,SQLException,Exception {
        TDTIREV_BR.rp = new DBParm();
        TDTIREV_BR.rp.TableName = "TDTIREV";
        TDTIREV_BR.rp.where = "INT_RUL_CD = :TDRIREV.INT_RUL_CD";
        IBS.STARTBR(SCCGWA, TDRIREV, this, TDTIREV_BR);
    }
    public void T000_READNEXT_TDTIREV() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRIREV, this, TDTIREV_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.IREV_FLG = 'Y';
        } else {
            WS_COND_FLG.IREV_FLG = 'N';
        }
    }
    public void T000_ENDBR_TDTIREV() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTIREV_BR);
    }
    public void B052_ADD_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AWA_5020.RUL_CD);
        B120_TRANS_DATA_INPUT1();
        if (pgmRtn) return;
        B053_ADD_TDTRATE();
        if (pgmRtn) return;
        B054_ADD_TDTRACD();
        if (pgmRtn) return;
    }
    public void B053_ADD_TDTRATE() throws IOException,SQLException,Exception {
        TDTRATE_RD = new DBParm();
        TDTRATE_RD.TableName = "TDTRATE";
        IBS.WRITE(SCCGWA, TDRRATE, TDTRATE_RD);
        WS_COND_FLG.FOUND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.EFF_TYP = '0';
        } else {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.TD_DOCU_WRITE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B054_ADD_TDTRACD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AWA_5020.RUL_CD);
        if (AWA_5020.FUNC == 'M') {
            T900_REUPDATE_TDTRACD();
            if (pgmRtn) return;
        }
        if (AWA_5020.OC_RACD[1-1].NUM == 0) {
            CEP.ERR(SCCGWA, ERROR_MSG.TD_DOCU_WRITE_ERR);
        }
        CEP.TRC(SCCGWA, AWA_5020.OC_RACD[1-1].BAL);
        CEP.TRC(SCCGWA, AWA_5020.OC_RACD[1-1].TERM);
        CEP.TRC(SCCGWA, AWA_5020.OC_RACD[1-1].GRPS_NO);
        CEP.TRC(SCCGWA, AWA_5020.OC_RACD[1-1].BR);
        CEP.TRC(SCCGWA, AWA_5020.OC_RACD[1-1].AGE);
        CEP.TRC(SCCGWA, AWA_5020.OC_RACD[1-1].GENDER);
        CEP.TRC(SCCGWA, AWA_5020.OC_RACD[1-1].CHNL_NO);
        CEP.TRC(SCCGWA, AWA_5020.OC_RACD[1-1].ASS_LVL);
        CEP.TRC(SCCGWA, AWA_5020.OC_RACD[1-1].SVR_LVL);
        WS_TIME = 0;
        WS_TIME += 1;
        CEP.TRC(SCCGWA, WS_TIME);
        while (AWA_5020.OC_RACD[WS_TIME-1].NUM != 0 
            && WS_TIME <= 30 
            && AWA_5020.OC_RACD[WS_TIME-1].NUM != 0) {
            WS_TIME2 = WS_TIME;
            CEP.TRC(SCCGWA, WS_TIME2);
            if (WS_TIME2 != 1) {
                CEP.TRC(SCCGWA, "WS-TIME2 NOT EQUAL 1");
                while (WS_TIME2 != 1 
                    && WS_TIME4 != 2) {
                    WS_TIME2 = (short) (WS_TIME2 - 1);
                    WS_COND_FLG.FOUND_FLG = ' ';
                    WS_TIME4 = 0;
                    WS_TIME6 = 0;
                    if (AWA_5020.OC_RACD[WS_TIME2-1].BAL != AWA_5020.OC_RACD[WS_TIME-1].BAL) {
                        WS_TIME6 += 1;
                    }
                    if (!AWA_5020.OC_RACD[WS_TIME2-1].TERM.equalsIgnoreCase(AWA_5020.OC_RACD[WS_TIME-1].TERM)) {
                        WS_TIME6 += 1;
                    }
                    if (!AWA_5020.OC_RACD[WS_TIME2-1].GRPS_NO.equalsIgnoreCase(AWA_5020.OC_RACD[WS_TIME-1].GRPS_NO)) {
                        WS_TIME6 += 1;
                    }
                    if (AWA_5020.OC_RACD[WS_TIME2-1].BR != AWA_5020.OC_RACD[WS_TIME-1].BR) {
                        WS_TIME6 += 1;
                    }
                    if (AWA_5020.OC_RACD[WS_TIME2-1].AGE != AWA_5020.OC_RACD[WS_TIME-1].AGE) {
                        WS_TIME6 += 1;
                    }
                    if (AWA_5020.OC_RACD[WS_TIME2-1].GENDER != AWA_5020.OC_RACD[WS_TIME-1].GENDER) {
                        WS_TIME6 += 1;
                    }
                    if (!AWA_5020.OC_RACD[WS_TIME2-1].CHNL_NO.equalsIgnoreCase(AWA_5020.OC_RACD[WS_TIME-1].CHNL_NO)) {
                        WS_TIME6 += 1;
                    }
                    if (!AWA_5020.OC_RACD[WS_TIME2-1].ASS_LVL.equalsIgnoreCase(AWA_5020.OC_RACD[WS_TIME-1].ASS_LVL)) {
                        WS_TIME6 += 1;
                    }
                    if (!AWA_5020.OC_RACD[WS_TIME2-1].SVR_LVL.equalsIgnoreCase(AWA_5020.OC_RACD[WS_TIME-1].SVR_LVL)) {
                        WS_TIME6 += 1;
                    }
                    CEP.TRC(SCCGWA, AWA_5020.OC_RACD[WS_TIME2-1].BAL);
                    CEP.TRC(SCCGWA, AWA_5020.OC_RACD[WS_TIME2-1].TERM);
                    CEP.TRC(SCCGWA, AWA_5020.OC_RACD[WS_TIME2-1].GRPS_NO);
                    CEP.TRC(SCCGWA, AWA_5020.OC_RACD[WS_TIME2-1].BR);
                    CEP.TRC(SCCGWA, AWA_5020.OC_RACD[WS_TIME2-1].AGE);
                    CEP.TRC(SCCGWA, AWA_5020.OC_RACD[WS_TIME2-1].GENDER);
                    CEP.TRC(SCCGWA, AWA_5020.OC_RACD[WS_TIME2-1].CHNL_NO);
                    CEP.TRC(SCCGWA, AWA_5020.OC_RACD[WS_TIME2-1].ASS_LVL);
                    CEP.TRC(SCCGWA, AWA_5020.OC_RACD[WS_TIME2-1].SVR_LVL);
                    WS_TIME5 = 0;
                    CEP.TRC(SCCGWA, WS_TIME6);
                    if (WS_TIME6 == 0) {
                        CEP.TRC(SCCGWA, WS_TIME4);
                        CEP.ERR(SCCGWA, ERROR_MSG.TD_MSG_HAVE);
                    }
                }
                WS_COND_FLG.FOUND_FLG = ' ';
                WS_TIME2 = 0;
                WS_TIME3 = 0;
                WS_TIME4 = 0;
                WS_TIME5 = 0;
                WS_TIME6 = 0;
            }
            CEP.TRC(SCCGWA, WS_TIME);
            B120_TRANS_DATA_INPUT2();
            if (pgmRtn) return;
            B110_WRITE_TDTRACD();
            if (pgmRtn) return;
        }
        WS_TIME = 0;
    }
    public void B090_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRIREV);
        TDRIREV.INT_RUL_CD = AWA_5020.RUL_CD;
        T000_STARTBR_TDTIREV_BY_RUL_CD();
        if (pgmRtn) return;
        T000_READNEXT_TDTIREV();
        if (pgmRtn) return;
        if (WS_COND_FLG.IREV_FLG == 'Y') {
            CEP.ERR(SCCGWA, ERROR_MSG.TD_RUL_CD_EFF);
        }
        T000_ENDBR_TDTIREV();
        if (pgmRtn) return;
        B091_DELETE_TDTRATE();
        if (pgmRtn) return;
        WS_TIME = 0;
        while (WS_TIME <= 29) {
            WS_TIME += 1;
            T900_REUPDATE_TDTRACD();
            if (pgmRtn) return;
            if (WS_COND_FLG.FOUND_FLG == 'Y') {
                B092_DELETE_TDTRACD();
                if (pgmRtn) return;
            }
        }
        WS_TIME = 0;
        WS_COND_FLG.FOUND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.FOUND_FLG = 'Y';
        }
    }
    public void B091_DELETE_TDTRATE() throws IOException,SQLException,Exception {
        TDRRATE.KEY.RUL_CD = AWA_5020.RUL_CD;
        TDTRATE_RD = new DBParm();
        TDTRATE_RD.TableName = "TDTRATE";
        IBS.DELETE(SCCGWA, TDRRATE, TDTRATE_RD);
        WS_COND_FLG.FOUND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.FOUND_FLG = 'Y';
        }
    }
    public void B092_DELETE_TDTRACD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRRACD);
        TDRRACD.KEY.RUL_CD = AWA_5020.RUL_CD;
        TDRRACD.KEY.NUM = WS_TIME;
        CEP.TRC(SCCGWA, TDRRACD.KEY.RUL_CD);
        CEP.TRC(SCCGWA, TDRRACD.KEY.NUM);
        TDTRACD_RD = new DBParm();
        TDTRACD_RD.TableName = "TDTRACD";
        IBS.DELETE(SCCGWA, TDRRACD, TDTRACD_RD);
        WS_COND_FLG.FOUND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.FOUND_FLG = 'Y';
        } else {
            CEP.ERR(SCCGWA, ERROR_MSG.TD_DELETE_TB_ERR);
        }
    }
    public void B110_WRITE_TDTRACD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRRACD.KEY.RUL_CD);
        CEP.TRC(SCCGWA, TDRRACD.KEY.NUM);
        TDTRACD_RD = new DBParm();
        TDTRACD_RD.TableName = "TDTRACD";
        IBS.WRITE(SCCGWA, TDRRACD, TDTRACD_RD);
        WS_COND_FLG.FOUND_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.EFF_TYP = '0';
        } else {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.TD_DOCU_WRITE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T900_REUPDATE_TDTRACD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRRACD);
        TDRRACD.KEY.RUL_CD = AWA_5020.RUL_CD;
        TDRRACD.KEY.NUM = WS_TIME;
        TDTRACD_RD = new DBParm();
        TDTRACD_RD.TableName = "TDTRACD";
        TDTRACD_RD.upd = true;
        IBS.READ(SCCGWA, TDRRACD, TDTRACD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.FOUND_FLG = 'Y';
        } else {
            WS_COND_FLG.FOUND_FLG = 'N';
        }
    }
    public void B120_TRANS_DATA_INPUT1() throws IOException,SQLException,Exception {
        TDRRATE.KEY.RUL_CD = AWA_5020.RUL_CD;
        TDRRATE.CCY = AWA_5020.CCY;
        TDRRATE.CDESC = AWA_5020.CDESC;
        TDRRATE.DESC = AWA_5020.DESC;
        TDRRATE.SDT = AWA_5020.SDT;
        TDRRATE.DDT = AWA_5020.DDT;
        TDRRACD.KEY.RUL_CD = AWA_5020.RUL_CD;
    }
    public void B120_TRANS_DATA_INPUT2() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_TIME);
        TDRRACD.KEY.NUM = WS_TIME;
        TDRRACD.LC_TYP = AWA_5020.OC_RACD[WS_TIME-1].LC_TYP;
        TDRRACD.FLT_RAT = AWA_5020.OC_RACD[WS_TIME-1].FLT_RAT;
        TDRRACD.PCT_S = AWA_5020.OC_RACD[WS_TIME-1].PCT_S;
        TDRRACD.BAL = AWA_5020.OC_RACD[WS_TIME-1].BAL;
        TDRRACD.TERM = AWA_5020.OC_RACD[WS_TIME-1].TERM;
        TDRRACD.SVR_LVL = AWA_5020.OC_RACD[WS_TIME-1].SVR_LVL;
        TDRRACD.GRPS_NO = AWA_5020.OC_RACD[WS_TIME-1].GRPS_NO;
        TDRRACD.BR = AWA_5020.OC_RACD[WS_TIME-1].BR;
        TDRRACD.ASS_LVL = AWA_5020.OC_RACD[WS_TIME-1].ASS_LVL;
        TDRRACD.CHNL_NO = AWA_5020.OC_RACD[WS_TIME-1].CHNL_NO;
        TDRRACD.AGE = AWA_5020.OC_RACD[WS_TIME-1].AGE;
        TDRRACD.GENDER = AWA_5020.OC_RACD[WS_TIME-1].GENDER;
        WS_TIME += 1;
    }
    public void B130_TRANS_DATA_OUTPUT1() throws IOException,SQLException,Exception {
        WS_LIST.RUL_CD = TDRRATE.KEY.RUL_CD;
        WS_LIST.CCY = TDRRATE.CCY;
        WS_LIST.CDESC = TDRRATE.CDESC;
        WS_LIST.DESC = TDRRATE.DESC;
        WS_LIST.SDT = TDRRATE.SDT;
        WS_LIST.DDT = TDRRATE.DDT;
    }
    public void B130_TRANS_DATA_OUTPUT2() throws IOException,SQLException,Exception {
        WS_TIME += 1;
        WS_LIST.WS_OC_RACD[WS_TIME-1].NUM = TDRRACD.KEY.NUM;
        WS_LIST.WS_OC_RACD[WS_TIME-1].LC_TYP = TDRRACD.LC_TYP;
        WS_LIST.WS_OC_RACD[WS_TIME-1].FLT_RAT = TDRRACD.FLT_RAT;
        WS_LIST.WS_OC_RACD[WS_TIME-1].PCT_S = TDRRACD.PCT_S;
        WS_LIST.WS_OC_RACD[WS_TIME-1].BAL = TDRRACD.BAL;
        WS_LIST.WS_OC_RACD[WS_TIME-1].TERM = TDRRACD.TERM;
        WS_LIST.WS_OC_RACD[WS_TIME-1].SVR_LVL = TDRRACD.SVR_LVL;
        WS_LIST.WS_OC_RACD[WS_TIME-1].GRPS_NO = TDRRACD.GRPS_NO;
        WS_LIST.WS_OC_RACD[WS_TIME-1].BR = TDRRACD.BR;
        WS_LIST.WS_OC_RACD[WS_TIME-1].ASS_LVL = TDRRACD.ASS_LVL;
        WS_LIST.WS_OC_RACD[WS_TIME-1].CHNL_NO = TDRRACD.CHNL_NO;
        WS_LIST.WS_OC_RACD[WS_TIME-1].AGE = TDRRACD.AGE;
        WS_LIST.WS_OC_RACD[WS_TIME-1].GENDER = TDRRACD.GENDER;
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
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
