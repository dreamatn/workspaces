package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.DC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSCHBR {
    String JIBS_tmp_str[] = new String[10];
    brParm BPTBRIS_BR = new brParm();
    DBParm BPTORGI_RD;
    DBParm BPTBRIS_RD;
    DBParm BPTFXORG_RD;
    DBParm BPTORGR_RD;
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BPX01";
    int WS_I = 0;
    int WS_J = 0;
    int WS_S = 0;
    int WS_SEQ = 0;
    String WS_VIL_TYP_NEW = " ";
    String WS_VIL_TYP_OLD = " ";
    char WS_ATTR_OLD = ' ';
    char WS_ATTR_NEW = ' ';
    int WS_SUPR_BR_OLD = 0;
    int WS_TMP_BR = 0;
    String WS_FX_BUSI = " ";
    int WS_TR_BRANCH = 0;
    char WS_PQORG_ATTR_NEW = ' ';
    String WS_TYPE_OLD = " ";
    int WS_CNT = 0;
    String[] WS_BRIS_LMTCCY = new String[100];
    short[] WS_CAL_MD = new short[12];
    BPZSCHBR_WS_DATE WS_DATE = new BPZSCHBR_WS_DATE();
    char WS_ORGL_FLG = ' ';
    char WS_BRIS_FLG = ' ';
    char WS_FXORG_FLG = ' ';
    char WS_ORGR_FLG = ' ';
    char WS_ORGI_FLG = ' ';
    char WS_DATE_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPRORGI BPRORGI = new BPRORGI();
    BPRORGL BPRORGL = new BPRORGL();
    CICQACRI CICQACRI = new CICQACRI();
    BPRPREAC BPRPREAC = new BPRPREAC();
    SCCFMT SCCFMT = new SCCFMT();
    BPCRORGI BPCRORGI = new BPCRORGI();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCRORGL BPCRORGL = new BPCRORGL();
    DCCUBRIQ DCCUBRIQ = new DCCUBRIQ();
    BPRBRIS BPRBRIS = new BPRBRIS();
    BPRORGR BPRORGR = new BPRORGR();
    BPRFXORG BPRFXORG = new BPRFXORG();
    BPCPORLO BPCPORLO = new BPCPORLO();
    BPCUCHBR BPCUCHBR = new BPCUCHBR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCSCHBR BPCSCHBR;
    public void MP(SCCGWA SCCGWA, BPCSCHBR BPCSCHBR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSCHBR = BPCSCHBR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSCHBR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_LIST();
        if (pgmRtn) return;
        B020_WRITE_BPTORGI();
        if (pgmRtn) return;
        B030_WRITE_BPTORGL();
        if (pgmRtn) return;
        Z_RET();
        if (pgmRtn) return;
    }
    public void B010_CHECK_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUCHBR);
        BPCUCHBR.FUNC = 'F';
        BPCUCHBR.OLD_BR = BPCSCHBR.RC.OLD_BR;
        BPCUCHBR.ORGI_FLG = '1';
        S000_CALL_BPZUCHBR();
        if (pgmRtn) return;
        if (BPCUCHBR.NEW_BR != 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_OLD_BR_HAVE_BEEN_CHG);
        }
        IBS.init(SCCGWA, BPCUCHBR);
        BPCUCHBR.FUNC = 'F';
        BPCUCHBR.OLD_BR = BPCSCHBR.RC.OLD_BR;
        BPCUCHBR.ORGI_FLG = '0';
        S000_CALL_BPZUCHBR();
        if (pgmRtn) return;
        if (BPCUCHBR.NEW_BR != 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_OLD_BR_APPLIED_CHG);
        }
        if (BPCSCHBR.RC.OLD_BR == BPCSCHBR.RC.NEW_BR) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_OLD_NEW_BR_SAME);
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = BPCSCHBR.RC.NEW_BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_NOTFND);
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = BPCSCHBR.RC.OLD_BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_NOTFND);
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPCPQORG.BR = BPCSCHBR.RC.NEW_BR;
        CEP.TRC(SCCGWA, BPCSCHBR.RC.NEW_BR);
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_VIL_TYP_NEW = BPCPQORG.VIL_TYP;
        WS_FX_BUSI = BPCPQORG.FX_BUSI;
        WS_PQORG_ATTR_NEW = BPCPQORG.ATTR;
        if (BPCPQORG.ATTR == '0' 
            || BPCPQORG.ATTR == '1') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NEW_BR_ATTR_ACCOUNT);
        }
        CEP.TRC(SCCGWA, BPCSCHBR.RC.EFF_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (BPCSCHBR.RC.EFF_DT <= SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INCO_DT_ERROR);
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPCPQORG.BR = BPCSCHBR.RC.OLD_BR;
        CEP.TRC(SCCGWA, BPCSCHBR.RC.OLD_BR);
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_VIL_TYP_OLD = BPCPQORG.VIL_TYP;
        WS_ATTR_OLD = BPCPQORG.ATTR;
        WS_SUPR_BR_OLD = BPCPQORG.SUPR_BR;
        WS_TYPE_OLD = BPCPQORG.TYP;
        CEP.TRC(SCCGWA, WS_VIL_TYP_NEW);
        CEP.TRC(SCCGWA, WS_VIL_TYP_OLD);
        if (!WS_VIL_TYP_NEW.equalsIgnoreCase(WS_VIL_TYP_OLD)) {
        }
        if (WS_TYPE_OLD.equalsIgnoreCase("01") 
            || WS_TYPE_OLD.equalsIgnoreCase("02") 
            || WS_TYPE_OLD.equalsIgnoreCase("03") 
            || WS_TYPE_OLD.equalsIgnoreCase("13")) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_OLDBR_TYPE_CHECK);
        }
        if (WS_ATTR_OLD == '2') {
            IBS.init(SCCGWA, BPCPORLO);
            BPCPORLO.BNK = SCCGWA.COMM_AREA.TR_BANK;
            BPCPORLO.BR = BPCSCHBR.RC.OLD_BR;
            S000_CALL_BPZPORLO();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPORLO.SUB_NUM);
            if (BPCPORLO.FLAG == 'Y') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_OLDBR_NOT_HAVE_UNDER);
            }
        }
        WS_TR_BRANCH = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, BPCSCHBR.RC.OLD_BR);
        CEP.TRC(SCCGWA, WS_TR_BRANCH);
        if (WS_ATTR_OLD == '3') {
            if (WS_TR_BRANCH == BPCSCHBR.RC.OLD_BR 
                || WS_TR_BRANCH == WS_SUPR_BR_OLD) {
            } else {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CHEB_ERROR6);
            }
        } else {
            if (WS_TR_BRANCH == BPCSCHBR.RC.OLD_BR) {
            } else {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CHEB_ERROR6);
            }
        }
        IBS.init(SCCGWA, BPRORGI);
        BPRORGI.INCO_DATE = BPCSCHBR.RC.EFF_DT;
        BPRORGI.ORGI_FLG = '0';
        BPRORGI.ORGI_TYP = '3';
        T000_READ_BPTORGI();
        if (pgmRtn) return;
        if (WS_ORGI_FLG == 'Y') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_WHOLEBR_CHG_MUST_ONE);
        }
        IBS.init(SCCGWA, BPRORGR);
        BPRORGR.KEY.BR = BPCSCHBR.RC.OLD_BR;
        BPRORGR.KEY.BNK = "001";
        BPRORGR.EXP_DT = BPCSCHBR.RC.EFF_DT;
        T000_READ_BPTORGR_FIRST();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_EXIST_UPBR);
        }
        IBS.init(SCCGWA, BPRBRIS);
        BPRBRIS.KEY.BR = BPCSCHBR.RC.OLD_BR;
        T000_STARTBR_BPTBRIS();
        if (pgmRtn) return;
        T000_READNEXT_BPTBRIS();
        if (pgmRtn) return;
        while (WS_BRIS_FLG != 'N' 
            && WS_CNT < 100) {
            WS_CNT += 1;
            WS_BRIS_LMTCCY[WS_CNT-1] = BPRBRIS.KEY.LMT_CCY;
            T000_READNEXT_BPTBRIS();
            if (pgmRtn) return;
        }
        T000_ENDBR_BPTBRIS();
        if (pgmRtn) return;
        for (WS_CNT = 1; WS_BRIS_LMTCCY[WS_CNT-1].trim().length() != 0 
            && WS_CNT <= 100; WS_CNT += 1) {
            IBS.init(SCCGWA, BPRBRIS);
            BPRBRIS.KEY.LMT_CCY = WS_BRIS_LMTCCY[WS_CNT-1];
            BPRBRIS.KEY.BR = BPCSCHBR.RC.NEW_BR;
            T000_QUERY_BPTBRIS();
            if (pgmRtn) return;
            if (WS_BRIS_FLG == 'N') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NEWBR_LMT_CCY_NOTFND);
            }
        }
        IBS.init(SCCGWA, BPRFXORG);
        BPRFXORG.KEY.BR = BPCSCHBR.RC.OLD_BR;
        T000_READ_BPTFXORG();
        if (pgmRtn) return;
        if (WS_FXORG_FLG == 'Y') {
            IBS.init(SCCGWA, BPRFXORG);
            BPRFXORG.KEY.BR = BPCSCHBR.RC.NEW_BR;
            T000_READ_BPTFXORG();
            if (pgmRtn) return;
            if (WS_FXORG_FLG == 'N') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_OBRFX_NBRNO);
            }
        }
        IBS.CPY2CLS(SCCGWA, BPCSCHBR.RC.EFF_DT+"", WS_DATE);
        WS_J = WS_DATE.WS_DATE_YYYY % 4;
        WS_I = (int) ((WS_DATE.WS_DATE_YYYY - WS_J) / 4);
        if (WS_J != 0) {
            WS_CAL_MD[2-1] = 28;
        } else {
            WS_J = WS_DATE.WS_DATE_YYYY % 100;
            WS_I = (int) ((WS_DATE.WS_DATE_YYYY - WS_J) / 100);
            if (WS_J != 0) {
                WS_CAL_MD[2-1] = 29;
            } else {
                WS_J = WS_DATE.WS_DATE_YYYY % 400;
                WS_I = (int) ((WS_DATE.WS_DATE_YYYY - WS_J) / 400);
                if (WS_J == 0) {
                    WS_CAL_MD[2-1] = 29;
                } else {
                    WS_CAL_MD[2-1] = 28;
                }
            }
        }
        for (WS_S = 1; WS_S <= 12; WS_S += 1) {
            if (WS_DATE.WS_DATE_MM == 1 
                || WS_DATE.WS_DATE_MM == 3 
                || WS_DATE.WS_DATE_MM == 5 
                || WS_DATE.WS_DATE_MM == 7 
                || WS_DATE.WS_DATE_MM == 8 
                || WS_DATE.WS_DATE_MM == 10 
                || WS_DATE.WS_DATE_MM == 12) {
                if (WS_DATE.WS_DATE_DD == 31) {
                    WS_DATE_FLG = 'Y';
                }
            } else {
                if (WS_DATE.WS_DATE_MM == 4 
                    || WS_DATE.WS_DATE_MM == 6 
                    || WS_DATE.WS_DATE_MM == 9 
                    || WS_DATE.WS_DATE_MM == 11) {
                    if (WS_DATE.WS_DATE_DD == 30) {
                        WS_DATE_FLG = 'Y';
                    }
                } else {
                    if (WS_DATE.WS_DATE_MM == 2) {
                        if (WS_DATE.WS_DATE_DD == WS_CAL_MD[WS_S-1]) {
                            WS_DATE_FLG = 'Y';
                        }
                    }
                }
            }
            if (WS_DATE.WS_DATE_MM == 3 
                || WS_DATE.WS_DATE_MM == 6 
                || WS_DATE.WS_DATE_MM == 9 
                || WS_DATE.WS_DATE_MM == 12) {
                if (WS_DATE.WS_DATE_DD == 20) {
                    WS_DATE_FLG = 'Y';
                }
            }
        }
        if (WS_DATE_FLG == 'Y') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CHBR_DATE_ERR);
        }
    }
    public void T000_STARTBR_BPTBRIS() throws IOException,SQLException,Exception {
        BPTBRIS_BR.rp = new DBParm();
        BPTBRIS_BR.rp.TableName = "BPTBRIS";
        BPTBRIS_BR.rp.where = "BR = :BPRBRIS.KEY.BR";
        IBS.STARTBR(SCCGWA, BPRBRIS, this, BPTBRIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_BRIS_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_BRIS_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTBRIS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_BPTBRIS() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRBRIS, this, BPTBRIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_BRIS_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_BRIS_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTBRIS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTBRIS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTBRIS_BR);
    }
    public void T000_READ_BPTORGI() throws IOException,SQLException,Exception {
        BPTORGI_RD = new DBParm();
        BPTORGI_RD.TableName = "BPTORGI";
        BPTORGI_RD.where = "ORGI_TYP = :BPRORGI.ORGI_TYP "
            + "AND INCO_DATE = :BPRORGI.INCO_DATE "
            + "AND ORGI_FLG = :BPRORGI.ORGI_FLG";
        IBS.READ(SCCGWA, BPRORGI, this, BPTORGI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ORGI_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ORGI_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTORGI";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_QUERY_BPTBRIS() throws IOException,SQLException,Exception {
        BPTBRIS_RD = new DBParm();
        BPTBRIS_RD.TableName = "BPTBRIS";
        BPTBRIS_RD.where = "LMT_CCY = :BPRBRIS.KEY.LMT_CCY "
            + "AND BR = :BPRBRIS.KEY.BR";
        IBS.READ(SCCGWA, BPRBRIS, this, BPTBRIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_BRIS_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_BRIS_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTBRIS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPORLO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG-LOW", BPCPORLO);
        if (BPCPORLO.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPORLO.RC);
        }
    }
    public void T000_READ_BPTFXORG() throws IOException,SQLException,Exception {
        BPTFXORG_RD = new DBParm();
        BPTFXORG_RD.TableName = "BPTFXORG";
        BPTFXORG_RD.where = "BR = :BPRFXORG.KEY.BR "
            + "AND INQ_TYP = '1' "
            + "AND STS = '1'";
        IBS.READ(SCCGWA, BPRFXORG, this, BPTFXORG_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FXORG_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FXORG_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTFXORG";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTORGR_FIRST() throws IOException,SQLException,Exception {
        BPTORGR_RD = new DBParm();
        BPTORGR_RD.TableName = "BPTORGR";
        BPTORGR_RD.where = "BNK = :BPRORGR.KEY.BNK "
            + "AND BR = :BPRORGR.KEY.BR "
            + "AND EXP_DT > :BPRORGR.EXP_DT";
        BPTORGR_RD.fst = true;
        BPTORGR_RD.order = "BNK,TYP,BR";
        IBS.READ(SCCGWA, BPRORGR, this, BPTORGR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ORGR_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ORGR_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTORGR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B020_WRITE_BPTORGI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRORGI);
        IBS.init(SCCGWA, BPCRORGI);
        BPCRORGI.INFO.FUNC = 'A';
        BPRORGI.KEY.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRORGI.KEY.MN_JRN = SCCGWA.COMM_AREA.JRN_NO;
        BPRORGI.TX_TM = SCCGWA.COMM_AREA.TR_TIME;
        BPRORGI.INCO_OLD_BR = BPCSCHBR.RC.OLD_BR;
        BPRORGI.INCO_NEW_BR = BPCSCHBR.RC.NEW_BR;
        BPRORGI.INCO_DATE = BPCSCHBR.RC.EFF_DT;
        BPRORGI.ORGI_TYP = '3';
        BPRORGI.ORGI_FLG = '0';
        BPRORGI.UPT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRORGI.UPT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRORGI.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRORGI.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRORGI.SUP_TLR = SCCGWA.COMM_AREA.SUP1_ID;
        CEP.TRC(SCCGWA, BPCSCHBR.RC.OLD_BR);
        CEP.TRC(SCCGWA, BPCSCHBR.RC.NEW_BR);
        S000_CALL_BPZRORGI();
        if (pgmRtn) return;
    }
    public void B030_WRITE_BPTORGL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRORGL);
        IBS.init(SCCGWA, BPCRORGL);
        BPCRORGL.INFO.FUNC = 'A';
        WS_SEQ += 1;
        CEP.TRC(SCCGWA, WS_SEQ);
        BPRORGL.KEY.TX_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRORGL.KEY.TX_JRN = SCCGWA.COMM_AREA.JRN_NO;
        BPRORGL.KEY.TX_SEQ = WS_SEQ;
        BPRORGL.AC_DT = BPCSCHBR.RC.EFF_DT;
        BPRORGL.INCO_OLD_BR = BPCSCHBR.RC.OLD_BR;
        BPRORGL.INCO_NEW_BR = BPCSCHBR.RC.NEW_BR;
        BPRORGL.TX_FLG = 'O';
        BPRORGL.TX_TM = SCCGWA.COMM_AREA.TR_TIME;
        BPRORGL.UPT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRORGL.UPT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRORGL.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRORGL.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, BPCSCHBR.RC.OLD_BR);
        CEP.TRC(SCCGWA, BPCSCHBR.RC.NEW_BR);
        S000_CALL_BPZRORGL();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRORGI() throws IOException,SQLException,Exception {
        BPCRORGI.INFO.POINTER = BPRORGI;
        BPCRORGI.INFO.LEN = 190;
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-ORGI", BPCRORGI);
        if (BPCRORGI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCRORGI.RC);
        }
    }
    public void S000_CALL_BPZRORGL() throws IOException,SQLException,Exception {
        BPCRORGL.INFO.POINTER = BPRORGL;
        BPCRORGL.INFO.LEN = 245;
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-ORGL", BPCRORGL);
        if (BPCRORGL.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCRORGL.RC);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPQORG.RC);
        }
    }
    public void S000_CALL_BPZUCHBR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-BPZUCHBR", BPCUCHBR);
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
        if (BPCSCHBR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCSCHBR=");
            CEP.TRC(SCCGWA, BPCSCHBR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
