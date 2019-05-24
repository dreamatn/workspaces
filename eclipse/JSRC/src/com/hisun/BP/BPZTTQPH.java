package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTTQPH {
    brParm BPTTQPH_BR = new brParm();
    DBParm BPTTQPH_RD;
    boolean pgmRtn = false;
    String PGM_BPZTTQPH = "BPZTTQPH";
    String TBL_BPTTQPH = "BPTTQPH ";
    int WS_STR_DATE = 0;
    int WS_END_DATE = 0;
    int WS_STR_TIME = 0;
    int WS_END_TIME = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRTQPH BPRTQPH = new BPRTQPH();
    SCCGWA SCCGWA;
    BPCRTQPH BPCRTQPH;
    BPRTQPH BPRTQPI;
    public void MP(SCCGWA SCCGWA, BPCRTQPH BPCRTQPH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRTQPH = BPCRTQPH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTTQPH return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRTQPI = (BPRTQPH) BPCRTQPH.INFO.POINTER;
        IBS.init(SCCGWA, BPRTQPH);
        CEP.TRC(SCCGWA, BPCRTQPH.INFO.LEN);
        IBS.CLONE(SCCGWA, BPRTQPI, BPRTQPH);
        CEP.TRC(SCCGWA, BPRTQPI);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRTQPH.INFO.FUNC == '1') {
            B011_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTQPH.INFO.FUNC == '2') {
            B012_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTQPH.INFO.FUNC == '3') {
            B013_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTQPH.INFO.FUNC == '4') {
            B014_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTQPH.INFO.FUNC == '5') {
            B015_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTQPH.INFO.FUNC == '6') {
            B016_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTQPH.INFO.FUNC == '7') {
            B017_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTQPH.INFO.FUNC == '8') {
            B018_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTQPH.INFO.FUNC == '9') {
            B019_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTQPH.INFO.FUNC == 'R') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTQPH.INFO.FUNC == 'D') {
            B030_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRTQPH.INFO.FUNC == 'E') {
            B040_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRTQPH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRTQPH);
        CEP.TRC(SCCGWA, JIBS_tmp_str[0]);
        IBS.CLONE(SCCGWA, BPRTQPH, BPRTQPI);
        CEP.TRC(SCCGWA, BPRTQPH.KEY.FWD_TENOR);
    }
    public void B011_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        WS_STR_DATE = BPCRTQPH.STR_DATE;
        WS_END_DATE = BPCRTQPH.END_DATE;
        if (BPRTQPH.KEY.BR == 0) {
            T000_STARTBR_BPTTQPH11();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_BPTTQPH12();
            if (pgmRtn) return;
        }
    }
    public void B012_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        WS_STR_DATE = BPCRTQPH.STR_DATE;
        WS_END_DATE = BPCRTQPH.END_DATE;
        if (BPRTQPH.KEY.BR == 0) {
            T000_STARTBR_BPTTQPH21();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_BPTTQPH22();
            if (pgmRtn) return;
        }
    }
    public void B013_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        WS_STR_DATE = BPCRTQPH.STR_DATE;
        WS_END_DATE = BPCRTQPH.END_DATE;
        if (BPRTQPH.KEY.BR == 0) {
            T000_STARTBR_BPTTQPH31();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_BPTTQPH32();
            if (pgmRtn) return;
        }
    }
    public void B014_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        WS_STR_DATE = BPCRTQPH.STR_DATE;
        WS_END_DATE = BPCRTQPH.END_DATE;
        if (BPRTQPH.KEY.BR == 0) {
            T000_STARTBR_BPTTQPH41();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_BPTTQPH42();
            if (pgmRtn) return;
        }
    }
    public void B015_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        WS_STR_DATE = BPCRTQPH.STR_DATE;
        T000_STARTBR_BPTTQPH51();
        if (pgmRtn) return;
    }
    public void B016_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        WS_STR_DATE = BPCRTQPH.STR_DATE;
        WS_STR_TIME = BPCRTQPH.STR_TIME;
        CEP.TRC(SCCGWA, WS_STR_TIME);
        T000_STARTBR_BPTTQPH61();
        if (pgmRtn) return;
    }
    public void B017_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        WS_STR_DATE = BPRTQPH.EFF_DT;
        WS_STR_TIME = BPRTQPH.EFF_TM;
        WS_END_DATE = BPRTQPH.KEY.EXP_DT;
        WS_END_TIME = BPRTQPH.KEY.EXP_TM;
        if (BPRTQPH.KEY.BR == 0) {
            T000_STARTBR_BPTTQPH71();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_BPTTQPH72();
            if (pgmRtn) return;
        }
    }
    public void B018_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTTQPH8();
        if (pgmRtn) return;
    }
    public void B019_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTTQPH9();
        if (pgmRtn) return;
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTTQPH();
        if (pgmRtn) return;
    }
    public void B030_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTTQPH();
        if (pgmRtn) return;
    }
    public void B040_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTTQPH();
        if (pgmRtn) return;
    }
    public void T000_READNEXT_BPTTQPH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRTQPH.EFF_DT);
        CEP.TRC(SCCGWA, BPRTQPH.EFF_TM);
        CEP.TRC(SCCGWA, BPRTQPH.KEY.EXR_TYP);
        CEP.TRC(SCCGWA, BPRTQPH.KEY.FWD_TENOR);
        CEP.TRC(SCCGWA, BPRTQPH.KEY.BR);
        CEP.TRC(SCCGWA, BPRTQPH.KEY.CCY);
        CEP.TRC(SCCGWA, BPRTQPH.KEY.CORR_CCY);
        IBS.READNEXT(SCCGWA, BPRTQPH, this, BPTTQPH_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRTQPH.INFO.RTN_INFO = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRTQPH.INFO.RTN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTTQPH11() throws IOException,SQLException,Exception {
        if (BPRTQPH.KEY.EXR_TYP.equalsIgnoreCase("%%%")) {
            if (BPRTQPH.KEY.CCY.equalsIgnoreCase("%%%")) {
                if (WS_STR_DATE == WS_END_DATE) {
                    BPTTQPH_BR.rp = new DBParm();
                    BPTTQPH_BR.rp.TableName = "BPTTQPH";
                    BPTTQPH_BR.rp.where = "EXP_DT = :WS_STR_DATE";
                    IBS.STARTBR(SCCGWA, BPRTQPH, this, BPTTQPH_BR);
                } else {
                    BPTTQPH_BR.rp = new DBParm();
                    BPTTQPH_BR.rp.TableName = "BPTTQPH";
                    BPTTQPH_BR.rp.where = "EXP_DT BETWEEN :WS_STR_DATE "
                        + "AND :WS_END_DATE";
                    IBS.STARTBR(SCCGWA, BPRTQPH, this, BPTTQPH_BR);
                }
            } else {
                if (WS_STR_DATE == WS_END_DATE) {
                    BPTTQPH_BR.rp = new DBParm();
                    BPTTQPH_BR.rp.TableName = "BPTTQPH";
                    BPTTQPH_BR.rp.where = "CCY = :BPRTQPH.KEY.CCY "
                        + "AND EXP_DT = :WS_STR_DATE";
                    IBS.STARTBR(SCCGWA, BPRTQPH, this, BPTTQPH_BR);
                } else {
                    BPTTQPH_BR.rp = new DBParm();
                    BPTTQPH_BR.rp.TableName = "BPTTQPH";
                    BPTTQPH_BR.rp.where = "CCY = :BPRTQPH.KEY.CCY "
                        + "AND EXP_DT BETWEEN :WS_STR_DATE "
                        + "AND :WS_END_DATE";
                    IBS.STARTBR(SCCGWA, BPRTQPH, this, BPTTQPH_BR);
                }
            }
        } else {
            if (BPRTQPH.KEY.CCY.equalsIgnoreCase("%%%")) {
                if (WS_STR_DATE == WS_END_DATE) {
                    BPTTQPH_BR.rp = new DBParm();
                    BPTTQPH_BR.rp.TableName = "BPTTQPH";
                    BPTTQPH_BR.rp.where = "EXR_TYP = :BPRTQPH.KEY.EXR_TYP "
                        + "AND EXP_DT = :WS_STR_DATE";
                    IBS.STARTBR(SCCGWA, BPRTQPH, this, BPTTQPH_BR);
                } else {
                    BPTTQPH_BR.rp = new DBParm();
                    BPTTQPH_BR.rp.TableName = "BPTTQPH";
                    BPTTQPH_BR.rp.where = "EXR_TYP = :BPRTQPH.KEY.EXR_TYP "
                        + "AND EXP_DT BETWEEN :WS_STR_DATE "
                        + "AND :WS_END_DATE";
                    IBS.STARTBR(SCCGWA, BPRTQPH, this, BPTTQPH_BR);
                }
            } else {
                if (WS_STR_DATE == WS_END_DATE) {
                    BPTTQPH_BR.rp = new DBParm();
                    BPTTQPH_BR.rp.TableName = "BPTTQPH";
                    BPTTQPH_BR.rp.where = "EXR_TYP = :BPRTQPH.KEY.EXR_TYP "
                        + "AND CCY = :BPRTQPH.KEY.CCY "
                        + "AND EXP_DT = :WS_STR_DATE";
                    IBS.STARTBR(SCCGWA, BPRTQPH, this, BPTTQPH_BR);
                } else {
                    BPTTQPH_BR.rp = new DBParm();
                    BPTTQPH_BR.rp.TableName = "BPTTQPH";
                    BPTTQPH_BR.rp.where = "EXR_TYP = :BPRTQPH.KEY.EXR_TYP "
                        + "AND CCY = :BPRTQPH.KEY.CCY "
                        + "AND EXP_DT BETWEEN :WS_STR_DATE "
                        + "AND :WS_END_DATE";
                    IBS.STARTBR(SCCGWA, BPRTQPH, this, BPTTQPH_BR);
                }
            }
        }
    }
    public void T000_STARTBR_BPTTQPH12() throws IOException,SQLException,Exception {
        if (BPRTQPH.KEY.EXR_TYP.equalsIgnoreCase("%%%")) {
            if (BPRTQPH.KEY.CCY.equalsIgnoreCase("%%%")) {
                if (WS_STR_DATE == WS_END_DATE) {
                    BPTTQPH_BR.rp = new DBParm();
                    BPTTQPH_BR.rp.TableName = "BPTTQPH";
                    BPTTQPH_BR.rp.where = "EXP_DT = :WS_STR_DATE";
                    BPTTQPH_BR.rp.order = "EXR_TYP";
                    IBS.STARTBR(SCCGWA, BPRTQPH, this, BPTTQPH_BR);
                } else {
                    BPTTQPH_BR.rp = new DBParm();
                    BPTTQPH_BR.rp.TableName = "BPTTQPH";
                    BPTTQPH_BR.rp.where = "EXP_DT BETWEEN :WS_STR_DATE "
                        + "AND :WS_END_DATE";
                    BPTTQPH_BR.rp.order = "EXR_TYP";
                    IBS.STARTBR(SCCGWA, BPRTQPH, this, BPTTQPH_BR);
                }
            } else {
                if (WS_STR_DATE == WS_END_DATE) {
                    BPTTQPH_BR.rp = new DBParm();
                    BPTTQPH_BR.rp.TableName = "BPTTQPH";
                    BPTTQPH_BR.rp.where = "CCY = :BPRTQPH.KEY.CCY "
                        + "AND EXP_DT = :WS_STR_DATE";
                    BPTTQPH_BR.rp.order = "EXR_TYP";
                    IBS.STARTBR(SCCGWA, BPRTQPH, this, BPTTQPH_BR);
                } else {
                    BPTTQPH_BR.rp = new DBParm();
                    BPTTQPH_BR.rp.TableName = "BPTTQPH";
                    BPTTQPH_BR.rp.where = "CCY = :BPRTQPH.KEY.CCY "
                        + "AND EXP_DT BETWEEN :WS_STR_DATE "
                        + "AND :WS_END_DATE";
                    BPTTQPH_BR.rp.order = "EXR_TYP";
                    IBS.STARTBR(SCCGWA, BPRTQPH, this, BPTTQPH_BR);
                }
            }
        } else {
            if (BPRTQPH.KEY.CCY.equalsIgnoreCase("%%%")) {
                if (WS_STR_DATE == WS_END_DATE) {
                    BPTTQPH_BR.rp = new DBParm();
                    BPTTQPH_BR.rp.TableName = "BPTTQPH";
                    BPTTQPH_BR.rp.where = "EXR_TYP = :BPRTQPH.KEY.EXR_TYP "
                        + "AND EXP_DT = :WS_STR_DATE";
                    IBS.STARTBR(SCCGWA, BPRTQPH, this, BPTTQPH_BR);
                } else {
                    BPTTQPH_BR.rp = new DBParm();
                    BPTTQPH_BR.rp.TableName = "BPTTQPH";
                    BPTTQPH_BR.rp.where = "EXR_TYP = :BPRTQPH.KEY.EXR_TYP "
                        + "AND EXP_DT BETWEEN :WS_STR_DATE "
                        + "AND :WS_END_DATE";
                    IBS.STARTBR(SCCGWA, BPRTQPH, this, BPTTQPH_BR);
                }
            } else {
                if (WS_STR_DATE == WS_END_DATE) {
                    BPTTQPH_BR.rp = new DBParm();
                    BPTTQPH_BR.rp.TableName = "BPTTQPH";
                    BPTTQPH_BR.rp.where = "EXR_TYP = :BPRTQPH.KEY.EXR_TYP "
                        + "AND CCY = :BPRTQPH.KEY.CCY "
                        + "AND EXP_DT = :WS_STR_DATE";
                    IBS.STARTBR(SCCGWA, BPRTQPH, this, BPTTQPH_BR);
                } else {
                    BPTTQPH_BR.rp = new DBParm();
                    BPTTQPH_BR.rp.TableName = "BPTTQPH";
                    BPTTQPH_BR.rp.where = "EXR_TYP = :BPRTQPH.KEY.EXR_TYP "
                        + "AND CCY = :BPRTQPH.KEY.CCY "
                        + "AND EXP_DT BETWEEN :WS_STR_DATE "
                        + "AND :WS_END_DATE";
                    IBS.STARTBR(SCCGWA, BPRTQPH, this, BPTTQPH_BR);
                }
            }
        }
    }
    public void T000_STARTBR_BPTTQPH21() throws IOException,SQLException,Exception {
        if (BPRTQPH.KEY.CCY.equalsIgnoreCase("%%%")) {
            if (WS_STR_DATE == WS_END_DATE) {
                BPTTQPH_BR.rp = new DBParm();
                BPTTQPH_BR.rp.TableName = "BPTTQPH";
                BPTTQPH_BR.rp.where = "EXP_DT = :WS_STR_DATE";
                IBS.STARTBR(SCCGWA, BPRTQPH, this, BPTTQPH_BR);
            } else {
                BPTTQPH_BR.rp = new DBParm();
                BPTTQPH_BR.rp.TableName = "BPTTQPH";
                BPTTQPH_BR.rp.where = "EXP_DT BETWEEN :WS_STR_DATE "
                    + "AND :WS_END_DATE";
                IBS.STARTBR(SCCGWA, BPRTQPH, this, BPTTQPH_BR);
            }
        } else {
            if (WS_STR_DATE == WS_END_DATE) {
                BPTTQPH_BR.rp = new DBParm();
                BPTTQPH_BR.rp.TableName = "BPTTQPH";
                BPTTQPH_BR.rp.where = "CCY = :BPRTQPH.KEY.CCY "
                    + "AND EXP_DT = :WS_STR_DATE";
                IBS.STARTBR(SCCGWA, BPRTQPH, this, BPTTQPH_BR);
            } else {
                BPTTQPH_BR.rp = new DBParm();
                BPTTQPH_BR.rp.TableName = "BPTTQPH";
                BPTTQPH_BR.rp.where = "CCY = :BPRTQPH.KEY.CCY "
                    + "AND EXP_DT BETWEEN :WS_STR_DATE "
                    + "AND :WS_END_DATE";
                IBS.STARTBR(SCCGWA, BPRTQPH, this, BPTTQPH_BR);
            }
        }
    }
    public void T000_STARTBR_BPTTQPH22() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTTQPH21();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTTQPH31() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTTQPH11();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTTQPH32() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTTQPH11();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTTQPH41() throws IOException,SQLException,Exception {
        if (BPRTQPH.KEY.CCY.equalsIgnoreCase("%%%")) {
            if (WS_STR_DATE == WS_END_DATE) {
                BPTTQPH_BR.rp = new DBParm();
                BPTTQPH_BR.rp.TableName = "BPTTQPH";
                BPTTQPH_BR.rp.where = "EXP_DT = :WS_STR_DATE";
                IBS.STARTBR(SCCGWA, BPRTQPH, this, BPTTQPH_BR);
            } else {
                BPTTQPH_BR.rp = new DBParm();
                BPTTQPH_BR.rp.TableName = "BPTTQPH";
                BPTTQPH_BR.rp.where = "EXP_DT BETWEEN :WS_STR_DATE "
                    + "AND :WS_END_DATE";
                IBS.STARTBR(SCCGWA, BPRTQPH, this, BPTTQPH_BR);
            }
        } else {
            if (WS_STR_DATE == WS_END_DATE) {
                BPTTQPH_BR.rp = new DBParm();
                BPTTQPH_BR.rp.TableName = "BPTTQPH";
                BPTTQPH_BR.rp.where = "CCY = :BPRTQPH.KEY.CCY "
                    + "AND EXP_DT = :WS_STR_DATE";
                IBS.STARTBR(SCCGWA, BPRTQPH, this, BPTTQPH_BR);
            } else {
                BPTTQPH_BR.rp = new DBParm();
                BPTTQPH_BR.rp.TableName = "BPTTQPH";
                BPTTQPH_BR.rp.where = "CCY = :BPRTQPH.KEY.CCY "
                    + "AND EXP_DT BETWEEN :WS_STR_DATE "
                    + "AND :WS_END_DATE";
                IBS.STARTBR(SCCGWA, BPRTQPH, this, BPTTQPH_BR);
            }
        }
    }
    public void T000_STARTBR_BPTTQPH42() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTTQPH41();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTTQPH51() throws IOException,SQLException,Exception {
        BPTTQPH_BR.rp = new DBParm();
        BPTTQPH_BR.rp.TableName = "BPTTQPH";
        BPTTQPH_BR.rp.where = "EXR_TYP = :BPRTQPH.KEY.EXR_TYP "
            + "AND CCY = :BPRTQPH.KEY.CCY "
            + "AND EFF_DT < :WS_STR_DATE";
        BPTTQPH_BR.rp.order = "EFF_DT DESC, EFF_TM DESC";
        IBS.STARTBR(SCCGWA, BPRTQPH, this, BPTTQPH_BR);
    }
    public void T000_STARTBR_BPTTQPH61() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRTQPH.KEY.EXR_TYP);
        CEP.TRC(SCCGWA, BPRTQPH.KEY.CCY);
        BPTTQPH_BR.rp = new DBParm();
        BPTTQPH_BR.rp.TableName = "BPTTQPH";
        BPTTQPH_BR.rp.where = "EXR_TYP = :BPRTQPH.KEY.EXR_TYP "
            + "AND CCY = :BPRTQPH.KEY.CCY "
            + "AND ( ( EFF_DT = :WS_STR_DATE "
            + "AND EFF_TM <= :WS_STR_TIME ) "
            + "OR EFF_DT < :WS_STR_DATE )";
        BPTTQPH_BR.rp.order = "EFF_DT DESC,EFF_TM DESC";
        IBS.STARTBR(SCCGWA, BPRTQPH, this, BPTTQPH_BR);
    }
    public void T000_STARTBR_BPTTQPH71() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_STR_DATE);
        CEP.TRC(SCCGWA, WS_END_DATE);
        CEP.TRC(SCCGWA, BPRTQPH.KEY.EXR_TYP);
        CEP.TRC(SCCGWA, BPRTQPH.KEY.FWD_TENOR);
        CEP.TRC(SCCGWA, BPRTQPH.KEY.CCY);
        CEP.TRC(SCCGWA, BPRTQPH.KEY.CORR_CCY);
        if (BPRTQPH.KEY.EXR_TYP.equalsIgnoreCase("%%%")) {
            if (BPRTQPH.KEY.CCY.equalsIgnoreCase("%%%")) {
                BPTTQPH_BR.rp = new DBParm();
                BPTTQPH_BR.rp.TableName = "BPTTQPH";
                BPTTQPH_BR.rp.where = "EFF_DT <= :WS_END_DATE "
                    + "AND EXP_DT >= :WS_STR_DATE";
                BPTTQPH_BR.rp.order = "EXR_TYP,CCY, EFF_DT DESC,EFF_TM DESC";
                IBS.STARTBR(SCCGWA, BPRTQPH, this, BPTTQPH_BR);
            } else {
                BPTTQPH_BR.rp = new DBParm();
                BPTTQPH_BR.rp.TableName = "BPTTQPH";
                BPTTQPH_BR.rp.where = "CCY = :BPRTQPH.KEY.CCY "
                    + "AND ( EFF_DT <= :WS_END_DATE "
                    + "AND EXP_DT >= :WS_STR_DATE )";
                BPTTQPH_BR.rp.order = "EXR_TYP,CCY, EFF_DT DESC,EFF_TM DESC";
                IBS.STARTBR(SCCGWA, BPRTQPH, this, BPTTQPH_BR);
            }
        } else {
            if (BPRTQPH.KEY.CCY.equalsIgnoreCase("%%%")) {
                BPTTQPH_BR.rp = new DBParm();
                BPTTQPH_BR.rp.TableName = "BPTTQPH";
                BPTTQPH_BR.rp.where = "EXR_TYP = :BPRTQPH.KEY.EXR_TYP "
                    + "AND ( EFF_DT <= :WS_END_DATE "
                    + "AND EXP_DT >= :WS_STR_DATE )";
                BPTTQPH_BR.rp.order = "CCY, EFF_DT DESC,EFF_TM DESC";
                IBS.STARTBR(SCCGWA, BPRTQPH, this, BPTTQPH_BR);
            } else {
                CEP.TRC(SCCGWA, "DEVHZ750");
                BPTTQPH_BR.rp = new DBParm();
                BPTTQPH_BR.rp.TableName = "BPTTQPH";
                BPTTQPH_BR.rp.where = "EXR_TYP = :BPRTQPH.KEY.EXR_TYP "
                    + "AND CCY = :BPRTQPH.KEY.CCY "
                    + "AND BR = :BPRTQPH.KEY.BR "
                    + "AND CORR_CCY = :BPRTQPH.KEY.CORR_CCY "
                    + "AND ( EFF_DT <= :WS_END_DATE "
                    + "AND EXP_DT >= :WS_STR_DATE )";
                BPTTQPH_BR.rp.order = "CCY, EFF_DT DESC,EFF_TM DESC";
                IBS.STARTBR(SCCGWA, BPRTQPH, this, BPTTQPH_BR);
            }
        }
    }
    public void T000_STARTBR_BPTTQPH72() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRTQPH.KEY.EXR_TYP);
        CEP.TRC(SCCGWA, BPRTQPH.KEY.FWD_TENOR);
        CEP.TRC(SCCGWA, BPRTQPH.KEY.CCY);
        CEP.TRC(SCCGWA, BPRTQPH.KEY.CORR_CCY);
        CEP.TRC(SCCGWA, WS_STR_DATE);
        CEP.TRC(SCCGWA, WS_END_DATE);
        T000_STARTBR_BPTTQPH71();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTTQPH8() throws IOException,SQLException,Exception {
        BPTTQPH_BR.rp = new DBParm();
        BPTTQPH_BR.rp.TableName = "BPTTQPH";
        BPTTQPH_BR.rp.where = "EXP_DT < :BPRTQPH.KEY.EXP_DT";
        BPTTQPH_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, BPRTQPH, this, BPTTQPH_BR);
    }
    public void T000_STARTBR_BPTTQPH9() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRTQPH.EFF_DT);
        CEP.TRC(SCCGWA, BPRTQPH.EFF_TM);
        CEP.TRC(SCCGWA, BPRTQPH.KEY.EXR_TYP);
        CEP.TRC(SCCGWA, BPRTQPH.KEY.FWD_TENOR);
        CEP.TRC(SCCGWA, BPRTQPH.KEY.BR);
        CEP.TRC(SCCGWA, BPRTQPH.KEY.CCY);
        CEP.TRC(SCCGWA, BPRTQPH.KEY.CORR_CCY);
        BPTTQPH_BR.rp = new DBParm();
        BPTTQPH_BR.rp.TableName = "BPTTQPH";
        BPTTQPH_BR.rp.where = "EXR_TYP = :BPRTQPH.KEY.EXR_TYP "
            + "AND CCY = :BPRTQPH.KEY.CCY "
            + "AND EFF_DT = :BPRTQPH.EFF_DT "
            + "AND EFF_TM = :BPRTQPH.EFF_TM";
        IBS.STARTBR(SCCGWA, BPRTQPH, this, BPTTQPH_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_DELETE_BPTTQPH() throws IOException,SQLException,Exception {
        BPTTQPH_RD = new DBParm();
        BPTTQPH_RD.TableName = "BPTTQPH";
        IBS.DELETE(SCCGWA, BPRTQPH, BPTTQPH_RD);
    }
    public void T000_ENDBR_BPTTQPH() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTTQPH_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRTQPH.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRTQPH = ");
            CEP.TRC(SCCGWA, BPCRTQPH);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
