package com.hisun.CM;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CMZUBSPM {
    DBParm CMTBSPM_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    char WS_CMTBSPM_FLG = ' ';
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    CMRBSPM CMRBSPM = new CMRBSPM();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CMCRBSPM CMCRBSPM;
    public void MP(SCCGWA SCCGWA, CMCRBSPM CMCRBSPM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CMCRBSPM = CMCRBSPM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CMZUBSPM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHECK();
        if (pgmRtn) return;
        if (CMCRBSPM.FUNC == '1') {
            B200_ADD_BSPM_PROC();
            if (pgmRtn) return;
        } else if (CMCRBSPM.FUNC == '2') {
            B300_UPDATE_BSPM_PROC();
            if (pgmRtn) return;
        } else if (CMCRBSPM.FUNC == '3') {
            B400_INQ_BSPM_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_FUNC_ERROR, CMCRBSPM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_INPUT_CHECK() throws IOException,SQLException,Exception {
        if (CMCRBSPM.AP_TYPE.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_AP_TYPE_INP_ERR, CMCRBSPM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (CMCRBSPM.AP_BATNO == 0) {
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_AP_BATNO_INP_ERR, CMCRBSPM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_ADD_BSPM_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMRBSPM);
        CMRBSPM.KEY.AP_TYPE = CMCRBSPM.AP_TYPE;
        CMRBSPM.KEY.AP_BATNO = CMCRBSPM.AP_BATNO;
        CMRBSPM.IN_DATA = CMCRBSPM.IN_DATA;
        CMRBSPM.TR_DATA = CMCRBSPM.TR_DATA;
        T000_WRITE_CMTBSPM();
        if (pgmRtn) return;
    }
    public void B300_UPDATE_BSPM_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMCRBSPM.AP_TYPE);
        CEP.TRC(SCCGWA, CMCRBSPM.AP_BATNO);
        CEP.TRC(SCCGWA, CMCRBSPM.TR_DATA);
        IBS.init(SCCGWA, CMRBSPM);
        CMRBSPM.KEY.AP_TYPE = CMCRBSPM.AP_TYPE;
        CMRBSPM.KEY.AP_BATNO = CMCRBSPM.AP_BATNO;
        T000_READUP_CMTBSPM();
        if (pgmRtn) return;
        if (WS_CMTBSPM_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_REC_NOTFND, CMCRBSPM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CMRBSPM.TR_DATA = CMCRBSPM.TR_DATA;
        T000_REWRITE_CMTBSPM();
        if (pgmRtn) return;
    }
    public void B400_INQ_BSPM_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMRBSPM);
        CMRBSPM.KEY.AP_TYPE = CMCRBSPM.AP_TYPE;
        CMRBSPM.KEY.AP_BATNO = CMCRBSPM.AP_BATNO;
        T000_READ_CMTBSPM();
        if (pgmRtn) return;
        if (WS_CMTBSPM_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_REC_NOTFND, CMCRBSPM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CMCRBSPM.IN_DATA = CMRBSPM.IN_DATA;
        CMCRBSPM.TR_DATA = CMRBSPM.TR_DATA;
    }
    public void T000_READ_CMTBSPM() throws IOException,SQLException,Exception {
        CMTBSPM_RD = new DBParm();
        CMTBSPM_RD.TableName = "CMTBSPM";
        IBS.READ(SCCGWA, CMRBSPM, CMTBSPM_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CMTBSPM_FLG = 'Y';
        } else {
            WS_CMTBSPM_FLG = 'N';
        }
    }
    public void T000_READUP_CMTBSPM() throws IOException,SQLException,Exception {
        CMTBSPM_RD = new DBParm();
        CMTBSPM_RD.TableName = "CMTBSPM";
        CMTBSPM_RD.upd = true;
        IBS.READ(SCCGWA, CMRBSPM, CMTBSPM_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CMTBSPM_FLG = 'Y';
        } else {
            WS_CMTBSPM_FLG = 'N';
        }
    }
    public void T000_REWRITE_CMTBSPM() throws IOException,SQLException,Exception {
        CMTBSPM_RD = new DBParm();
        CMTBSPM_RD.TableName = "CMTBSPM";
        IBS.REWRITE(SCCGWA, CMRBSPM, CMTBSPM_RD);
    }
    public void T000_WRITE_CMTBSPM() throws IOException,SQLException,Exception {
        CMTBSPM_RD = new DBParm();
        CMTBSPM_RD.TableName = "CMTBSPM";
        CMTBSPM_RD.errhdl = true;
        IBS.WRITE(SCCGWA, CMRBSPM, CMTBSPM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, CMCMSG_ERROR_MSG.CM_REQTR_REC_DUP, CMCRBSPM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
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
