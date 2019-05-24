package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUFHIS {
    DBParm BPTFHIST_RD;
    brParm BPTFHIST_BR = new brParm();
    DBParm BPTFHIS_RD;
    brParm BPTFHIS_BR = new brParm();
    boolean pgmRtn = false;
    String PGM_BPZUFHIS = "BPZUFHIS";
    String TBL_BPTFHIST = "BPTFHIST";
    String TBL_BPTFHIS = "BPTFHIS";
    short WS_JRN_SEQ = 0;
    char WS_JRN_IN_USE = ' ';
    char WS_TBL_FHIS_FLAG = ' ';
    char WS_TABLE_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRFHIS BPRFHIS = new BPRFHIS();
    SCCGWA SCCGWA;
    BPCUFHIS BPCUFHIS;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCRCWAT SCRCWA;
    SCCBATH SCCBATH;
    BPRFHIST BPRFHIST;
    public void MP(SCCGWA SCCGWA, BPCUFHIS BPCUFHIS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUFHIS = BPCUFHIS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUFHIS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRFHIST = (BPRFHIST) BPCUFHIS.DATA.POINTER;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCBATH = (SCCBATH) SCCGWA.COMM_AREA.BAT_AREA_PTR;
        SCRCWA = (SCRCWAT) GWA_SC_AREA.CWA_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCUFHIS.RC);
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            A010_INIT_PROC_CN();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_IN_USE);
    }
    public void A010_INIT_PROC_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRFHIST.KEY.AC_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (BPRFHIST.KEY.AC_DT == SCCGWA.COMM_AREA.AC_DATE) {
            WS_TABLE_FLG = 'T';
            IBS.init(SCCGWA, BPRFHIS);
            IBS.CLONE(SCCGWA, BPRFHIST, BPRFHIS);
        } else {
            if (BPRFHIST.KEY.AC_DT > SCCGWA.COMM_AREA.AC_DATE) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_DATE_GT_ACDATE, BPCUFHIS.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                WS_TABLE_FLG = 'H';
            }
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCUFHIS.DATA.FUNC);
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            if (BPCUFHIS.DATA.FUNC == '1') {
                B010_CREATE_PROC_CN();
                if (pgmRtn) return;
            } else if (BPCUFHIS.DATA.FUNC == '3') {
                B020_STARTBR_UPD_PROC_CN();
                if (pgmRtn) return;
            } else if (BPCUFHIS.DATA.FUNC == '4') {
                B030_READNEXT_PROC_CN();
                if (pgmRtn) return;
            } else if (BPCUFHIS.DATA.FUNC == '5') {
                B040_ENDBR_PROC_CN();
                if (pgmRtn) return;
            } else if (BPCUFHIS.DATA.FUNC == '2') {
                B050_UPDATE_PROC_CN();
                if (pgmRtn) return;
            } else if (BPCUFHIS.DATA.FUNC == '6') {
                B060_STARTBR_SUMUP_UPD_PROC_CN();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, SCCEXCP);
                SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "BPZUFHIS INVALID FUNC(" + BPCUFHIS.DATA.FUNC + ")";
                CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
            }
            if (WS_TABLE_FLG == 'H') {
            } else {
                IBS.CLONE(SCCGWA, BPRFHIS, BPRFHIST);
            }
        } else {
            if (BPCUFHIS.DATA.FUNC == '1') {
                B010_CREATE_PROC();
                if (pgmRtn) return;
            } else if (BPCUFHIS.DATA.FUNC == '3') {
                B020_STARTBR_UPD_PROC();
                if (pgmRtn) return;
            } else if (BPCUFHIS.DATA.FUNC == '4') {
                B030_READNEXT_PROC();
                if (pgmRtn) return;
            } else if (BPCUFHIS.DATA.FUNC == '5') {
                B040_ENDBR_PROC();
                if (pgmRtn) return;
            } else if (BPCUFHIS.DATA.FUNC == '2') {
                B050_UPDATE_PROC();
                if (pgmRtn) return;
            } else if (BPCUFHIS.DATA.FUNC == '6') {
                B060_STARTBR_SUMUP_UPD_PROC();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, SCCEXCP);
                SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "BPZUFHIS INVALID FUNC(" + BPCUFHIS.DATA.FUNC + ")";
                CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
            }
        }
    }
    public void B010_CREATE_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRFHIST.KEY.AC_DT);
        CEP.TRC(SCCGWA, BPRFHIST.KEY.JRNNO);
        CEP.TRC(SCCGWA, BPRFHIST.KEY.JRN_SEQ);
        CEP.TRC(SCCGWA, BPRFHIST.VCHNO);
        CEP.TRC(SCCGWA, BPRFHIST.REF_NO);
        CEP.TRC(SCCGWA, BPRFHIST.TX_TOOL);
        CEP.TRC(SCCGWA, BPRFHIST.RLT_REF_NO);
        CEP.TRC(SCCGWA, BPRFHIST.APP_MMO);
        CEP.TRC(SCCGWA, BPRFHIST.TX_CD);
        CEP.TRC(SCCGWA, BPRFHIST.TX_STS);
        CEP.TRC(SCCGWA, BPRFHIST.TX_DT);
        CEP.TRC(SCCGWA, BPRFHIST.TX_TM);
        CEP.TRC(SCCGWA, BPRFHIST.TX_VAL_DT);
        CEP.TRC(SCCGWA, BPRFHIST.TX_REV_DT);
        CEP.TRC(SCCGWA, BPRFHIST.PRINT_FLG);
        CEP.TRC(SCCGWA, BPRFHIST.TX_CCY);
        CEP.TRC(SCCGWA, BPRFHIST.TX_AMT);
        CEP.TRC(SCCGWA, BPRFHIST.SUMUP_FLG);
        CEP.TRC(SCCGWA, BPRFHIST.DRCRFLG);
        CEP.TRC(SCCGWA, BPRFHIST.PROD_CD);
        CEP.TRC(SCCGWA, BPRFHIST.CI_NO);
        CEP.TRC(SCCGWA, BPRFHIST.TX_BR);
        CEP.TRC(SCCGWA, BPRFHIST.TX_DP);
        CEP.TRC(SCCGWA, BPRFHIST.REMARK);
        CEP.TRC(SCCGWA, BPRFHIST.RLT_AC_NAME);
        CEP.TRC(SCCGWA, BPRFHIST.TX_CHNL);
        CEP.TRC(SCCGWA, BPRFHIST.TX_TLR);
        CEP.TRC(SCCGWA, BPRFHIST.SUP1);
        CEP.TRC(SCCGWA, BPRFHIST.SUP2);
        CEP.TRC(SCCGWA, BPRFHIST.TS);
        T000_WRITE_BPTFHIST();
        if (pgmRtn) return;
    }
    public void B010_CREATE_PROC_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BEFORE CREATE");
        CEP.TRC(SCCGWA, BPRFHIS);
        CEP.TRC(SCCGWA, BPRFHIS.VCHNO);
        CEP.TRC(SCCGWA, BPRFHIS.REF_NO);
        CEP.TRC(SCCGWA, BPRFHIS.TX_TOOL);
        CEP.TRC(SCCGWA, BPRFHIS.RLT_REF_NO);
        CEP.TRC(SCCGWA, BPRFHIS.APP_MMO);
        CEP.TRC(SCCGWA, BPRFHIS.TX_CD);
        CEP.TRC(SCCGWA, BPRFHIS.TX_STS);
        CEP.TRC(SCCGWA, BPRFHIS.TX_DT);
        CEP.TRC(SCCGWA, BPRFHIS.TX_TM);
        CEP.TRC(SCCGWA, BPRFHIS.TX_VAL_DT);
        CEP.TRC(SCCGWA, BPRFHIS.TX_REV_DT);
        CEP.TRC(SCCGWA, BPRFHIS.PRINT_FLG);
        CEP.TRC(SCCGWA, BPRFHIS.TX_CCY);
        CEP.TRC(SCCGWA, BPRFHIS.TX_AMT);
        CEP.TRC(SCCGWA, BPRFHIS.SUMUP_FLG);
        CEP.TRC(SCCGWA, BPRFHIS.DRCRFLG);
        CEP.TRC(SCCGWA, BPRFHIS.PROD_CD);
        CEP.TRC(SCCGWA, BPRFHIS.CI_NO);
        CEP.TRC(SCCGWA, BPRFHIS.TX_BR);
        CEP.TRC(SCCGWA, BPRFHIS.TX_DP);
        CEP.TRC(SCCGWA, BPRFHIS.REMARK);
        CEP.TRC(SCCGWA, BPRFHIS.RLT_AC_NAME);
        CEP.TRC(SCCGWA, BPRFHIS.TX_CHNL);
        CEP.TRC(SCCGWA, BPRFHIS.TX_TLR);
        CEP.TRC(SCCGWA, BPRFHIS.SUP1);
        CEP.TRC(SCCGWA, BPRFHIS.SUP2);
        CEP.TRC(SCCGWA, BPRFHIS.TS);
        WS_JRN_IN_USE = SCCGWA.COMM_AREA.JRN_IN_USE;
        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            if (BPRFHIS.KEY.AC_DT == SCCBATH.JPRM.AC_DATE) {
                SCCGWA.COMM_AREA.JRN_IN_USE = SCCBATH.JPRM.JRN_IND;
            } else {
                if (SCCBATH.JPRM.JRN_IND == '1') {
                    SCCGWA.COMM_AREA.JRN_IN_USE = '2';
                } else {
                    SCCGWA.COMM_AREA.JRN_IN_USE = '1';
                }
            }
        }
        if (BPRFHIS.KEY.JRNNO > 0) {
            T200_WRITE_BPTFHIS_CN();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_JRNNO_CANTNOT_ZERO, BPCUFHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        SCCGWA.COMM_AREA.JRN_IN_USE = WS_JRN_IN_USE;
    }
    public void B020_STARTBR_UPD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTFHIST_UPD();
        if (pgmRtn) return;
    }
    public void B030_READNEXT_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTFHIST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRFHIST.KEY.AC_DT);
        CEP.TRC(SCCGWA, BPRFHIST.KEY.JRNNO);
        CEP.TRC(SCCGWA, BPRFHIST.KEY.JRN_SEQ);
    }
    public void B040_ENDBR_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTFHIST();
        if (pgmRtn) return;
    }
    public void B050_UPDATE_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTFHIST();
        if (pgmRtn) return;
    }
    public void B060_STARTBR_SUMUP_UPD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTFHIST_UPD_2();
        if (pgmRtn) return;
    }
    public void T000_WRITE_BPTFHIST() throws IOException,SQLException,Exception {
        BPTFHIST_RD = new DBParm();
        BPTFHIST_RD.TableName = "BPTFHIST";
        BPTFHIST_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRFHIST, BPTFHIST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "NORMAL");
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.TRC(SCCGWA, "DUPKEY");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CREATE_REC_ERR, BPCUFHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "OTHER ");
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTFHIST;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_BPTFHIST() throws IOException,SQLException,Exception {
        BPTFHIST_RD = new DBParm();
        BPTFHIST_RD.TableName = "BPTFHIST";
        IBS.REWRITE(SCCGWA, BPRFHIST, BPTFHIST_RD);
    }
    public void T000_STARTBR_BPTFHIST_UPD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRFHIST.KEY.AC_DT);
        CEP.TRC(SCCGWA, BPRFHIST.KEY.JRNNO);
        BPTFHIST_BR.rp = new DBParm();
        BPTFHIST_BR.rp.TableName = "BPTFHIST";
        BPTFHIST_BR.rp.upd = true;
        BPTFHIST_BR.rp.col = "AC_DT,JRNNO,JRN_SEQ,VCHNO, TX_BR,TX_DP,TX_DT,TX_TM,TX_CHNL,TX_REQFM, APP_MMO,TX_CD,DRCRFLG,PROD_CD,COM_PROD, PRDMO_CD,REF_NO,BV_CODE,HEAD_NO, BV_NO,CI_NO,AC,TX_TOOL,OTH_AC,OTH_TX_TOOL, RLT_AC,RLT_AC_NAME,RLT_TX_TOOL,RLT_BANK, RLT_REF_NO,RLT_CCY,TX_CCY,TX_CCY_TYPE, TX_AMT,TX_MMO,TX_STS,TX_VAL_DT,SUMUP_FLG, PRINT_FLG,REMARK,NARRATIVE,TX_TLR, MAKER,SUP1,SUP2,TX_REV_DT,ORG_AC_DT, ORG_JRNNO,UPDATE_DT";
        BPTFHIST_BR.rp.where = "AC_DT = :BPRFHIST.KEY.AC_DT "
            + "AND JRNNO = :BPRFHIST.KEY.JRNNO "
            + "AND TX_STS < > 'C'";
        IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
    }
    public void T000_READNEXT_BPTFHIST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_END_OF_TABLE, BPCUFHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ NEXT BPTFHIST ERR";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_ENDBR_BPTFHIST() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTFHIST_BR);
    }
    public void T000_STARTBR_BPTFHIST_UPD_2() throws IOException,SQLException,Exception {
        BPTFHIST_BR.rp = new DBParm();
        BPTFHIST_BR.rp.TableName = "BPTFHIST";
        BPTFHIST_BR.rp.upd = true;
        BPTFHIST_BR.rp.col = "AC_DT,JRNNO,JRN_SEQ,VCHNO, TX_BR,TX_DP,TX_DT,TX_TM,TX_CHNL,TX_REQFM, APP_MMO,TX_CD,DRCRFLG,PROD_CD,COM_PROD, PRDMO_CD,REF_NO,BV_CODE,HEAD_NO, BV_NO,CI_NO,AC,TX_TOOL,OTH_AC,OTH_TX_TOOL, RLT_AC,RLT_AC_NAME,RLT_TX_TOOL,RLT_BANK, RLT_REF_NO,RLT_CCY,TX_CCY,TX_CCY_TYPE, TX_AMT,TX_MMO,TX_STS,TX_VAL_DT,SUMUP_FLG, PRINT_FLG,REMARK,NARRATIVE,TX_TLR, MAKER,SUP1,SUP2,TX_REV_DT,ORG_AC_DT, ORG_JRNNO,UPDATE_DT";
        BPTFHIST_BR.rp.where = "AC_DT = :BPRFHIST.KEY.AC_DT "
            + "AND AC = :BPRFHIST.KEY.AC "
            + "AND TX_CCY = :BPRFHIST.TX_CCY "
            + "AND DRCRFLG = :BPRFHIST.DRCRFLG";
        IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
    }
    public void B020_STARTBR_UPD_PROC_CN() throws IOException,SQLException,Exception {
        if (WS_TABLE_FLG == 'H') {
            T000_STARTBR_BPTFHIST_UPD();
            if (pgmRtn) return;
        } else {
            T200_STARTBR_BPTFHIS_UPD_CN();
            if (pgmRtn) return;
        }
    }
    public void B030_READNEXT_PROC_CN() throws IOException,SQLException,Exception {
        if (WS_TABLE_FLG == 'H') {
            T000_READNEXT_BPTFHIST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPRFHIST.KEY.AC_DT);
            CEP.TRC(SCCGWA, BPRFHIST.KEY.JRNNO);
            CEP.TRC(SCCGWA, BPRFHIST.KEY.JRN_SEQ);
            CEP.TRC(SCCGWA, BPRFHIST.RLT_AC);
        } else {
            T200_READNEXT_BPTFHIS_CN();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPRFHIS.KEY.AC_DT);
            CEP.TRC(SCCGWA, BPRFHIS.KEY.JRNNO);
            CEP.TRC(SCCGWA, BPRFHIS.KEY.JRN_SEQ);
            CEP.TRC(SCCGWA, BPRFHIS.RLT_AC);
        }
    }
    public void B040_ENDBR_PROC_CN() throws IOException,SQLException,Exception {
        if (WS_TABLE_FLG == 'H') {
            T000_ENDBR_BPTFHIST();
            if (pgmRtn) return;
        } else {
            T200_ENDBR_BPTFHIS_CN();
            if (pgmRtn) return;
        }
    }
    public void B050_UPDATE_PROC_CN() throws IOException,SQLException,Exception {
        if (WS_TABLE_FLG == 'H') {
            T000_REWRITE_BPTFHIST();
            if (pgmRtn) return;
        } else {
            T200_REWRITE_BPTFHIS_CN();
            if (pgmRtn) return;
        }
    }
    public void B060_STARTBR_SUMUP_UPD_PROC_CN() throws IOException,SQLException,Exception {
        if (WS_TABLE_FLG == 'H') {
            T000_STARTBR_BPTFHIST_UPD_2();
            if (pgmRtn) return;
        } else {
            T200_STARTBR_BPTFHIS_UPD_2_CN();
            if (pgmRtn) return;
        }
    }
    public void T200_WRITE_BPTFHIS_CN() throws IOException,SQLException,Exception {
        BPTFHIS_RD = new DBParm();
        if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_RD.TableName = "BPTFHIS1";
        else BPTFHIS_RD.TableName = "BPTFHIS2";
        BPTFHIS_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRFHIS, BPTFHIS_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "NORMAL");
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.TRC(SCCGWA, "DUPKEY");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CREATE_REC_ERR, BPCUFHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "OTHER ");
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTFHIS;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T200_REWRITE_BPTFHIS_CN() throws IOException,SQLException,Exception {
        BPTFHIS_RD = new DBParm();
        if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_RD.TableName = "BPTFHIS1";
        else BPTFHIS_RD.TableName = "BPTFHIS2";
        IBS.REWRITE(SCCGWA, BPRFHIS, BPTFHIS_RD);
    }
    public void T200_STARTBR_BPTFHIS_UPD_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRFHIS.KEY.AC_DT);
        CEP.TRC(SCCGWA, BPRFHIS.KEY.JRNNO);
        BPTFHIS_BR.rp = new DBParm();
        if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
        else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
        BPTFHIS_BR.rp.upd = true;
        BPTFHIS_BR.rp.col = "AC_DT,JRNNO,JRN_SEQ,VCHNO, TX_BR,TX_DP,TX_DT,TX_TM,TX_CHNL,TX_REQFM, APP_MMO,TX_CD,DRCRFLG,PROD_CD,COM_PROD, PRDMO_CD,REF_NO,BV_CODE,HEAD_NO, BV_NO,CI_NO,AC,TX_TOOL,OTH_AC,OTH_TX_TOOL, RLT_AC,RLT_AC_NAME,RLT_TX_TOOL,RLT_BANK, RLT_REF_NO,RLT_CCY,TX_CCY,TX_CCY_TYPE, TX_AMT,TX_MMO,TX_STS,TX_VAL_DT,SUMUP_FLG, PRINT_FLG,REMARK,NARRATIVE,TX_TLR, MAKER,SUP1,SUP2,TX_REV_DT,ORG_AC_DT, ORG_JRNNO,UPDATE_DT";
        BPTFHIS_BR.rp.where = "AC_DT = :BPRFHIS.KEY.AC_DT "
            + "AND JRNNO = :BPRFHIS.KEY.JRNNO "
            + "AND TX_STS < > 'C'";
        IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T200_READNEXT_BPTFHIS_CN() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_END_OF_TABLE, BPCUFHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ NEXT BPTFHIS ERR";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T200_ENDBR_BPTFHIS_CN() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTFHIS_BR);
    }
    public void T200_STARTBR_BPTFHIS_UPD_2_CN() throws IOException,SQLException,Exception {
        BPTFHIS_BR.rp = new DBParm();
        if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
        else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
        BPTFHIS_BR.rp.upd = true;
        BPTFHIS_BR.rp.col = "AC_DT,JRNNO,JRN_SEQ,VCHNO, TX_BR,TX_DP,TX_DT,TX_TM,TX_CHNL,TX_REQFM, APP_MMO,TX_CD,DRCRFLG,PROD_CD,COM_PROD, PRDMO_CD,REF_NO,BV_CODE,HEAD_NO, BV_NO,CI_NO,AC,TX_TOOL,OTH_AC,OTH_TX_TOOL, RLT_AC,RLT_AC_NAME,RLT_TX_TOOL,RLT_BANK, RLT_REF_NO,RLT_CCY,TX_CCY,TX_CCY_TYPE, TX_AMT,TX_MMO,TX_STS,TX_VAL_DT,SUMUP_FLG, PRINT_FLG,REMARK,NARRATIVE,TX_TLR, MAKER,SUP1,SUP2,TX_REV_DT,ORG_AC_DT, ORG_JRNNO,UPDATE_DT";
        BPTFHIS_BR.rp.where = "AC_DT = :BPRFHIS.KEY.AC_DT "
            + "AND AC = :BPRFHIS.KEY.AC "
            + "AND TX_CCY = :BPRFHIS.TX_CCY "
            + "AND DRCRFLG = :BPRFHIS.DRCRFLG";
        IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCUFHIS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCUFHIS = ");
            CEP.TRC(SCCGWA, BPCUFHIS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
