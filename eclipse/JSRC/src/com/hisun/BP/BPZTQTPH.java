package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTQTPH {
    String JIBS_tmp_str[] = new String[10];
    brParm BPTSQTPH_BR = new brParm();
    DBParm BPTSQTPH_RD;
    boolean pgmRtn = false;
    String PGM_BPZTQTPH = "BPZTQTPH";
    String TBL_BPTSQTPH = "BPTSQTPH";
    int WS_DATE_P = 0;
    BPZTQTPH_WS_DATE WS_DATE = new BPZTQTPH_WS_DATE();
    int WS_EFF_STR_DATE = 0;
    int WS_EFF_END_DATE = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRSQTPH BPRSQTPH = new BPRSQTPH();
    SCCGWA SCCGWA;
    BPCRQTPH BPCRQTPH;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRSQTPH BPRSQTQH;
    public void MP(SCCGWA SCCGWA, BPCRQTPH BPCRQTPH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRQTPH = BPCRQTPH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTQTPH return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRSQTQH = (BPRSQTPH) BPCRQTPH.INFO.POINTER;
        IBS.init(SCCGWA, BPRSQTPH);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRSQTQH);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRSQTQH, BPRSQTPH);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRQTPH.INFO.FUNC == 'S') {
            B100_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRQTPH.INFO.FUNC == '1') {
            B101_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRQTPH.INFO.FUNC == '2') {
            B102_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRQTPH.INFO.FUNC == '3') {
            B103_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRQTPH.INFO.FUNC == '4') {
            B104_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRQTPH.INFO.FUNC == '5') {
            B105_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRQTPH.INFO.FUNC == '6') {
            B106_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRQTPH.INFO.FUNC == '7') {
            B107_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRQTPH.INFO.FUNC == '8') {
            B108_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRQTPH.INFO.FUNC == 'R') {
            B200_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRQTPH.INFO.FUNC == 'U') {
            B300_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRQTPH.INFO.FUNC == 'D') {
            B400_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRQTPH.INFO.FUNC == 'E') {
            B500_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRQTPH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRSQTPH);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRSQTPH, BPRSQTQH);
    }
    public void B100_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTSQTPH();
        if (pgmRtn) return;
    }
    public void B101_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        WS_EFF_STR_DATE = BPCRQTPH.EFF_STR_DATE;
        CEP.TRC(SCCGWA, BPRSQTPH.KEY.EXR_TYP);
        CEP.TRC(SCCGWA, BPRSQTPH.KEY.CCY);
        CEP.TRC(SCCGWA, WS_EFF_STR_DATE);
        T000_STARTBR_BPTSQTPH_1();
        if (pgmRtn) return;
    }
    public void B102_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTSQTPH_2();
        if (pgmRtn) return;
    }
    public void B103_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        WS_EFF_STR_DATE = BPRSQTPH.KEY.EFF_DT;
        WS_EFF_END_DATE = BPRSQTPH.EXP_DT;
        T000_STARTBR_BPTSQTPH_3();
        if (pgmRtn) return;
    }
    public void B104_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTSQTPH_4();
        if (pgmRtn) return;
    }
    public void B105_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTSQTPH_5();
        if (pgmRtn) return;
    }
    public void B106_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        WS_EFF_STR_DATE = BPCRQTPH.EFF_STR_DATE;
        WS_EFF_END_DATE = BPCRQTPH.EFF_END_DATE;
        T000_STARTBR_BPTSQTPH_6();
        if (pgmRtn) return;
    }
    public void B107_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        WS_EFF_STR_DATE = BPCRQTPH.EFF_STR_DATE;
        WS_EFF_END_DATE = BPCRQTPH.EFF_END_DATE;
        T000_STARTBR_BPTSQTPH_7();
        if (pgmRtn) return;
    }
    public void B108_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTSQTPH_8();
        if (pgmRtn) return;
    }
    public void B300_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTSQTPH();
        if (pgmRtn) return;
    }
    public void B400_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTSQTPH();
        if (pgmRtn) return;
    }
    public void B200_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTSQTPH();
        if (pgmRtn) return;
    }
    public void B500_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTSQTPH();
        if (pgmRtn) return;
    }
    public void T000_READNEXT_BPTSQTPH() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRSQTPH, this, BPTSQTPH_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRQTPH.INFO.RTN_INFO = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRQTPH.INFO.RTN_INFO = 'N';
        } else {
        }
        CEP.TRC(SCCGWA, BPCRQTPH.RC);
        CEP.TRC(SCCGWA, BPCRQTPH.INFO.RTN_INFO);
    }
    public void T000_STARTBR_BPTSQTPH() throws IOException,SQLException,Exception {
        BPTSQTPH_BR.rp = new DBParm();
        BPTSQTPH_BR.rp.TableName = "BPTSQTPH";
        BPTSQTPH_BR.rp.order = "EFF_DT DESC";
        IBS.STARTBR(SCCGWA, BPRSQTPH, BPTSQTPH_BR);
    }
    public void T000_STARTBR_BPTSQTPH_1() throws IOException,SQLException,Exception {
        BPTSQTPH_BR.rp = new DBParm();
        BPTSQTPH_BR.rp.TableName = "BPTSQTPH";
        BPTSQTPH_BR.rp.where = "EXR_TYP = :BPRSQTPH.KEY.EXR_TYP "
            + "AND CCY = :BPRSQTPH.KEY.CCY "
            + "AND EFF_DT <= :WS_EFF_STR_DATE "
            + "AND EXP_DT >= :WS_EFF_STR_DATE";
        BPTSQTPH_BR.rp.order = "EFF_DT DESC";
        IBS.STARTBR(SCCGWA, BPRSQTPH, this, BPTSQTPH_BR);
    }
    public void T000_STARTBR_BPTSQTPH_2() throws IOException,SQLException,Exception {
        BPTSQTPH_BR.rp = new DBParm();
        BPTSQTPH_BR.rp.TableName = "BPTSQTPH";
        BPTSQTPH_BR.rp.where = "EXR_TYP = :BPRSQTPH.KEY.EXR_TYP "
            + "AND CCY = :BPRSQTPH.KEY.CCY "
            + "AND EFF_DT <= :BPRSQTPH.KEY.EFF_DT "
            + "AND EXP_DT >= :BPRSQTPH.KEY.EFF_DT";
        BPTSQTPH_BR.rp.order = "CCY";
        IBS.STARTBR(SCCGWA, BPRSQTPH, this, BPTSQTPH_BR);
    }
    public void T000_STARTBR_BPTSQTPH_3() throws IOException,SQLException,Exception {
        BPTSQTPH_BR.rp = new DBParm();
        BPTSQTPH_BR.rp.TableName = "BPTSQTPH";
        BPTSQTPH_BR.rp.where = "EXR_TYP LIKE :BPRSQTPH.KEY.EXR_TYP "
            + "AND CCY LIKE :BPRSQTPH.KEY.CCY "
            + "AND EFF_DT >= :BPRSQTPH.KEY.EFF_DT "
            + "AND EFF_DT <= :BPRSQTPH.EXP_DT";
        BPTSQTPH_BR.rp.order = "EXR_TYP,CCY,EFF_DT";
        IBS.STARTBR(SCCGWA, BPRSQTPH, this, BPTSQTPH_BR);
    }
    public void T000_STARTBR_BPTSQTPH_4() throws IOException,SQLException,Exception {
        BPTSQTPH_BR.rp = new DBParm();
        BPTSQTPH_BR.rp.TableName = "BPTSQTPH";
        BPTSQTPH_BR.rp.where = "EXR_TYP = :BPRSQTPH.KEY.EXR_TYP "
            + "AND CCY = :BPRSQTPH.KEY.CCY "
            + "AND EXP_DT = 99991231";
        BPTSQTPH_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, BPRSQTPH, this, BPTSQTPH_BR);
    }
    public void T000_STARTBR_BPTSQTPH_5() throws IOException,SQLException,Exception {
        BPTSQTPH_BR.rp = new DBParm();
        BPTSQTPH_BR.rp.TableName = "BPTSQTPH";
        BPTSQTPH_BR.rp.where = "EXR_TYP = :BPRSQTPH.KEY.EXR_TYP "
            + "AND CCY = :BPRSQTPH.KEY.CCY";
        BPTSQTPH_BR.rp.order = "EFF_DT DESC";
        IBS.STARTBR(SCCGWA, BPRSQTPH, this, BPTSQTPH_BR);
    }
    public void T000_STARTBR_BPTSQTPH_6() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRSQTPH.KEY.EXR_TYP);
        CEP.TRC(SCCGWA, BPRSQTPH.KEY.CCY);
        CEP.TRC(SCCGWA, WS_EFF_STR_DATE);
        BPTSQTPH_BR.rp = new DBParm();
        BPTSQTPH_BR.rp.TableName = "BPTSQTPH";
        BPTSQTPH_BR.rp.where = "EXR_TYP = :BPRSQTPH.KEY.EXR_TYP "
            + "AND CCY = :BPRSQTPH.KEY.CCY "
            + "AND EFF_DT <= :WS_EFF_STR_DATE "
            + "AND EXP_DT >= :WS_EFF_STR_DATE";
        IBS.STARTBR(SCCGWA, BPRSQTPH, this, BPTSQTPH_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRQTPH.INFO.RTN_INFO = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRQTPH.INFO.RTN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTSQTPH_7() throws IOException,SQLException,Exception {
        BPTSQTPH_BR.rp = new DBParm();
        BPTSQTPH_BR.rp.TableName = "BPTSQTPH";
        BPTSQTPH_BR.rp.where = "EXR_TYP = :BPRSQTPH.KEY.EXR_TYP "
            + "AND CCY = :BPRSQTPH.KEY.CCY "
            + "AND EFF_DT <= :WS_EFF_END_DATE "
            + "AND EXP_DT > :WS_EFF_STR_DATE";
        BPTSQTPH_BR.rp.order = "EFF_DT DESC";
        IBS.STARTBR(SCCGWA, BPRSQTPH, this, BPTSQTPH_BR);
    }
    public void T000_STARTBR_BPTSQTPH_8() throws IOException,SQLException,Exception {
        BPTSQTPH_BR.rp = new DBParm();
        BPTSQTPH_BR.rp.TableName = "BPTSQTPH";
        BPTSQTPH_BR.rp.where = "EXP_DT < :BPRSQTPH.EXP_DT";
        BPTSQTPH_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, BPRSQTPH, this, BPTSQTPH_BR);
    }
    public void T000_REWRITE_BPTSQTPH() throws IOException,SQLException,Exception {
        BPTSQTPH_RD = new DBParm();
        BPTSQTPH_RD.TableName = "BPTSQTPH";
        IBS.REWRITE(SCCGWA, BPRSQTPH, BPTSQTPH_RD);
    }
    public void T000_DELETE_BPTSQTPH() throws IOException,SQLException,Exception {
        BPTSQTPH_RD = new DBParm();
        BPTSQTPH_RD.TableName = "BPTSQTPH";
        IBS.DELETE(SCCGWA, BPRSQTPH, BPTSQTPH_RD);
    }
    public void T000_ENDBR_BPTSQTPH() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTSQTPH_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRQTPH.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRQTPH = ");
            CEP.TRC(SCCGWA, BPCRQTPH);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
