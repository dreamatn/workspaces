package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.FX.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFXCUS {
    brParm FXTDIRFX_BR = new brParm();
    brParm FXTBFXT_BR = new brParm();
    DBParm FXTDIRFX_RD;
    DBParm FXTBFXT_RD;
    brParm BPTICSP_BR = new brParm();
    DBParm BPTICSP_RD;
    DBParm BPTTQP_RD;
    DBParm BPTEXRD_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    double WS_NEW_RATE = 0;
    double WS_OLD_RATE = 0;
    char WS_TABLE_REC = ' ';
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPRICSP BPRICSP = new BPRICSP();
    BPRICSP BPRICSPN = new BPRICSP();
    BPRICSP BPRICSPO = new BPRICSP();
    FXRDIRFX FXRDIRFX = new FXRDIRFX();
    FXRBFXT FXRBFXT = new FXRBFXT();
    CICCUST CICCUST = new CICCUST();
    FXCMSG_ERROR_MSG FXCMSG_ERROR_MSG = new FXCMSG_ERROR_MSG();
    BPREXRD BPREXRD = new BPREXRD();
    BPRTQP BPRTQP = new BPRTQP();
    String WS_OLD_CI_NO = " ";
    String WS_NEW_CI_NO = " ";
    String WS_AC_NO = " ";
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGMSG SCCGMSG;
    BPCFXCUS BPCFXCUS;
    public void MP(SCCGWA SCCGWA, BPCFXCUS BPCFXCUS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFXCUS = BPCFXCUS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFXCUS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        SCCGMSG = (SCCGMSG) SCCGSCA_SC_AREA.MSG_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'C';
        CICCUST.DATA.CI_NO = BPCFXCUS.NEW_CI_NO;
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCUST.RC);
        }
        if (BPCFXCUS.FUNC == '1') {
            B020_CINO_MODIFY_PROC();
            if (pgmRtn) return;
        } else if (BPCFXCUS.FUNC == '2') {
            B020_ACNO_MODIFY_PROC();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_IPT_FUNC_ERR);
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCFXCUS.FUNC == '2') {
            if (BPCFXCUS.AC_NO.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, FXCMSG_ERROR_MSG.FX_ERR_MUST_INPUT, BPCFXCUS.RC);
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_ERR_MUST_INPUT);
            }
        }
        if (BPCFXCUS.OLD_CI_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, FXCMSG_ERROR_MSG.FX_ERR_MUST_INPUT, BPCFXCUS.RC);
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_ERR_MUST_INPUT);
        }
        if (BPCFXCUS.NEW_CI_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, FXCMSG_ERROR_MSG.FX_ERR_MUST_INPUT, BPCFXCUS.RC);
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_ERR_MUST_INPUT);
        }
    }
    public void B020_CINO_MODIFY_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFXCUS.NEW_CI_NO);
        CEP.TRC(SCCGWA, BPCFXCUS.OLD_CI_NO);
        WS_OLD_CI_NO = BPCFXCUS.OLD_CI_NO;
        WS_NEW_CI_NO = BPCFXCUS.NEW_CI_NO;
        T000_STARTBR_FXTDIRFX_2();
        if (pgmRtn) return;
        T000_READNEXT_FXTDIRFX();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            FXRDIRFX.CI_NO = WS_NEW_CI_NO;
            FXRDIRFX.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
            FXRDIRFX.ID_NO = CICCUST.O_DATA.O_ID_NO;
            FXRDIRFX.CI_CNM = CICCUST.O_DATA.O_CI_NM;
            FXRDIRFX.CI_ENM = CICCUST.O_DATA.O_CI_ENM;
            T000_REWRITE_FXTDIRFX();
            if (pgmRtn) return;
            T000_READNEXT_FXTDIRFX();
            if (pgmRtn) return;
        }
        T000_ENDBR_FXTDIRFX();
        if (pgmRtn) return;
        T000_STARTBR_FXTBFXT_2();
        if (pgmRtn) return;
        T000_READNEXT_FXTBFXT();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            FXRBFXT.CI_NO = WS_NEW_CI_NO;
            FXRBFXT.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
            FXRBFXT.ID_NO = CICCUST.O_DATA.O_ID_NO;
            FXRBFXT.CI_CNM = CICCUST.O_DATA.O_CI_NM;
            FXRBFXT.CI_ENM = CICCUST.O_DATA.O_CI_ENM;
            T000_REWRITE_FXTBFXT();
            if (pgmRtn) return;
            T000_READNEXT_FXTBFXT();
            if (pgmRtn) return;
        }
        T000_ENDBR_FXTBFXT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRICSP);
        IBS.init(SCCGWA, BPRICSPN);
        IBS.init(SCCGWA, BPRICSPO);
        BPRICSP.KEY.CI_NO = BPCFXCUS.OLD_CI_NO;
        S000_STARTBR_BPTICSP();
        if (pgmRtn) return;
        S000_READ_NEXT_BPTICSP();
        if (pgmRtn) return;
        while (BPCFXCUS.DB_INFO != 'E') {
            CEP.TRC(SCCGWA, BPRICSP.KEY.CI_NO);
            CEP.TRC(SCCGWA, BPRICSP.EXR_TYP);
            CEP.TRC(SCCGWA, BPRICSP.CCY);
            S000_READ_UPD_BPTICSP();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, BPRICSP, BPRICSPO);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                S000_DELETE_BPTICSP();
                if (pgmRtn) return;
                BPRICSP.KEY.CI_NO = BPCFXCUS.NEW_CI_NO;
                IBS.CLONE(SCCGWA, BPRICSP, BPRICSPN);
                S000_ADD_BPTICSP();
                if (pgmRtn) return;
                if (BPCFXCUS.DB_INFO == 'D') {
                    S000_COMPARE_ICSP();
                    if (pgmRtn) return;
                }
            }
            S000_READ_NEXT_BPTICSP();
            if (pgmRtn) return;
            S000_WRITE_NHIS();
            if (pgmRtn) return;
        }
        S000_ENDBR_BPTICSP();
        if (pgmRtn) return;
    }
    public void B020_ACNO_MODIFY_PROC() throws IOException,SQLException,Exception {
        WS_NEW_CI_NO = BPCFXCUS.NEW_CI_NO;
        WS_AC_NO = BPCFXCUS.AC_NO;
        T000_STARTBR_FXTDIRFX_1();
        if (pgmRtn) return;
        T000_READNEXT_FXTDIRFX();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            FXRDIRFX.CI_NO = WS_NEW_CI_NO;
            FXRDIRFX.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
            FXRDIRFX.ID_NO = CICCUST.O_DATA.O_ID_NO;
            FXRDIRFX.CI_CNM = CICCUST.O_DATA.O_CI_NM;
            FXRDIRFX.CI_ENM = CICCUST.O_DATA.O_CI_ENM;
            T000_REWRITE_FXTDIRFX();
            if (pgmRtn) return;
            T000_READNEXT_FXTDIRFX();
            if (pgmRtn) return;
        }
        T000_ENDBR_FXTDIRFX();
        if (pgmRtn) return;
        T000_STARTBR_FXTBFXT_1();
        if (pgmRtn) return;
        T000_READNEXT_FXTBFXT();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            FXRBFXT.CI_NO = WS_NEW_CI_NO;
            FXRBFXT.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
            FXRBFXT.ID_NO = CICCUST.O_DATA.O_ID_NO;
            FXRBFXT.CI_CNM = CICCUST.O_DATA.O_CI_NM;
            FXRBFXT.CI_ENM = CICCUST.O_DATA.O_CI_ENM;
            T000_REWRITE_FXTBFXT();
            if (pgmRtn) return;
            T000_READNEXT_FXTBFXT();
            if (pgmRtn) return;
        }
        T000_ENDBR_FXTBFXT();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_FXTDIRFX_1() throws IOException,SQLException,Exception {
        FXTDIRFX_BR.rp = new DBParm();
        FXTDIRFX_BR.rp.TableName = "FXTDIRFX";
        FXTDIRFX_BR.rp.where = "( STATUS = 'U' "
            + "OR STATUS = 'E' ) "
            + "AND CI_NO < > :WS_NEW_CI_NO "
            + "AND ( DD_AC1 = :WS_AC_NO "
            + "OR DD_AC2 = :WS_AC_NO )";
        FXTDIRFX_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, FXRDIRFX, this, FXTDIRFX_BR);
    }
    public void T000_STARTBR_FXTDIRFX_2() throws IOException,SQLException,Exception {
        FXTDIRFX_BR.rp = new DBParm();
        FXTDIRFX_BR.rp.TableName = "FXTDIRFX";
        FXTDIRFX_BR.rp.where = "( STATUS = 'U' "
            + "OR STATUS = 'E' ) "
            + "AND CI_NO = :WS_OLD_CI_NO";
        FXTDIRFX_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, FXRDIRFX, this, FXTDIRFX_BR);
    }
    public void T000_STARTBR_FXTBFXT_1() throws IOException,SQLException,Exception {
        FXTBFXT_BR.rp = new DBParm();
        FXTBFXT_BR.rp.TableName = "FXTBFXT";
        FXTBFXT_BR.rp.where = "STATUS = 'U' "
            + "AND CI_NO < > :WS_NEW_CI_NO "
            + "AND TRA_AC = :WS_AC_NO";
        FXTBFXT_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, FXRBFXT, this, FXTBFXT_BR);
    }
    public void T000_STARTBR_FXTBFXT_2() throws IOException,SQLException,Exception {
        FXTBFXT_BR.rp = new DBParm();
        FXTBFXT_BR.rp.TableName = "FXTBFXT";
        FXTBFXT_BR.rp.where = "STATUS = 'U' "
            + "AND CI_NO = :WS_OLD_CI_NO";
        FXTBFXT_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, FXRBFXT, this, FXTBFXT_BR);
    }
    public void T000_READNEXT_FXTDIRFX() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, FXRDIRFX, this, FXTDIRFX_BR);
    }
    public void T000_REWRITE_FXTDIRFX() throws IOException,SQLException,Exception {
        FXTDIRFX_RD = new DBParm();
        FXTDIRFX_RD.TableName = "FXTDIRFX";
        IBS.REWRITE(SCCGWA, FXRDIRFX, FXTDIRFX_RD);
    }
    public void T000_ENDBR_FXTDIRFX() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, FXTDIRFX_BR);
    }
    public void T000_READNEXT_FXTBFXT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, FXRBFXT, this, FXTBFXT_BR);
    }
    public void T000_REWRITE_FXTBFXT() throws IOException,SQLException,Exception {
        FXTBFXT_RD = new DBParm();
        FXTBFXT_RD.TableName = "FXTBFXT";
        IBS.REWRITE(SCCGWA, FXRBFXT, FXTBFXT_RD);
    }
    public void T000_ENDBR_FXTBFXT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, FXTBFXT_BR);
    }
    public void S000_STARTBR_BPTICSP() throws IOException,SQLException,Exception {
        BPTICSP_BR.rp = new DBParm();
        BPTICSP_BR.rp.TableName = "BPTICSP";
        BPTICSP_BR.rp.eqWhere = "CI_NO";
        IBS.STARTBR(SCCGWA, BPRICSP, BPTICSP_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCFXCUS.DB_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCFXCUS.DB_INFO = 'N';
        } else {
            BPCFXCUS.DB_INFO = 'O';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_READ_NEXT_BPTICSP() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRICSP, this, BPTICSP_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCFXCUS.DB_INFO = 'E';
        }
    }
    public void S000_ENDBR_BPTICSP() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTICSP_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void S000_READ_UPD_BPTICSP() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRICSP.KEY.CI_NO);
        CEP.TRC(SCCGWA, BPRICSP.EXR_TYP);
        CEP.TRC(SCCGWA, BPRICSP.CCY);
        BPTICSP_RD = new DBParm();
        BPTICSP_RD.TableName = "BPTICSP";
        BPTICSP_RD.eqWhere = "CI_NO,EXR_TYP,CCY";
        BPTICSP_RD.upd = true;
        IBS.READ(SCCGWA, BPRICSP, BPTICSP_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCFXCUS.DB_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCFXCUS.DB_INFO = 'N';
        } else {
            BPCFXCUS.DB_INFO = 'O';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ADD_BPTICSP() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRICSP.KEY.CI_NO);
        CEP.TRC(SCCGWA, BPRICSP.EXR_TYP);
        CEP.TRC(SCCGWA, BPRICSP.CCY);
        BPTICSP_RD = new DBParm();
        BPTICSP_RD.TableName = "BPTICSP";
        BPTICSP_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRICSP, BPTICSP_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCFXCUS.DB_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCFXCUS.DB_INFO = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCFXCUS.DB_INFO = 'D';
        } else {
            BPCFXCUS.DB_INFO = 'O';
        }
    }
    public void S000_DELETE_BPTICSP() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRICSP.KEY.CI_NO);
        CEP.TRC(SCCGWA, BPRICSP.EXR_TYP);
        CEP.TRC(SCCGWA, BPRICSP.CCY);
        BPTICSP_RD = new DBParm();
        BPTICSP_RD.TableName = "BPTICSP";
        IBS.DELETE(SCCGWA, BPRICSP, BPTICSP_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void S000_COMPARE_ICSP() throws IOException,SQLException,Exception {
        if (BPRICSPO.CMP_FLG == BPRICSPN.CMP_FLG) {
            if (BPRICSPO.CS_BUY_P >= BPRICSPN.CS_BUY_P) {
            } else {
                BPRICSP.KEY.CI_NO = BPCFXCUS.OLD_CI_NO;
                S000_DELETE_BPTICSP();
                if (pgmRtn) return;
                BPRICSP.KEY.CI_NO = BPCFXCUS.NEW_CI_NO;
                S000_ADD_BPTICSP();
                if (pgmRtn) return;
            }
        } else {
            IBS.init(SCCGWA, BPRTQP);
            BPRTQP.KEY.BR = 706660800;
            BPRTQP.KEY.EXR_TYP = BPRICSP.EXR_TYP;
            BPRTQP.KEY.CCY = BPRICSP.CCY;
            BPTTQP_RD = new DBParm();
            BPTTQP_RD.TableName = "BPTTQP";
            BPTTQP_RD.eqWhere = "BR,EXR_TYP,CCY";
            IBS.READ(SCCGWA, BPRTQP, BPTTQP_RD);
            CEP.TRC(SCCGWA, BPRTQP.CS_BUY);
            if (BPRICSPO.CMP_FLG == '0') {
                WS_OLD_RATE = BPRTQP.CS_BUY * ( 1 + BPRICSPO.CS_BUY_P / 100 );
                IBS.init(SCCGWA, BPREXRD);
                BPREXRD.KEY.EXR_TYP = BPRICSPN.EXR_TYP;
                BPREXRD.KEY.CCY = BPRICSPN.CCY;
                BPTEXRD_RD = new DBParm();
                BPTEXRD_RD.TableName = "BPTEXRD";
                BPTEXRD_RD.eqWhere = "EXR_TYP,CCY";
                IBS.READ(SCCGWA, BPREXRD, BPTEXRD_RD);
                if (BPREXRD.EXR_PNT == '2') {
                    WS_NEW_RATE = BPRTQP.CS_BUY + BPRICSPN.CS_BUY_P * 0.01;
                } else if (BPREXRD.EXR_PNT == '3') {
                    WS_NEW_RATE = BPRTQP.CS_BUY + BPRICSPN.CS_BUY_P * 0.001;
                } else if (BPREXRD.EXR_PNT == '4') {
                    WS_NEW_RATE = BPRTQP.CS_BUY + BPRICSPN.CS_BUY_P * 0.0001;
                } else {
                }
            } else {
                IBS.init(SCCGWA, BPREXRD);
                BPREXRD.KEY.EXR_TYP = BPRICSPO.EXR_TYP;
                BPREXRD.KEY.CCY = BPRICSPO.CCY;
                BPTEXRD_RD = new DBParm();
                BPTEXRD_RD.TableName = "BPTEXRD";
                BPTEXRD_RD.eqWhere = "EXR_TYP,CCY";
                IBS.READ(SCCGWA, BPREXRD, BPTEXRD_RD);
                CEP.TRC(SCCGWA, BPREXRD.EXR_PNT);
                if (BPREXRD.EXR_PNT == '2') {
                    WS_OLD_RATE = BPRTQP.CS_BUY + BPRICSPO.CS_BUY_P * 0.01;
                } else if (BPREXRD.EXR_PNT == '3') {
                    WS_OLD_RATE = BPRTQP.CS_BUY + BPRICSPO.CS_BUY_P * 0.001;
                } else if (BPREXRD.EXR_PNT == '4') {
                    WS_OLD_RATE = BPRTQP.CS_BUY + BPRICSPO.CS_BUY_P * 0.0001;
                } else {
                }
                WS_NEW_RATE = BPRTQP.CS_BUY * ( 1 + BPRICSPN.CS_BUY_P / 100 );
            }
            if (WS_NEW_RATE >= WS_OLD_RATE) {
                BPRICSP.KEY.CI_NO = BPCFXCUS.OLD_CI_NO;
                S000_DELETE_BPTICSP();
                if (pgmRtn) return;
                BPRICSP.KEY.CI_NO = BPCFXCUS.NEW_CI_NO;
                S000_ADD_BPTICSP();
                if (pgmRtn) return;
            } else {
            }
        }
    }
    public void S000_WRITE_NHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.OLD_DAT_PT = BPRICSPO;
        BPCPNHIS.INFO.NEW_DAT_PT = BPRICSPN;
        BPCPNHIS.INFO.FMT_ID_LEN = 671;
        BPCPNHIS.INFO.REF_NO = "TBL-BPTICSP";
        BPCPNHIS.INFO.TX_RMK = "UPD-CI-NO";
        BPCPNHIS.INFO.FMT_ID = "BPRICSP";
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.FMT_ID_LEN);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.REF_NO);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_RMK);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.FMT_ID);
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFXCUS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
