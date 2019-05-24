package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPRMBR {
    String JIBS_tmp_str[] = new String[10];
    brParm BPTPARM_BR = new brParm();
    BPZPRMBR_WS_REQ_INFO[] WS_REQ_INFO = new BPZPRMBR_WS_REQ_INFO[3];
    int WS_I = 0;
    char WS_INITF_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    char WS_END_FLG = ' ';
    BPRPARM BPRPARM = new BPRPARM();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPCPRMB BPCPRMB;
    BPRPRMT BPRPRMT = new BPRPRMT();
    public BPZPRMBR() {
        for (int i=0;i<3;i++) WS_REQ_INFO[i] = new BPZPRMBR_WS_REQ_INFO();
    }
    public void MP(SCCGWA SCCGWA, BPCPRMB BPCPRMB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPRMB = BPCPRMB;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZPRMBR return!");
        Z_RET();
        JIBS_RETURN();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        if (WS_INITF_FLG == ' ') {
            WS_INITF_FLG = 'Y';
        }
        BPCPRMB.RC.RC_APP = "BP";
        BPCPRMB.RC.RC_RTNCODE = 0;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMB.DAT_PTR);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT);
        if (BPRPRMT.KEY.BK.trim().length() == 0) {
            BPRPRMT.KEY.BK = SCCGWA.COMM_AREA.TR_BANK;
        }
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCPRMB.FUNC == '0') {
            B01_STARTBR();
        } else if (BPCPRMB.FUNC == '1') {
            B02_READNEXT();
        } else if (BPCPRMB.FUNC == '2') {
            B03_ENDBR();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCPRMB.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B01_STARTBR() throws IOException,SQLException,Exception {
        B01_01_GET_REQID();
        B01_03_STARTBR_DB();
        WS_REQ_INFO[BPCPRMB.REQID-1].WS_REQ_FST_FLG = 'Y';
    }
    public void B01_01_GET_REQID() throws IOException,SQLException,Exception {
        WS_FOUND_FLG = ' ';
        for (WS_I = 1; WS_I <= 3 
            && WS_FOUND_FLG != 'Y'; WS_I += 1) {
            if (WS_REQ_INFO[WS_I-1].WS_REQ_USED_FLG != 'Y') {
                WS_FOUND_FLG = 'Y';
                BPCPRMB.REQID = (short) WS_I;
                WS_REQ_INFO[WS_I-1].WS_REQ_USED_FLG = 'Y';
            }
        }
        if (WS_FOUND_FLG != 'Y') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "NO FREE REQID";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B01_03_STARTBR_DB() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPARM);
        BPRPARM.KEY.TYPE = BPRPRMT.KEY.TYP;
        BPRPARM.KEY.CODE = BPRPRMT.KEY.CD;
        BPRPARM.KEY.IBS_AC_BK = BPRPRMT.KEY.BK;
        BPRPARM.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, BPRPARM.KEY.CODE);
        if (BPCPRMB.REQID == 1) {
            T00_STARTBR_BPTPARM_1();
        } else if (BPCPRMB.REQID == 2) {
            T00_STARTBR_BPTPARM_2();
        } else if (BPCPRMB.REQID == 3) {
            T00_STARTBR_BPTPARM_3();
        } else {
        }
    }
    public void B02_READNEXT() throws IOException,SQLException,Exception {
        if (BPCPRMB.REQID < 1 
            || BPCPRMB.REQID > 3 
            || WS_REQ_INFO[BPCPRMB.REQID-1].WS_REQ_USED_FLG != 'Y') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID REQUEST";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        B02_02_READNEXT_DB();
    }
    public void B02_02_READNEXT_DB() throws IOException,SQLException,Exception {
        WS_END_FLG = ' ';
        WS_FOUND_FLG = ' ';
        while (WS_END_FLG != 'Y' 
            && WS_FOUND_FLG != 'Y') {
            if (BPCPRMB.REQID == 1) {
                T00_READNEXT_BPTPARM_1();
            } else if (BPCPRMB.REQID == 2) {
                T00_READNEXT_BPTPARM_2();
            } else if (BPCPRMB.REQID == 3) {
                T00_READNEXT_BPTPARM_3();
            } else {
            }
            if (WS_END_FLG != 'Y') {
                if (WS_REQ_INFO[BPCPRMB.REQID-1].WS_REQ_FST_FLG == 'Y' 
                    || !BPRPARM.KEY.CODE.equalsIgnoreCase(BPRPRMT.KEY.CD)) {
                    WS_FOUND_FLG = 'Y';
                }
            }
        }
        if (WS_FOUND_FLG == 'Y') {
            BPRPRMT.KEY.CD = BPRPARM.KEY.CODE;
            BPRPRMT.DESC = BPRPARM.DESC;
            BPRPRMT.CDESC = BPRPARM.CDESC;
            CEP.TRC(SCCGWA, BPRPARM.BLOB_VAL.trim().length());
            CEP.TRC(SCCGWA, BPCPRMB.RC);
            JIBS_tmp_str[0] = BPRPARM.BLOB_VAL;
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
            CEP.TRC(SCCGWA, BPCPRMB.RC);
            WS_REQ_INFO[BPCPRMB.REQID-1].WS_REQ_FST_FLG = ' ';
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_NOTFND, BPCPRMB.RC);
        }
    }
    public void B03_ENDBR() throws IOException,SQLException,Exception {
        if (BPCPRMB.REQID < 1 
            || BPCPRMB.REQID > 3 
            || WS_REQ_INFO[BPCPRMB.REQID-1].WS_REQ_USED_FLG != 'Y') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID REQUEST";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        B03_02_ENDBR_DB();
        IBS.init(SCCGWA, WS_REQ_INFO[BPCPRMB.REQID-1]);
    }
    public void B03_02_ENDBR_DB() throws IOException,SQLException,Exception {
        if (BPCPRMB.REQID == 1) {
            T00_ENDBR_BPTPARM_1();
        } else if (BPCPRMB.REQID == 2) {
            T00_ENDBR_BPTPARM_2();
        } else if (BPCPRMB.REQID == 3) {
            T00_ENDBR_BPTPARM_3();
        }
    }
    public void T00_STARTBR_BPTPARM_1() throws IOException,SQLException,Exception {
        BPTPARM_BR.rp = new DBParm();
        BPTPARM_BR.rp.TableName = "BPTPARM";
        BPTPARM_BR.rp.eqWhere = "TYPE,IBS_AC_BK";
        BPTPARM_BR.rp.where = "CODE LIKE :BPRPARM.KEY.CODE "
            + "AND EFF_DATE <= :BPRPARM.EFF_DATE "
            + "AND EXP_DATE > :BPRPARM.EFF_DATE";
        BPTPARM_BR.rp.Reqid = 1;
        BPTPARM_BR.rp.order = "CODE, EFF_DATE DESC";
        IBS.STARTBR(SCCGWA, BPRPARM, this, BPTPARM_BR);
    }
    public void T00_STARTBR_BPTPARM_2() throws IOException,SQLException,Exception {
        BPTPARM_BR.rp = new DBParm();
        BPTPARM_BR.rp.TableName = "BPTPARM";
        BPTPARM_BR.rp.eqWhere = "TYPE,IBS_AC_BK";
        BPTPARM_BR.rp.where = "CODE LIKE :BPRPARM.KEY.CODE "
            + "AND EFF_DATE <= :BPRPARM.EFF_DATE "
            + "AND EXP_DATE > :BPRPARM.EFF_DATE";
        BPTPARM_BR.rp.Reqid = 2;
        BPTPARM_BR.rp.order = "CODE, EFF_DATE DESC";
        IBS.STARTBR(SCCGWA, BPRPARM, this, BPTPARM_BR);
    }
    public void T00_STARTBR_BPTPARM_3() throws IOException,SQLException,Exception {
        BPTPARM_BR.rp = new DBParm();
        BPTPARM_BR.rp.TableName = "BPTPARM";
        BPTPARM_BR.rp.eqWhere = "TYPE,IBS_AC_BK";
        BPTPARM_BR.rp.where = "CODE LIKE :BPRPARM.KEY.CODE "
            + "AND EFF_DATE <= :BPRPARM.EFF_DATE "
            + "AND EXP_DATE > :BPRPARM.EFF_DATE";
        BPTPARM_BR.rp.Reqid = 3;
        BPTPARM_BR.rp.order = "CODE, EFF_DATE DESC";
        IBS.STARTBR(SCCGWA, BPRPARM, this, BPTPARM_BR);
    }
    public void T00_READNEXT_BPTPARM_1() throws IOException,SQLException,Exception {
        BPTPARM_BR.rp.Reqid = 1;
        IBS.READNEXT(SCCGWA, BPRPARM, this, BPTPARM_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_END_FLG = 'Y';
        }
    }
    public void T00_READNEXT_BPTPARM_2() throws IOException,SQLException,Exception {
        BPTPARM_BR.rp.Reqid = 2;
        IBS.READNEXT(SCCGWA, BPRPARM, this, BPTPARM_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_END_FLG = 'Y';
        }
    }
    public void T00_READNEXT_BPTPARM_3() throws IOException,SQLException,Exception {
        BPTPARM_BR.rp.Reqid = 3;
        IBS.READNEXT(SCCGWA, BPRPARM, this, BPTPARM_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_END_FLG = 'Y';
        }
    }
    public void T00_ENDBR_BPTPARM_1() throws IOException,SQLException,Exception {
        BPTPARM_BR.rp.Reqid = 1;
        IBS.ENDBR(SCCGWA, BPTPARM_BR);
    }
    public void T00_ENDBR_BPTPARM_2() throws IOException,SQLException,Exception {
        BPTPARM_BR.rp.Reqid = 2;
        IBS.ENDBR(SCCGWA, BPTPARM_BR);
    }
    public void T00_ENDBR_BPTPARM_3() throws IOException,SQLException,Exception {
        BPTPARM_BR.rp.Reqid = 3;
        IBS.ENDBR(SCCGWA, BPTPARM_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPRMB.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, " BPCPRMB = ");
            CEP.TRC(SCCGWA, BPCPRMB);
        }
    } //FROM #ENDIF
        return;
    }
    public void JIBS_RETURN() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPRMB.DAT_PTR);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
