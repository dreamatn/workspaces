package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPCSBR {
    brParm BPTCLIB_BR = new brParm();
    brParm BPTCMOV_BR = new brParm();
    DBParm BPTBRIS_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_P_INQ_ORG_STATION = "BP-P-INQ-ORG-STATION";
    String CPN_R_BRW_BMOV = "BP-R-BRW-BMOV ";
    String TBL_BPTCMOV = "BPTCMOB ";
    String TBL_BPTCLIB = "BPTCLIB ";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    String WS_ERR_INF = " ";
    int WS_CNT = 0;
    String WS_CCY = " ";
    double WS_CCY_TOTAL = 0;
    double WS_CCY_TLTAL = 0;
    int WS_DAYS = 0;
    char WS_DATA_OVERFLOW_FLAG = ' ';
    int WS_BR = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRCLIB BPRCLIB = new BPRCLIB();
    BPRBRIS BPRBRIS = new BPRBRIS();
    BPRCMOV BPRCMOV = new BPRCMOV();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCGWA SCCGWA;
    BPCPCSBR BPCPCSBR;
    public void MP(SCCGWA SCCGWA, BPCPCSBR BPCPCSBR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPCSBR = BPCPCSBR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPCSBR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBRIS);
        IBS.init(SCCGWA, BPRCLIB);
        IBS.init(SCCGWA, BPRCMOV);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B030_CHECK_CS_WAY();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCPCSBR.BR == ' ') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCPCSBR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_CHECK_CS_LMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCLIB);
        WS_BR = BPCPCSBR.BR;
        CEP.TRC(SCCGWA, WS_BR);
        T000_STARTBR_BPTCLIB_BR();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRCLIB);
        T000_READNEXT_BPTCLIB();
        if (pgmRtn) return;
        WS_CCY = BPRCLIB.KEY.CCY;
        CEP.TRC(SCCGWA, WS_CCY);
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            if (BPRCLIB.KEY.CCY.equalsIgnoreCase(WS_CCY)) {
                WS_CCY_TLTAL = WS_CCY_TLTAL + BPRCLIB.BAL;
            } else {
                IBS.init(SCCGWA, BPRBRIS);
                BPRBRIS.KEY.LMT_CCY = WS_CCY;
                BPRBRIS.KEY.BR = WS_BR;
                T000_CALL_BPTBRIS();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, WS_CCY_TLTAL);
                CEP.TRC(SCCGWA, BPRBRIS.LMT_U);
                CEP.TRC(SCCGWA, BPRBRIS.LMT_L);
                if (WS_CCY_TLTAL > BPRBRIS.LMT_U) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR85, BPRBRIS.KEY.LMT_CCY);
                }
                if (WS_CCY_TLTAL < BPRBRIS.LMT_L) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR86, BPRBRIS.KEY.LMT_CCY);
                }
                WS_CCY = " ";
                WS_CCY = BPRCLIB.KEY.CCY;
                WS_CCY_TOTAL = 0;
                WS_CCY_TLTAL = WS_CCY_TLTAL + BPRCLIB.BAL;
            }
            IBS.init(SCCGWA, BPRCLIB);
            T000_READNEXT_BPTCLIB();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                IBS.init(SCCGWA, BPRBRIS);
                BPRBRIS.KEY.LMT_CCY = WS_CCY;
                BPRBRIS.KEY.BR = WS_BR;
                T000_CALL_BPTBRIS();
                if (pgmRtn) return;
                if (WS_CCY_TLTAL > BPRBRIS.LMT_U) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR85, BPRBRIS.KEY.LMT_CCY);
                }
                if (WS_CCY_TLTAL < BPRBRIS.LMT_L) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR86, BPRBRIS.KEY.LMT_CCY);
                }
            }
        }
        T000_ENDBR_BPTCLIB();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTCLIB_BR() throws IOException,SQLException,Exception {
        BPTCLIB_BR.rp = new DBParm();
        BPTCLIB_BR.rp.TableName = "BPTCLIB";
        BPTCLIB_BR.rp.where = "BR = :WS_BR";
        BPTCLIB_BR.rp.order = "CCY DESC";
        IBS.STARTBR(SCCGWA, BPRCLIB, this, BPTCLIB_BR);
    }
    public void T000_STARTBR_BPTCMOV_BR() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "IN_BR = :BPRCMOV.IN_BR "
            + "AND MOV_STS = :BPRCMOV.MOV_STS";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_READNEXT_BPTCLIB() throws IOException,SQLException,Exception {
        BPTCLIB_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRCLIB, this, BPTCLIB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0'
            || SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTCLIB;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_BPTCMOV_BR() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0'
            || SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTCMOV;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTCLIB() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTCLIB_BR);
    }
    public void T000_ENDBR_BPTCMOV_BR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTCMOV_BR);
    }
    public void T000_CALL_BPTBRIS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRBRIS.KEY.BR);
        CEP.TRC(SCCGWA, BPRBRIS.KEY.LMT_CCY);
        BPTBRIS_RD = new DBParm();
        BPTBRIS_RD.TableName = "BPTBRIS";
        BPTBRIS_RD.where = "BR = :BPRBRIS.KEY.BR "
            + "AND LMT_CCY = :BPRBRIS.KEY.LMT_CCY";
        BPTBRIS_RD.upd = true;
        IBS.READ(SCCGWA, BPRBRIS, this, BPTBRIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR87, BPRBRIS.KEY.LMT_CCY);
        }
    }
    public void B030_CHECK_CS_WAY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCMOV);
        BPRCMOV.IN_BR = BPCPCSBR.BR;
        BPRCMOV.MOV_STS = '1';
        T000_STARTBR_BPTCMOV_BR();
        if (pgmRtn) return;
        T000_READNEXT_BPTCMOV_BR();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            if (BPRCMOV.MOV_TYP == '4') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR88, BPCPCSBR.RC);
            }
            if (BPRCMOV.MOV_TYP == '5') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR89, BPCPCSBR.RC);
            }
            if (BPRCMOV.MOV_TYP == '1') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR95, BPCPCSBR.RC);
            }
            if (BPRCMOV.MOV_TYP == '2') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR96, BPCPCSBR.RC);
            }
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = BPRCMOV.KEY.MOV_DT;
            SCCCLDT.DATE2 = SCCGWA.COMM_AREA.AC_DATE;
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            WS_DAYS = 0;
            WS_DAYS = SCCCLDT.DAYS;
            CEP.TRC(SCCGWA, WS_DAYS);
            if (WS_DAYS >= BPRCMOV.ONWAY_DT) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR90, BPCPCSBR.RC);
            }
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPCSBR.RC);
            if (!JIBS_tmp_str[0].equalsIgnoreCase("0")) {
                Z_RET();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, BPRCMOV);
            T000_READNEXT_BPTCMOV_BR();
            if (pgmRtn) return;
        }
        T000_ENDBR_BPTCMOV_BR();
        if (pgmRtn) return;
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            CEP.ERR(SCCGWA, SCCCLDT.RC);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INF);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPCSBR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPCSBR = ");
            CEP.TRC(SCCGWA, BPCPCSBR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
