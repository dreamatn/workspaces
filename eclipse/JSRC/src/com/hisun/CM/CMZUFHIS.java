package com.hisun.CM;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class CMZUFHIS {
    int JIBS_tmp_int;
    brParm BPTFHIS1_BR = new brParm();
    brParm BPTFHIS2_BR = new brParm();
    brParm BPTFHIST_BR = new brParm();
    DBParm BPTTHIS_RD;
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    CMZUFHIS_WS_FEE_INFO WS_FEE_INFO = new CMZUFHIS_WS_FEE_INFO();
    char WS_EOF_FHIS_FLG = ' ';
    char WS_EOF_FHIST_FLG = ' ';
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRFHIS BPRFHIS = new BPRFHIS();
    BPRFHIST BPRFHIST = new BPRFHIST();
    BPRTHIS BPRTHIS = new BPRTHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    CMCUFHIS CMCUFHIS;
    public void MP(SCCGWA SCCGWA, CMCUFHIS CMCUFHIS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CMCUFHIS = CMCUFHIS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CMZUFHIS return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHK_INP_PROC();
        CEP.TRC(SCCGWA, "STARTBR BPTFHIS1/2");
        B030_NEW_PROC();
    }
    public void B010_CHK_INP_PROC() throws IOException,SQLException,Exception {
        if (CMCUFHIS.AC_DT == 0 
            || !IBS.isNumeric(CMCUFHIS.AC_DT+"") 
            || CMCUFHIS.JRNNO == 0 
            || !IBS.isNumeric(CMCUFHIS.JRNNO+"")) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_INP_DT_JRN_ERR;
            S000_ERR_MSG_PROC();
            CEP.TRC(SCCGWA, CMCUFHIS.AC_DT);
            CEP.TRC(SCCGWA, CMCUFHIS.JRNNO);
        }
    }
    public void B020_OLD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTFHIS1();
        T000_READNEXT_BPTFHIS1();
        while (WS_EOF_FHIS_FLG != 'Y') {
            CEP.TRC(SCCGWA, BPRFHIS.TX_MMO);
            WS_I += 1;
            if (BPRFHIS.TX_TOOL.trim().length() == 0) {
                CMCUFHIS.INFO[WS_I-1].TX_TOOL = BPRFHIS.KEY.AC;
                CMCUFHIS.INFO[WS_I-1].AC = BPRFHIS.KEY.AC;
            } else {
                CMCUFHIS.INFO[WS_I-1].TX_TOOL = BPRFHIS.TX_TOOL;
                CMCUFHIS.INFO[WS_I-1].AC = BPRFHIS.KEY.AC;
            }
            CMCUFHIS.INFO[WS_I-1].JRN_SEQ = BPRFHIS.KEY.JRN_SEQ;
            CMCUFHIS.INFO[WS_I-1].DRCRFLG = BPRFHIS.DRCRFLG;
            CMCUFHIS.INFO[WS_I-1].TX_CCY = BPRFHIS.TX_CCY;
            CMCUFHIS.INFO[WS_I-1].TX_AMT = BPRFHIS.TX_AMT;
            CEP.TRC(SCCGWA, BPRFHIS.TX_AMT);
            if (BPRFHIS.TX_AMT >= 0) {
                CMCUFHIS.INFO[WS_I-1].TX_AMT_FLG = '+';
            } else {
                CMCUFHIS.INFO[WS_I-1].TX_AMT_FLG = '-';
            }
            T000_READNEXT_BPTFHIS1();
        }
        T000_ENDBR_BPTFHIS1();
        T000_STARTBR_BPTFHIS2();
        T000_READNEXT_BPTFHIS2();
        while (WS_EOF_FHIS_FLG != 'Y') {
            CEP.TRC(SCCGWA, BPRFHIS.TX_MMO);
            WS_I += 1;
            if (BPRFHIS.TX_TOOL.trim().length() == 0) {
                CMCUFHIS.INFO[WS_I-1].TX_TOOL = BPRFHIS.KEY.AC;
                CMCUFHIS.INFO[WS_I-1].AC = BPRFHIS.KEY.AC;
            } else {
                CMCUFHIS.INFO[WS_I-1].TX_TOOL = BPRFHIS.TX_TOOL;
                CMCUFHIS.INFO[WS_I-1].AC = BPRFHIS.KEY.AC;
            }
            CMCUFHIS.INFO[WS_I-1].JRN_SEQ = BPRFHIS.KEY.JRN_SEQ;
            CMCUFHIS.INFO[WS_I-1].DRCRFLG = BPRFHIS.DRCRFLG;
            CMCUFHIS.INFO[WS_I-1].TX_CCY = BPRFHIS.TX_CCY;
            CMCUFHIS.INFO[WS_I-1].TX_AMT = BPRFHIS.TX_AMT;
            CEP.TRC(SCCGWA, BPRFHIS.TX_AMT);
            if (BPRFHIS.TX_AMT >= 0) {
                CMCUFHIS.INFO[WS_I-1].TX_AMT_FLG = '+';
            } else {
                CMCUFHIS.INFO[WS_I-1].TX_AMT_FLG = '-';
            }
            T000_READNEXT_BPTFHIS2();
        }
        T000_ENDBR_BPTFHIS2();
        CEP.TRC(SCCGWA, WS_I);
        CEP.TRC(SCCGWA, WS_FEE_INFO.WS_TX_AMT);
        if (WS_I == 0 
            && WS_FEE_INFO.WS_TX_AMT != 0) {
            CEP.TRC(SCCGWA, "ONLY FEE REC");
            if (WS_FEE_INFO.WS_TX_TOOL.trim().length() == 0) {
                CMCUFHIS.INFO[1-1].TX_TOOL = WS_FEE_INFO.WS_AC;
                CMCUFHIS.INFO[1-1].AC = " ";
            } else {
                CMCUFHIS.INFO[1-1].TX_TOOL = WS_FEE_INFO.WS_TX_TOOL;
                CMCUFHIS.INFO[1-1].AC = WS_FEE_INFO.WS_AC;
            }
            CMCUFHIS.INFO[1-1].DRCRFLG = WS_FEE_INFO.WS_DRCRFLG;
            CMCUFHIS.INFO[1-1].TX_CCY = WS_FEE_INFO.WS_TX_CCY;
            CMCUFHIS.INFO[1-1].TX_AMT = WS_FEE_INFO.WS_TX_AMT;
            if (WS_FEE_INFO.WS_TX_AMT >= 0) {
                CMCUFHIS.INFO[1-1].TX_AMT_FLG = '+';
            } else {
                CMCUFHIS.INFO[1-1].TX_AMT_FLG = '-';
            }
        }
        if (WS_I == 0 
            && WS_FEE_INFO.WS_TX_AMT == 0) {
            CEP.TRC(SCCGWA, "STARTBR BPTFHIST");
            T000_STARTBR_BPTFHIST();
            T000_READNEXT_BPTFHIST();
            while (WS_EOF_FHIST_FLG != 'Y') {
                if (BPRFHIST.TX_MMO == null) BPRFHIST.TX_MMO = "";
                JIBS_tmp_int = BPRFHIST.TX_MMO.length();
                for (int i=0;i<5-JIBS_tmp_int;i++) BPRFHIST.TX_MMO += " ";
                if (BPRFHIST.TX_MMO == null) BPRFHIST.TX_MMO = "";
                JIBS_tmp_int = BPRFHIST.TX_MMO.length();
                for (int i=0;i<5-JIBS_tmp_int;i++) BPRFHIST.TX_MMO += " ";
                if (BPRFHIST.TX_MMO.substring(0, 1).equalsIgnoreCase("F") 
                    || BPRFHIST.TX_MMO.substring(0, 1).equalsIgnoreCase("N")) {
                    IBS.init(SCCGWA, WS_FEE_INFO);
                    if (BPRFHIST.TX_TOOL.trim().length() == 0) {
                        WS_FEE_INFO.WS_TX_TOOL = BPRFHIST.KEY.AC;
                        WS_FEE_INFO.WS_AC = " ";
                    } else {
                        WS_FEE_INFO.WS_TX_TOOL = BPRFHIST.TX_TOOL;
                        WS_FEE_INFO.WS_AC = BPRFHIST.KEY.AC;
                    }
                    WS_FEE_INFO.WS_DRCRFLG = BPRFHIST.DRCRFLG;
                    WS_FEE_INFO.WS_TX_CCY = BPRFHIST.TX_CCY;
                    WS_FEE_INFO.WS_TX_AMT = BPRFHIST.TX_AMT;
                    CEP.TRC(SCCGWA, BPRFHIST.TX_AMT);
                    if (BPRFHIST.TX_AMT >= 0) {
                        WS_FEE_INFO.WS_TX_AMT_FLG = '+';
                    } else {
                        WS_FEE_INFO.WS_TX_AMT_FLG = '-';
                    }
                } else {
                    WS_I += 1;
                    CEP.TRC(SCCGWA, WS_I);
                    if (WS_I <= 2) {
                        if (BPRFHIST.TX_TOOL.trim().length() == 0) {
                            CMCUFHIS.INFO[WS_I-1].TX_TOOL = BPRFHIST.KEY.AC;
                            CMCUFHIS.INFO[WS_I-1].AC = " ";
                        } else {
                            CMCUFHIS.INFO[WS_I-1].TX_TOOL = BPRFHIST.TX_TOOL;
                            CMCUFHIS.INFO[WS_I-1].AC = BPRFHIST.KEY.AC;
                        }
                        CMCUFHIS.INFO[WS_I-1].DRCRFLG = BPRFHIST.DRCRFLG;
                        CMCUFHIS.INFO[WS_I-1].TX_CCY = BPRFHIST.TX_CCY;
                        CMCUFHIS.INFO[WS_I-1].TX_AMT = BPRFHIST.TX_AMT;
                        CEP.TRC(SCCGWA, BPRFHIST.TX_AMT);
                        if (BPRFHIST.TX_AMT >= 0) {
                            CMCUFHIS.INFO[WS_I-1].TX_AMT_FLG = '+';
                        } else {
                            CMCUFHIS.INFO[WS_I-1].TX_AMT_FLG = '-';
                        }
                    }
                }
                T000_READNEXT_BPTFHIST();
            }
            T000_ENDBR_BPTFHIST();
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, WS_FEE_INFO.WS_TX_AMT);
            if (WS_I == 0 
                && WS_FEE_INFO.WS_TX_AMT != 0) {
                CEP.TRC(SCCGWA, "ONLY FEE REC");
                if (WS_FEE_INFO.WS_TX_TOOL.trim().length() == 0) {
                    CMCUFHIS.INFO[1-1].TX_TOOL = WS_FEE_INFO.WS_AC;
                    CMCUFHIS.INFO[1-1].AC = " ";
                } else {
                    CMCUFHIS.INFO[1-1].TX_TOOL = WS_FEE_INFO.WS_TX_TOOL;
                    CMCUFHIS.INFO[1-1].AC = WS_FEE_INFO.WS_AC;
                }
                CMCUFHIS.INFO[1-1].DRCRFLG = WS_FEE_INFO.WS_DRCRFLG;
                CMCUFHIS.INFO[1-1].TX_CCY = WS_FEE_INFO.WS_TX_CCY;
                CMCUFHIS.INFO[1-1].TX_AMT = WS_FEE_INFO.WS_TX_AMT;
                if (WS_FEE_INFO.WS_TX_AMT >= 0) {
                    CMCUFHIS.INFO[1-1].TX_AMT_FLG = '+';
                } else {
                    CMCUFHIS.INFO[1-1].TX_AMT_FLG = '-';
                }
            }
            if (WS_I == 0 
                && WS_FEE_INFO.WS_TX_AMT == 0) {
                CEP.TRC(SCCGWA, "STARTBR BPTTHIS FIRST");
                T000_STARTBR_BPTTHIS();
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
                if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                    CEP.TRC(SCCGWA, "FIND BPTTHIS REC");
                    CEP.TRC(SCCGWA, BPRTHIS.DC_FLG);
                    CMCUFHIS.INFO[1-1].TX_TOOL = BPRTHIS.AC;
                    if (BPRTHIS.DC_FLG == 'D') {
                        CMCUFHIS.INFO[1-1].DRCRFLG = 'C';
                    } else {
                        CMCUFHIS.INFO[1-1].DRCRFLG = 'D';
                    }
                    CMCUFHIS.INFO[1-1].TX_CCY = BPRTHIS.CCY;
                    CMCUFHIS.INFO[1-1].TX_AMT = BPRTHIS.AMT;
                    CEP.TRC(SCCGWA, BPRTHIS.AMT);
                    if (BPRTHIS.AMT >= 0) {
                        CMCUFHIS.INFO[1-1].TX_AMT_FLG = '+';
                    } else {
                        CMCUFHIS.INFO[1-1].TX_AMT_FLG = '-';
                    }
                }
            }
        }
    }
    public void B030_NEW_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTFHIS1();
        T000_READNEXT_BPTFHIS1();
        while (WS_EOF_FHIS_FLG != 'Y') {
            CEP.TRC(SCCGWA, BPRFHIS.TX_MMO);
            WS_I += 1;
            if (BPRFHIS.TX_TOOL.trim().length() == 0) {
                CMCUFHIS.INFO[WS_I-1].TX_TOOL = BPRFHIS.KEY.AC;
                CMCUFHIS.INFO[WS_I-1].AC = BPRFHIS.KEY.AC;
            } else {
                CMCUFHIS.INFO[WS_I-1].TX_TOOL = BPRFHIS.TX_TOOL;
                CMCUFHIS.INFO[WS_I-1].AC = BPRFHIS.KEY.AC;
            }
            CMCUFHIS.INFO[WS_I-1].JRN_SEQ = BPRFHIS.KEY.JRN_SEQ;
            CMCUFHIS.INFO[WS_I-1].DRCRFLG = BPRFHIS.DRCRFLG;
            CMCUFHIS.INFO[WS_I-1].TX_CCY = BPRFHIS.TX_CCY;
            CMCUFHIS.INFO[WS_I-1].TX_CCY_TYP = "" + BPRFHIS.TX_CCY_TYPE;
            JIBS_tmp_int = CMCUFHIS.INFO[WS_I-1].TX_CCY_TYP.length();
            for (int i=0;i<1-JIBS_tmp_int;i++) CMCUFHIS.INFO[WS_I-1].TX_CCY_TYP = "0" + CMCUFHIS.INFO[WS_I-1].TX_CCY_TYP;
            CMCUFHIS.INFO[WS_I-1].TX_AMT = BPRFHIS.TX_AMT;
            CMCUFHIS.INFO[WS_I-1].OPP_AC = BPRFHIS.RLT_AC;
            CEP.TRC(SCCGWA, BPRFHIS.TX_AMT);
            if (BPRFHIS.TX_AMT >= 0) {
                CMCUFHIS.INFO[WS_I-1].TX_AMT_FLG = '+';
            } else {
                CMCUFHIS.INFO[WS_I-1].TX_AMT_FLG = '-';
            }
            T000_READNEXT_BPTFHIS1();
        }
        T000_ENDBR_BPTFHIS1();
        T000_STARTBR_BPTFHIS2();
        T000_READNEXT_BPTFHIS2();
        while (WS_EOF_FHIS_FLG != 'Y') {
            CEP.TRC(SCCGWA, BPRFHIS.TX_MMO);
            WS_I += 1;
            if (BPRFHIS.TX_TOOL.trim().length() == 0) {
                CMCUFHIS.INFO[WS_I-1].TX_TOOL = BPRFHIS.KEY.AC;
                CMCUFHIS.INFO[WS_I-1].AC = BPRFHIS.KEY.AC;
            } else {
                CMCUFHIS.INFO[WS_I-1].TX_TOOL = BPRFHIS.TX_TOOL;
                CMCUFHIS.INFO[WS_I-1].AC = BPRFHIS.KEY.AC;
            }
            CMCUFHIS.INFO[WS_I-1].JRN_SEQ = BPRFHIS.KEY.JRN_SEQ;
            CMCUFHIS.INFO[WS_I-1].DRCRFLG = BPRFHIS.DRCRFLG;
            CMCUFHIS.INFO[WS_I-1].TX_CCY = BPRFHIS.TX_CCY;
            CMCUFHIS.INFO[WS_I-1].TX_CCY_TYP = "" + BPRFHIS.TX_CCY_TYPE;
            JIBS_tmp_int = CMCUFHIS.INFO[WS_I-1].TX_CCY_TYP.length();
            for (int i=0;i<1-JIBS_tmp_int;i++) CMCUFHIS.INFO[WS_I-1].TX_CCY_TYP = "0" + CMCUFHIS.INFO[WS_I-1].TX_CCY_TYP;
            CMCUFHIS.INFO[WS_I-1].TX_AMT = BPRFHIS.TX_AMT;
            CMCUFHIS.INFO[WS_I-1].OPP_AC = BPRFHIS.RLT_AC;
            CEP.TRC(SCCGWA, BPRFHIS.TX_AMT);
            if (BPRFHIS.TX_AMT >= 0) {
                CMCUFHIS.INFO[WS_I-1].TX_AMT_FLG = '+';
            } else {
                CMCUFHIS.INFO[WS_I-1].TX_AMT_FLG = '-';
            }
            T000_READNEXT_BPTFHIS2();
        }
        T000_ENDBR_BPTFHIS2();
        if (WS_I == 0 
            && WS_FEE_INFO.WS_TX_AMT == 0) {
            CEP.TRC(SCCGWA, "STARTBR BPTFHIST");
            T000_STARTBR_BPTFHIST();
            T000_READNEXT_BPTFHIST();
            while (WS_EOF_FHIST_FLG != 'Y') {
                WS_I += 1;
                CEP.TRC(SCCGWA, WS_I);
                if (BPRFHIST.TX_TOOL.trim().length() == 0) {
                    CMCUFHIS.INFO[WS_I-1].TX_TOOL = BPRFHIST.KEY.AC;
                    CMCUFHIS.INFO[WS_I-1].AC = " ";
                } else {
                    CMCUFHIS.INFO[WS_I-1].TX_TOOL = BPRFHIST.TX_TOOL;
                    CMCUFHIS.INFO[WS_I-1].AC = BPRFHIST.KEY.AC;
                }
                CMCUFHIS.INFO[WS_I-1].DRCRFLG = BPRFHIST.DRCRFLG;
                CMCUFHIS.INFO[WS_I-1].JRN_SEQ = BPRFHIST.KEY.JRN_SEQ;
                CMCUFHIS.INFO[WS_I-1].TX_CCY = BPRFHIST.TX_CCY;
                CMCUFHIS.INFO[WS_I-1].TX_CCY_TYP = "" + BPRFHIST.TX_CCY_TYPE;
                JIBS_tmp_int = CMCUFHIS.INFO[WS_I-1].TX_CCY_TYP.length();
                for (int i=0;i<1-JIBS_tmp_int;i++) CMCUFHIS.INFO[WS_I-1].TX_CCY_TYP = "0" + CMCUFHIS.INFO[WS_I-1].TX_CCY_TYP;
                CMCUFHIS.INFO[WS_I-1].TX_AMT = BPRFHIST.TX_AMT;
                CMCUFHIS.INFO[WS_I-1].OPP_AC = BPRFHIST.RLT_AC;
                CEP.TRC(SCCGWA, BPRFHIST.TX_AMT);
                if (BPRFHIST.TX_AMT >= 0) {
                    CMCUFHIS.INFO[WS_I-1].TX_AMT_FLG = '+';
                } else {
                    CMCUFHIS.INFO[WS_I-1].TX_AMT_FLG = '-';
                }
                T000_READNEXT_BPTFHIST();
            }
            T000_ENDBR_BPTFHIST();
        }
    }
    public void T000_STARTBR_BPTFHIS1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFHIS);
        BPRFHIS.KEY.AC_DT = CMCUFHIS.AC_DT;
        BPRFHIS.KEY.JRNNO = CMCUFHIS.JRNNO;
        CEP.TRC(SCCGWA, "STARTBRBPTFHIS1");
        CEP.TRC(SCCGWA, BPRFHIS.KEY.AC_DT);
        CEP.TRC(SCCGWA, BPRFHIS.KEY.JRNNO);
        BPTFHIS1_BR.rp = new DBParm();
        BPTFHIS1_BR.rp.TableName = "BPTFHIS1";
        BPTFHIS1_BR.rp.where = "AC_DT = :BPRFHIS.KEY.AC_DT "
            + "AND JRNNO = :BPRFHIS.KEY.JRNNO";
        BPTFHIS1_BR.rp.order = "JRN_SEQ";
        IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS1_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_STARTBR_BPTFHIS2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFHIS);
        BPRFHIS.KEY.AC_DT = CMCUFHIS.AC_DT;
        BPRFHIS.KEY.JRNNO = CMCUFHIS.JRNNO;
        CEP.TRC(SCCGWA, "STARTBRBPTFHIS1");
        CEP.TRC(SCCGWA, BPRFHIS.KEY.AC_DT);
        CEP.TRC(SCCGWA, BPRFHIS.KEY.JRNNO);
        BPTFHIS2_BR.rp = new DBParm();
        BPTFHIS2_BR.rp.TableName = "BPTFHIS2";
        BPTFHIS2_BR.rp.where = "AC_DT = :BPRFHIS.KEY.AC_DT "
            + "AND JRNNO = :BPRFHIS.KEY.JRNNO";
        BPTFHIS2_BR.rp.order = "JRN_SEQ";
        IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS2_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READNEXT_BPTFHIS1() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRFHIS, this, BPTFHIS1_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_EOF_FHIS_FLG = 'N';
        } else {
            WS_EOF_FHIS_FLG = 'Y';
        }
    }
    public void T000_READNEXT_BPTFHIS2() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRFHIS, this, BPTFHIS2_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_EOF_FHIS_FLG = 'N';
        } else {
            WS_EOF_FHIS_FLG = 'Y';
        }
    }
    public void T000_ENDBR_BPTFHIS1() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTFHIS1_BR);
    }
    public void T000_ENDBR_BPTFHIS2() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTFHIS2_BR);
    }
    public void T000_STARTBR_BPTFHIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFHIST);
        BPRFHIST.KEY.AC_DT = CMCUFHIS.AC_DT;
        BPRFHIST.KEY.JRNNO = CMCUFHIS.JRNNO;
        BPTFHIST_BR.rp = new DBParm();
        BPTFHIST_BR.rp.TableName = "BPTFHIST";
        BPTFHIST_BR.rp.where = "AC_DT = :BPRFHIST.KEY.AC_DT "
            + "AND JRNNO = :BPRFHIST.KEY.JRNNO";
        BPTFHIST_BR.rp.order = "JRN_SEQ";
        IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
    }
    public void T000_READNEXT_BPTFHIST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_EOF_FHIST_FLG = 'N';
        } else {
            WS_EOF_FHIST_FLG = 'Y';
        }
    }
    public void T000_ENDBR_BPTFHIST() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTFHIST_BR);
    }
    public void T000_STARTBR_BPTTHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTHIS);
        BPRTHIS.KEY.DATE = CMCUFHIS.AC_DT;
        BPRTHIS.KEY.VCH_NO = "" + CMCUFHIS.JRNNO;
        JIBS_tmp_int = BPRTHIS.KEY.VCH_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) BPRTHIS.KEY.VCH_NO = "0" + BPRTHIS.KEY.VCH_NO;
        BPTTHIS_RD = new DBParm();
        BPTTHIS_RD.TableName = "BPTTHIS";
        BPTTHIS_RD.where = "DATE = :BPRTHIS.KEY.DATE "
            + "AND VCH_NO = :BPRTHIS.KEY.VCH_NO";
        BPTTHIS_RD.fst = true;
        BPTTHIS_RD.order = "SEQ";
        IBS.READ(SCCGWA, BPRTHIS, this, BPTTHIS_RD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (CMCUFHIS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "CMCUFHIS=");
            CEP.TRC(SCCGWA, CMCUFHIS);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
